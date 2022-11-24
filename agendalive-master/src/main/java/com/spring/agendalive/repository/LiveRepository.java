package com.spring.agendalive.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.agendalive.document.LiveDocument;

public interface LiveRepository extends MongoRepository<LiveDocument, String> {

	Page<LiveDocument> findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime date, Pageable pageable);

	Page<LiveDocument> findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime date, Pageable pageable);
}
