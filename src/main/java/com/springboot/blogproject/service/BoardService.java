package com.springboot.blogproject.service;


import com.springboot.blogproject.domain.Board;
import com.springboot.blogproject.dto.BoardRequestDto;
import com.springboot.blogproject.dto.BoardResponseDto;
import com.springboot.blogproject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //전체 보기
    public List<BoardResponseDto.boardDetail> boardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto.boardDetail> detailList = new ArrayList<>();
        for(int i=0;i<boardList.size();i++){
            Board board = boardList.get(i);
            BoardResponseDto.boardDetail boardDetail = BoardResponseDto.boardDetail.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdAt(board.getCreatedAt())
                    .build();
            detailList.add(boardDetail);
        }
        return detailList;
    }

    //상세보기
    public BoardResponseDto.boardDetail detailBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 글입니다."));
        BoardResponseDto.boardDetail detailBoard = BoardResponseDto.boardDetail.builder()
                .title(board.getTitle())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt())
                .content(board.getContent())
                .build();
        return detailBoard;
    }

    //작성
    @Transactional
    public BoardResponseDto.boardDetail writeBoard(BoardRequestDto.writeBoard boardRequestDto){
        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .writer(boardRequestDto.getWriter())
                .password(boardRequestDto.getPassword())
                .build();
        boardRepository.save(board);

        BoardResponseDto.boardDetail boardDetail = BoardResponseDto.boardDetail.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .createdAt(board.getCreatedAt())
                .writer(boardRequestDto.getWriter())
                .build();

        return boardDetail;
    }

    //수성
    @Transactional
    public BoardResponseDto.boardDetail modifiedBoard(Long boardId, BoardRequestDto.writeBoard boardRequestDto) throws IllegalAccessException {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 글입니다."));

        if (!Objects.equals(boardRequestDto.getPassword(), board.getPassword())) {
            throw new IllegalAccessException("글을 수정할 권한이 없습니다");
        }

        board.updateBoard(boardRequestDto.getTitle(),boardRequestDto.getContent());

        Board updateBoard = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 글입니다."));

        BoardResponseDto.boardDetail boardDetail = BoardResponseDto.boardDetail.builder()
                .title(updateBoard.getTitle())
                .content(updateBoard.getContent())
                .createdAt(updateBoard.getCreatedAt())
                .writer(updateBoard.getWriter())
                .build();
        return boardDetail;
    }


    //삭제
    public String deleteBoard(Long boardId){
        boardRepository.deleteById(boardId);
        return boardId + "번 글 삭제가 완료되었습니다.";
    }
}
