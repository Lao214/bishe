package com.example.mymusic.dao;



import com.example.mymusic.entity.Message;
import com.example.mymusic.entity.param.MessageParam;

import java.util.List;


/**
 * 类说明：库存表dao
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IMessageDao {


    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return
     */
    public Message queryMessageById(Long id);

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Message login(Message message);



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Integer addMessage(Message message);

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public int updateMessage(Message message);

    public Integer deleteByMessageId(Long id);

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryMessageCountByCondition(MessageParam messageParam);

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockClass>
     */
    public List<Message> queryMessageByCondition(MessageParam messageParam);



}