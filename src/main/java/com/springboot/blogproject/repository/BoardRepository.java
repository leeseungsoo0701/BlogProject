package com.springboot.blogproject.repository;

import com.springboot.blogproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
