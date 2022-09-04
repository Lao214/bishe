package com.example.mymusic.service;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.entity.Member;
import com.example.mymusic.entity.StockClass;
import com.example.mymusic.entity.param.MemberParam;
import com.example.mymusic.entity.param.StockClassParam;

import java.util.List;


/**
 * 类说明：库存表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IMemberService {


    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return
     */
    public Member queryMemberById(Long id) throws FdServiceException;

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Member login(Member member) throws FdServiceException;



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Integer addMember(Member member) throws FdServiceException;

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Member updateMember(Member member) throws FdServiceException;

    public Integer deleteByMemberId(Long id) throws FdServiceException;

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryMemberCountByCondition(MemberParam memberParam) throws FdServiceException;

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockClass>
     */
    public List<Member> queryMemberByCondition(MemberParam memberParam) throws FdServiceException;


}