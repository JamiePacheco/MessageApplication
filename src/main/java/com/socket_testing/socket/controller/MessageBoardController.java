package com.socket_testing.socket.controller;


import com.socket_testing.socket.model.MessageBoard;
import com.socket_testing.socket.model.Response;
import com.socket_testing.socket.service.MessageBoardService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message-board")
@RequiredArgsConstructor
public class MessageBoardController {

    @NonNull
    MessageBoardService messageBoardService;

    @GetMapping()
    public ResponseEntity<Response<List<MessageBoard>>> getMessageBoard(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("query")String query) {
        try {
            List<MessageBoard> savedMessageBoard = messageBoardService
                    .getMessageBoards(pageSize, pageNum,query);
            return ResponseEntity.ok(
                    Response.<List<MessageBoard>>builder()
                            .message("Successfully Retrieved Message Boards")
                            .responseContent(savedMessageBoard)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.<List<MessageBoard>>builder()
                                    .message("Error Retrieving Message Boards: " + ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.BAD_REQUEST)
                                    .build()
                    );
        }
    }

    @PostMapping()
    public ResponseEntity<Response<MessageBoard>> saveMessageBoard(@RequestBody MessageBoard messageBoard) {
        try {
            MessageBoard savedMessageBoard = messageBoardService.saveMessageBoard(messageBoard);
            return ResponseEntity.ok(
                    Response.<MessageBoard>builder()
                            .message("Successfully saved message board")
                            .responseContent(savedMessageBoard)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.<MessageBoard>builder()
                                    .message("Error saving new message board: " + ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.BAD_REQUEST)
                                    .build()
                    );
        }
    }

    @PutMapping()
    public ResponseEntity<Response<MessageBoard>> updateMessageBoard(@RequestBody MessageBoard messageBoard) {
        try {
            MessageBoard updatedMessageBoard = messageBoardService.updateMessageBoard(messageBoard);
            return ResponseEntity.ok(
                    Response.<MessageBoard>builder()
                            .message("Successfully updated message board")
                            .responseContent(updatedMessageBoard)
                            .status(HttpStatus.ACCEPTED)
                            .build()
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(
                            Response.<MessageBoard>builder()
                                    .message("Error updating message board: " + ex.getMessage())
                                    .responseContent(null)
                                    .status(HttpStatus.BAD_REQUEST)
                                    .build()
                    );
        }
    }







}
