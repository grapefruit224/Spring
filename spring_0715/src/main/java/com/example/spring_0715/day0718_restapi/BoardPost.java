package com.example.spring_0715.day0718_restapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoardPost {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) { // 글에 댓글을 추가하는 메소드
        comments.add(comment);
        comment.setBoardPost(this); // 댓글에 현재 글 정보를 업데이트
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setBoardPost(null);
    }
}
