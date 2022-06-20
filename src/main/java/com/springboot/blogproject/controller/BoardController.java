package com.springboot.blogproject.controller;


import com.springboot.blogproject.dto.BoardRequestDto;
import com.springboot.blogproject.dto.BoardResponseDto;
import com.springboot.blogproject.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //전체 보기
    @GetMapping("/boards")
    public List<BoardResponseDto.boardDetail> boardList(){
        return boardService.boardList();
    }

    //직성
    @PostMapping("/boards")
    public BoardResponseDto.boardDetail writeBoard(@RequestBody BoardRequestDto.writeBoard boardRequestDto){
        return boardService.writeBoard(boardRequestDto);
    }

    //상세 보기
    @GetMapping("/boards/{boardId}")
    public BoardResponseDto.boardDetail detailBoard(@PathVariable Long boardId){
        return boardService.detailBoard(boardId);
    }

    //수정
    @PutMapping("/boards/{boardId}")
    public BoardResponseDto.boardDetail modifiedBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto.writeBoard boardRequestDto) throws IllegalAccessException {
        return boardService.modifiedBoard(boardId,boardRequestDto);
    }

    //삭제
    @DeleteMapping("/boards/{boardId}")
    public String deleteBoard(@PathVariable Long boardId){
        return boardService.deleteBoard(boardId);
    }
}
