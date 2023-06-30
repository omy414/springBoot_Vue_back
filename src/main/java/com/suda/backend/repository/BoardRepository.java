package com.suda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suda.backend.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
    
}
