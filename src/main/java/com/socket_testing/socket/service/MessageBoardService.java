package com.socket_testing.socket.service;

import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.repository.MessageBoardRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageBoardService {

    @NonNull
    private MessageBoardRepository messageBoardRepository;

    public MessageBoard getMessageBoard(Long id) {
        return messageBoardRepository.getMessageBoardById(id);
    }

}
