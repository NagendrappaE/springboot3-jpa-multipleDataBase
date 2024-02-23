package com.ece.springBoot3Jpa2Database;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("nach_api_reason_code")
public class NachAPIReasonCode {

	@Id
	@Column("RSN_ID")
	private Long rsnId;

	@Column("DEL_FLG")
	private int delFlag;

	@Column("RSN_CATEGORY")
	private String rsnCategory;

	@Column("RSN_CODE")
	private String rsnCode;

	@Column("RSN_DESC")
	private String rsnDesc;

	@Column("RSN_MAP_CODE")
	private String rsnMapCode;

	@Column("RSN_SOURCE")
	private String rsnSource;

}
