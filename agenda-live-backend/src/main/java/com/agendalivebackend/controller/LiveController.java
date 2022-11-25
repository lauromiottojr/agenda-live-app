package com.agendalivebackend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agendalivebackend.model.LiveModel;
import com.agendalivebackend.service.LiveService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lives")
public class LiveController {

	@Autowired
	LiveService liveService;

	@GetMapping
	public ResponseEntity<Page<LiveModel>> getAllLives(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String flag) {
		Page<LiveModel> livePage = liveService.findAll(pageable, flag);
		if (livePage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<LiveModel>>(livePage, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<LiveModel> getOneLive(@PathVariable(value = "id") Integer id) {
		Optional<LiveModel> liveO = liveService.findById(id);
		if (!liveO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<LiveModel>(liveO.get(), HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<LiveModel> saveLive(@RequestBody LiveModel live) {
		live.setRegistrationDate(LocalDateTime.now());
		return new ResponseEntity<LiveModel>(liveService.save(live), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLive(@PathVariable(value = "id") Integer id) {
		Optional<LiveModel> liveO = liveService.findById(id);
		if (!liveO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			liveService.delete(liveO.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<LiveModel> updateLive(@PathVariable(value = "id") Integer id,
			@RequestBody LiveModel liveModel) {
		Optional<LiveModel> liveO = liveService.findById(id);
		if (!liveO.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			liveModel.setId(liveO.get().getId());
			return new ResponseEntity<LiveModel>(liveService.save(liveModel), HttpStatus.OK);
		}
	}

}
