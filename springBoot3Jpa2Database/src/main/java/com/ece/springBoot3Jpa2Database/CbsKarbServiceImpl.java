package com.ece.springBoot3Jpa2Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CbsKarbServiceImpl implements CbsKarbService {

	@Autowired
	private NachAPIReasonCodeService nachAPIReasonCodeService;

	@Autowired
	@Qualifier("secondaryDbJdbcTemplate")
	private JdbcTemplate secondaryDbJdbcTemplate;

	@Override
	public CbsKarbResponse getAccPanDetails(CbsKarbRequest request) {

		try {
			String reasonMapCode = nachAPIReasonCodeService.getByReasonCodeAndCategory("Error", "CBS_CODE");
			String errorCode = null;
			if (reasonMapCode != null && !reasonMapCode.isEmpty()) {
				errorCode = nachAPIReasonCodeService.getByReasonMapCodeAndCategory(reasonMapCode, "NACH_CODE");
			}
		} catch (Exception e) {
			log.error("error{}", e);
		}

		return null;
	}

	@Override
	public CbsKarbResponse getAccHolderName(CbsKarbRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CbsKarbResponse getAccStatus(CbsKarbRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CbsKarbResponse invokeDBLinkAccPanDetails(CbsKarbRequest request) {

		try {
			String reasonMapCode = nachAPIReasonCodeService.getByReasonCodeAndCategory("Error", "CBS_CODE");
			String errorCode = null;
			if (reasonMapCode != null && !reasonMapCode.isEmpty()) {
				errorCode = nachAPIReasonCodeService.getByReasonMapCodeAndCategory(reasonMapCode, "NACH_CODE");
			}
		} catch (Exception e) {
			log.error("error{}", e);
		}

		return null;
	}

}
