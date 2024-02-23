package com.ece.springBoot3Jpa2Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cbsAPI")
@Slf4j
public class CbsKarbController {

	@Autowired
	private CbsKarbService cbsService;

	@PostMapping("getAccPanDetails")
	public @ResponseBody CbsKarbResponse getAccPanDetails(@RequestBody CbsKarbRequest request) throws Exception {

		return cbsService.invokeDBLinkAccPanDetails(request);

	}

}
