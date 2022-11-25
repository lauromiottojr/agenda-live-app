package com.agendalivebackend.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agendalivebackend.model.LiveModel;

public interface LiveRepository extends JpaRepository<LiveModel, Integer> {

	Page<LiveModel> findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime date, Pageable pageable);

	Page<LiveModel> findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime date, Pageable pageable);
}
