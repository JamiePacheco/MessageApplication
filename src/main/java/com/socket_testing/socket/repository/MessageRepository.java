package com.socket_testing.socket.repository;

import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> getMessageByAuthor(PageRequest pageRequest, User author);

    Page<Message> getMessageByMessageBoard(PageRequest pageRequest, MessageBoard messageBoard);


}
