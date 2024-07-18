package com.example.spring_0715.day0718_restapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentDto {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
