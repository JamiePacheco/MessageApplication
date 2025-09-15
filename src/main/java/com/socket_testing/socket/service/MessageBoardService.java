package com.socket_testing.socket.service;

import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.repository.MessageBoardRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<MessageBoard> getMessageBoards(int pageSize, int pageNum, String query) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<MessageBoard> messageBoards = messageBoardRepository.getMessageBoardsByQuery(query, pageRequest);

        return messageBoards.stream().toList();
    }

    public MessageBoard saveMessageBoard(MessageBoard messageBoard) {
        messageBoard.getUsers().add(messageBoard.getAuthor());
        MessageBoard savedMessageBoard = messageBoardRepository.save(messageBoard);
        return savedMessageBoard;
    }

    public MessageBoard updateMessageBoard(MessageBoard messageBoard) {
        MessageBoard savedMessageBoard = messageBoardRepository.getMessageBoardById(messageBoard.getId());

        if (savedMessageBoard == null) throw new RuntimeException("Message board does not exist");

        savedMessageBoard.setMessages(messageBoard.getMessages());
        savedMessageBoard.setUsers(messageBoard.getUsers());
        savedMessageBoard.setMessageBoardBannerImage(messageBoard.getMessageBoardBannerImage());
        savedMessageBoard.setMessageBoardProfileImage(messageBoard.getMessageBoardProfileImage());
        savedMessageBoard.setMessageBoardDescription(messageBoard.getMessageBoardDescription());

        MessageBoard updatedMessageBoard = messageBoardRepository.save(savedMessageBoard);
        return updatedMessageBoard;
    }

}
