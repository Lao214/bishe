package com.example.mymusic.service.impl;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.enums.MemberStatus;
import com.example.mymusic.dao.IMemberApplyDao;
import com.example.mymusic.dao.IMemberDao;
import com.example.mymusic.entity.MemberApply;
import com.example.mymusic.entity.param.MemberApplyParam;
import com.example.mymusic.service.IMemberApplyService;
import com.example.mymusic.service.IMemberService;
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
@Service("memberApplyService")
public class MemberApplyServiceImpl implements IMemberApplyService {

    private static Logger logger = LoggerFactory.getLogger(MemberApply.class);
    @Resource
    private IMemberApplyDao memberApplyDao;


    @Override
    public MemberApply queryMemberById(Long id) throws FdServiceException {
        return this.memberApplyDao.queryMemberById(id);
    }

    @Override
    public MemberApply login(MemberApply member) throws FdServiceException {
        return this.memberApplyDao.login(member);
    }

    @Override
    public Integer addMember(MemberApply member) throws FdServiceException {
        member.setStatus(MemberStatus.audit.toString());
        return this.memberApplyDao.addMember(member);
    }

    @Override
    public MemberApply updateMember(MemberApply member) throws FdServiceException {
        this.memberApplyDao.updateMember(member);
        MemberApply newMember =memberApplyDao.queryMemberById(member.getId());
        return newMember;
    }

    @Override
    public Integer deleteByMemberId(Long id) throws FdServiceException {
        return this.memberApplyDao.deleteByMemberId(id);
    }

    @Override
    public Long queryMemberCountByCondition(MemberApplyParam memberParam) throws FdServiceException {
        return this.memberApplyDao.queryMemberCountByCondition(memberParam);
    }

    @Override
    public List<MemberApply> queryMemberByCondition(MemberApplyParam memberParam) throws FdServiceException {
        return this.memberApplyDao.queryMemberByCondition (memberParam);
    }


}