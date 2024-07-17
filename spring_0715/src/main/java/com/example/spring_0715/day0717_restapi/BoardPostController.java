package com.example.spring_0715.day0717_restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/board-post")
public class BoardPostController {

    private final BoardPostService boardPostService;

    @Autowired
    public BoardPostController(BoardPostService boardPostService){
        this.boardPostService = boardPostService;
    }

    @PostMapping
    public ResponseEntity<BoardPostDto> createBoardPost(@RequestBody BoardPostDto boardPostDto){
        BoardPostDto createdBoardPostDto = boardPostService.createBoardPost(boardPostDto);
        return ResponseEntity.ok(createdBoardPostDto);
    }
}
