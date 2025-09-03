package com.socket_testing.socket.service;

import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.model.User;
import com.socket_testing.socket.repository.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    @NonNull
    private MessageRepository messageRepository;

    @NonNull
    private UserService userService;

    @NonNull
    private MessageBoardService messageBoardService;

    public List<Message> getMessages(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Message> page = messageRepository.findAll(pageRequest);

        return page.stream().toList();
    }

    public List<Message> getMessagesByUser(int pageNum, int pageSize, String username) {

        User user = userService.getUser(username);
        if (user == null) throw new RuntimeException("No user found with username: " + username);

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Message> page = messageRepository.getMessageByAuthor(pageRequest, user);

        return page.stream().toList();
    }

    public List<Message> getMessagesByMessageBoard(int pageNum, int pageSize, Long messageBoardId) {

        MessageBoard messageBoard = this.messageBoardService.getMessageBoard(messageBoardId);
        if (messageBoard==null) throw new RuntimeException("No message board found with id: " + messageBoardId);

        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Message> page = messageRepository.getMessageByMessageBoard(pageRequest, messageBoard);
        return page.stream().toList();
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}

