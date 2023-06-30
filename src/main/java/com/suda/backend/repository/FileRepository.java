package com.suda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suda.backend.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
