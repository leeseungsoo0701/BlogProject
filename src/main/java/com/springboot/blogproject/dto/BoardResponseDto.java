package com.springboot.blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardResponseDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class boardDetail{
        private String title;
        private String writer;
        private LocalDateTime createdAt;
        private String content;
    }

}
