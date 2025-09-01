package com.socket_testing.socket.repository;


import com.socket_testing.socket.model.MessageBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageBoardRepository extends JpaRepository<MessageBoard, Long> {

    public MessageBoard getMessageBoardById(Long id);

}
