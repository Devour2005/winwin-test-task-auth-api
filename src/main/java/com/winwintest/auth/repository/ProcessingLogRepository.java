package com.winwintest.auth.repository;

import com.winwintest.auth.model.ProcessingLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingLogRepository extends JpaRepository<ProcessingLog, Long> {

	@Query("SELECT pl FROM ProcessingLog pl JOIN FETCH pl.user")
	List<ProcessingLog> findAllWithUser();

	@Query("SELECT pl FROM ProcessingLog pl JOIN FETCH pl.user WHERE pl.user.id = :userId")
	List<ProcessingLog> findByUserIdWithUser(@Param("userId") Long userId);
}
