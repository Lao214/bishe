package com.example.mymusic.service.impl;

import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.enums.Status;
import com.example.mymusic.dao.IMessageDao;
import com.example.mymusic.entity.Message;
import com.example.mymusic.entity.param.MessageParam;
import com.example.mymusic.service.IMessageService;
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
@Service("messageService")
public class MessageServiceImpl implements IMessageService {

    private static Logger logger = LoggerFactory.getLogger(Message.class);
    @Resource
    private IMessageDao messageDao;


    @Override
    public Message queryMessageById(Long id) throws FdServiceException {
        return this.messageDao.queryMessageById(id);
    }

    @Override
    public Message login(Message message) throws FdServiceException {
        return this.messageDao.login(message);
    }

    @Override
    public Integer addMessage(Message message) throws FdServiceException {
        message.setStatus(Status.noread.toString());
        return this.messageDao.addMessage(message);
    }

    @Override
    public Message updateMessage(Message message) throws FdServiceException {
        this.messageDao.updateMessage(message);
        Message newMessage =messageDao.queryMessageById(message.getId());
        return newMessage;
    }

    @Override
    public Integer deleteByMessageId(Long id) throws FdServiceException {
        return this.messageDao.deleteByMessageId(id);
    }

    @Override
    public Long queryMessageCountByCondition(MessageParam messageParam) throws FdServiceException {
        return this.messageDao.queryMessageCountByCondition(messageParam);
    }

    @Override
    public List<Message> queryMessageByCondition(MessageParam messageParam) throws FdServiceException {
        return this.messageDao.queryMessageByCondition (messageParam);
    }


}