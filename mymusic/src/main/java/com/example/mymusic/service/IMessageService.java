package com.example.mymusic.service;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.entity.Message;
import com.example.mymusic.entity.param.MessageParam;

import java.util.List;


/**
 * 类说明：库存表管理接口
 * @author 聂枫
 * @date 2021年09月28日 18时19分06秒
 */
public interface IMessageService {


    /**
     * 备注： Integer
     * 方法说明：添加库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒

     * @return
     */
    public Message queryMessageById(Long id) throws FdServiceException;

    /**
     * 备注： Integer
     * 删除库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Message login(Message message) throws FdServiceException;



    /**
     * 备注： Integer
     * 修改库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return
     */
    public Integer addMessage(Message message) throws FdServiceException;

    /**
     * 备注： Integer
     * 通过编号查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Message updateMessage(Message message) throws FdServiceException;

    public Integer deleteByMessageId(Long id) throws FdServiceException;

    /**
     * 备注： Integer
     * 统计查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return Long
     */
    public Long queryMessageCountByCondition(MessageParam messageParam) throws FdServiceException;

    /**
     * 备注： Integer
     * 分页查询库存表dao
     * @author 聂枫
     * @date 2021年09月28日 18时19分06秒
     * @return List<StockClass>
     */
    public List<Message> queryMessageByCondition(MessageParam messageParam) throws FdServiceException;


}