package com.ece.springBoot3Jpa2Database;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CbsKarbResponse {

	private String accountNum;

	private List<Map<String, String>> panDetails;

	private List<String> accountHolderName;
	
	private String errorCode;
	
	private String errorMsg;
	
	private String accountType;

	private String accountStatus;

}
