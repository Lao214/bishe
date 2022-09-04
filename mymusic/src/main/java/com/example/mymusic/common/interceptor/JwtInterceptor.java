package com.example.mymusic.common.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.entity.Member;
import com.example.mymusic.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private IMemberService memberService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String token =  request.getHeader("Authorization");
        //如果不是映射的方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return  true;
        }
        //执行认证
        if(StringUtils.isEmpty(token)){
            throw  new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "无token，请重新登录");
        }
        //获取token中的id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "token验证失败");
        }
        Member member =memberService.queryMemberById(Long.valueOf(userId));
        if(member==null){
            throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "用户不存在，请重新登录");
        }

        //用户密码加签验证token
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256(member.getPassword())).build();
        try {
            jwtVerifier.verify(token);  //验证token
        }catch (JWTVerificationException je){
            throw new FdServiceException(ErrorCode.MEMBER_NOT_EXISTS, "token验证失败，请重新登录");
        }
        return true;
    }
}
