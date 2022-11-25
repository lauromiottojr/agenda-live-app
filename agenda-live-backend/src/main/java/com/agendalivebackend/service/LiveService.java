package com.agendalivebackend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agendalivebackend.model.LiveModel;
import com.agendalivebackend.repository.LiveRepository;

@Service
public class LiveService {

	@Autowired
	LiveRepository liveRepository;

	public Page<LiveModel> findAll(Pageable pageable, String flag) {
		if (flag != null && flag.equals("next")) {
			return liveRepository.findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime.now(), pageable);
		} else if (flag != null && flag.equals("previous")) {
			return liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime.now(), pageable);
		} else {
			return liveRepository.findAll(pageable);
		}
	}

	public Optional<LiveModel> findById(Integer id) {
		return liveRepository.findById(id);
	}

	public LiveModel save(LiveModel liveModel) {
		return liveRepository.save(liveModel);
	}

	public void delete(LiveModel liveModel) {
		liveRepository.delete(liveModel);
	}

}
