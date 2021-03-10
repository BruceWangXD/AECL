package com.aecl.demo.service.impl;

import com.aecl.demo.dao.MessageMapper;
import com.aecl.demo.dao.PersonInfoMapper;
import com.aecl.demo.entity.Message;
import com.aecl.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    PersonInfoMapper personInfoMapper;

    @Override
    public void addMessage(Message message) {
        messageMapper.insert(message);
    }

    @Override
    public void deleteMessageById(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Message> getAllRecvMsgByUID(int id) {
        List<Message> messages = messageMapper.getAllRecvMsgByUID(id);
        for (Message message : messages) {
            message.setSenderName(personInfoMapper.selectUserNameById(message.getSenderId()));
        }
        return messages;
    }

    @Override
    public Message getOneById(int id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        boolean flag = checkMessageAuth(message.getMessageId(), message.getReceiverId());
        if (flag) {
            message.setSenderName(personInfoMapper.selectUserNameById(message.getSenderId()));
            return message;
        } else {
            return null;
        }
    }

    @Override
    public List<Message> getAllMess(int id) {
        List<Message> messages = messageMapper.getAllMess(id);
        for (Message message : messages) {
            message.setSenderName(personInfoMapper.selectUserNameById(message.getSenderId()));
        }
        return messages;
    }

    @Override
    public Boolean checkMessageAuth(int mid, int pid) {
        Integer temp = messageMapper.selectMessageByAuth(mid, pid);
        return temp != null;
    }

    @Override
    public void deleteMsgByUid(int id) {
        messageMapper.deleteMessageByReceiver(id);
        messageMapper.deleteMessageBySender(id);
    }
}
