package com.example.mymusic.service;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.entity.MemberApply;
import com.example.mymusic.entity.param.MemberApplyParam;

import java.util.List;


/**
 * 类说明：库存表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IMemberApplyService {


    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return
     */
    public MemberApply queryMemberById(Long id) throws FdServiceException;

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public MemberApply login(MemberApply member) throws FdServiceException;



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Integer addMember(MemberApply member) throws FdServiceException;

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public MemberApply updateMember(MemberApply member) throws FdServiceException;

    public Integer deleteByMemberId(Long id) throws FdServiceException;

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryMemberCountByCondition(MemberApplyParam memberParam) throws FdServiceException;

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockClass>
     */
    public List<MemberApply> queryMemberByCondition(MemberApplyParam memberParam) throws FdServiceException;


}