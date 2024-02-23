package com.ece.springBoot3Jpa2Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional ()
public class NachAPIReasonCodeService {

	@Autowired
	private NachAPIReasonCodeRepository repository;
	
	
	

	public String getByReasonCode(String reasonCode) {
		return repository.getByReasonCode(reasonCode);
	}

	public String getByReasonCodeAndCategory(String reasonCode, String category) {
		return repository.getByReasonCodeAndCategory(reasonCode, category);
	}

	public String getByReasonMapCodeAndCategory(String reasonMapCode, String category) {
		return repository.getByReasonMapCodeAndCategory(reasonMapCode, category);
	}
}
