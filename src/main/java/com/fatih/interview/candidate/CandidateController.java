package com.fatih.interview.candidate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;

	@RequestMapping("/trial")
	public void doSth() {
		Candidate candidate = new Candidate();
		candidate.setLastName("Kaygisiz");
		candidate.setName("Fatih");
		candidateService.doSth(candidate);
	}
	
	@RequestMapping("/findAll")
	public ResponseEntity<List<Candidate>> findAll() {
		return candidateService.findAll();
	}
	
	
}
