package com.socket_testing.socket.repository;


import com.socket_testing.socket.model.Message;
import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageBoardRepository extends JpaRepository<MessageBoard, Long> {

    public MessageBoard getMessageBoardById(Long id);


    // TODO finish implementing endpoint for querying message boards
    @Query(value = """
            SELECT mb FROM message_board mb 
            WHERE LOWER(mb.message_board_name) LIKE 
            LOWER(CONCAT('%', :query, '%')) 
            ORDER BY b.message_board_name ASC
            """,
            countQuery = "SELECT count(*) FROM message_board",
            nativeQuery = true
    )
    public Page<MessageBoard> getMessageBoardsByQuery(String query, Pageable pageable);
}
