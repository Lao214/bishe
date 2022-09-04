package com.example.mymusic.dao;



import com.example.mymusic.entity.MemberApply;
import com.example.mymusic.entity.param.MemberApplyParam;

import java.util.List;


/**
 * 类说明：库存表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IMemberApplyDao {


    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return
     */
    public MemberApply queryMemberById(Long id);

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public MemberApply login(MemberApply member);



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Integer addMember(MemberApply member);

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public int updateMember(MemberApply member);

    public Integer deleteByMemberId(Long id);

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryMemberCountByCondition(MemberApplyParam memberParam);

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockClass>
     */
    public List<MemberApply> queryMemberByCondition(MemberApplyParam memberParam);



}