/**
 * 
 */
package com.ece.springBoot3Jpa2Database;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nagendrappae
 *
 */
@Repository
public interface NachAPIReasonCodeRepository extends  CrudRepository<NachAPIReasonCode, Long> {


	@Query("SELECT RSN_MAP_CODE FROM nach_api_reason_code rsnCode where rsnCode.RSN_CODE = :reasonCode")
	String getByReasonCode(String reasonCode);

	@Query("SELECT RSN_MAP_CODE FROM nach_api_reason_code rsnCode where rsnCode.RSN_CODE = :reasonCode and rsnCode.RSN_CATEGORY = :category")
	String getByReasonCodeAndCategory(String reasonCode, String category);

	@Query("SELECT RSN_CODE FROM nach_api_reason_code rsnCode where rsnCode.RSN_MAP_CODE = :reasonMapCode and rsnCode.RSN_CATEGORY = :category")
	String getByReasonMapCodeAndCategory(String reasonMapCode, String category);


}
