package com.socket_testing.socket.service;

import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.model.User;
import com.socket_testing.socket.repository.MessageBoardRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageBoardService {

    @NonNull
    private MessageBoardRepository messageBoardRepository;

    public MessageBoard getMessageBoard(Long id) {
        return messageBoardRepository.getMessageBoardById(id);
    }

    public List<MessageBoard> getMessageBoards(int pageSize, int )

    public void saveMessageBoard(MessageBoard messageBoard) {
        messageBoardRepository.save(messageBoard);
        return;
    }

    public MessageBoard updateMessageBoard(MessageBoard messageBoard) {
        MessageBoard savedMessageBoard = messageBoardRepository.getMessageBoardById(messageBoard.getId());

        if (savedMessageBoard == null) throw new RuntimeException("Message board does not exist");

        savedMessageBoard.setMessages(messageBoard.getMessages());
        savedMessageBoard.setUsers(messageBoard.getUsers());

        MessageBoard updatedMessageBoard = messageBoardRepository.save(savedMessageBoard);
        return updatedMessageBoard;
    }

}
