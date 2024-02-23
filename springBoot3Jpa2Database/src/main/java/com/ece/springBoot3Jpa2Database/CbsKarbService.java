package com.ece.springBoot3Jpa2Database;

public interface CbsKarbService {

	CbsKarbResponse getAccPanDetails(CbsKarbRequest request);
	
	CbsKarbResponse getAccHolderName(CbsKarbRequest request);

	CbsKarbResponse getAccStatus(CbsKarbRequest request);

	CbsKarbResponse invokeDBLinkAccPanDetails(CbsKarbRequest request);




}
