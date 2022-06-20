package com.springboot.blogproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String writer;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String password;

    public void updateBoard(String title, String content) {
        this.title =title;
        this.content = content;
    }
}