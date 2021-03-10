package com.aecl.demo.dao;

import com.aecl.demo.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);


    List<Message> getAllRecvMsgByUID(int id);

    List<Message> getAllMess(int id);

    int updateByPrimaryKey(Message record);

    Integer selectMessageByAuth(Integer mid, Integer pid);

    void deleteMessageByReceiver(Integer id);

    void deleteMessageBySender(Integer id);

}