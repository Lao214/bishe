package com.example.mymusic.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.entity.Member;
import com.example.mymusic.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {


     private  static IMemberService staticMemberService;

     @Resource
     private  IMemberService memberService;

     @PostConstruct
     public  void  setMemberService(){
         staticMemberService = memberService;
     }

    /**
     * 生成token
     * @return
     */
    public  static  String genToken(String userId,String sign) {
      return   JWT.create().withAudience(userId)       //将userId保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))    // 两小时后token 过期
                .sign(Algorithm.HMAC256(sign)); // 以password作为sign，token的密钥
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public  static Member getMemberByToken(){
        try {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
          if(StringUtils.isNotEmpty(token)){
                String memberId =JWT.decode(token).getAudience().get(0);
                return  staticMemberService.queryMemberById(Long.valueOf(memberId));
          }
        }catch (Exception e){
            return  null;
        }
        return null;
    }
}
