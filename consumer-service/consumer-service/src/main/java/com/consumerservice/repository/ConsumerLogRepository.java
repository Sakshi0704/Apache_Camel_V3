package com.consumerservice.repository;

import com.consumerservice.entity.ConsumerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerLogRepository extends JpaRepository<ConsumerLog, Long> {}

