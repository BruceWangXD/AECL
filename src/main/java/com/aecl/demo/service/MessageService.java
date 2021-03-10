package com.aecl.demo.service;

import com.aecl.demo.entity.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Message message);

    void deleteMessageById(Integer id);

    List<Message> getAllRecvMsgByUID(int id);

    List<Message> getAllMess(int id);

    Message getOneById(int id);

    Boolean checkMessageAuth(int mid, int pid);

    void deleteMsgByUid(int id);
}
