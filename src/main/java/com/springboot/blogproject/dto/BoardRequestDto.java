package com.springboot.blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class BoardRequestDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class writeBoard{
        private String title;
        private String writer;
        private String password;
        private String content;
    }

}
