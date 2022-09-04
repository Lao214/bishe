package com.example.mymusic.service.impl;

import com.example.mymusic.common.ErrorCode;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.Page;
import com.example.mymusic.common.enums.MemberStatus;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.dao.IMemberDao;
import com.example.mymusic.dao.IStockClassDao;
import com.example.mymusic.entity.EcahrtsClass;
import com.example.mymusic.entity.Member;
import com.example.mymusic.entity.StockClass;
import com.example.mymusic.entity.param.MemberParam;
import com.example.mymusic.entity.param.StockClassParam;
import com.example.mymusic.service.IMemberService;
import com.example.mymusic.service.IStockClassService;
import com.example.mymusic.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类说明：库存表接口实现
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
@Service("memberService")
public class MemberServiceImpl implements IMemberService {

    private static Logger logger = LoggerFactory.getLogger(Member.class);
    @Resource
    private IMemberDao memberDao;


    @Override
    public Member queryMemberById(Long id) throws FdServiceException {
        return this.memberDao.queryMemberById (id);
    }

    @Override
    public Member login(Member member) throws FdServiceException {

        return this.memberDao.login(member);
    }

    @Override
    public Integer addMember(Member member) throws FdServiceException {
        member.setMemberNo(member.getMemberNo());
        member.setStatus(MemberStatus.common.toString());
        member.setMemberLevel(1l);
        return this.memberDao.addMember (member);
    }

    @Override
    public Member updateMember(Member member) throws FdServiceException {
        this.memberDao.updateMember(member);
        Member newMember =memberDao.queryMemberById(member.getId());
        return newMember;
    }

    @Override
    public Integer deleteByMemberId(Long id) throws FdServiceException {
        return this.memberDao.deleteByMemberId (id);
    }

    @Override
    public Long queryMemberCountByCondition(MemberParam memberParam) throws FdServiceException {
        return this.memberDao.queryMemberCountByCondition (memberParam);
    }

    @Override
    public List<Member> queryMemberByCondition(MemberParam memberParam) throws FdServiceException {
        return this.memberDao.queryMemberByCondition (memberParam);
    }


}