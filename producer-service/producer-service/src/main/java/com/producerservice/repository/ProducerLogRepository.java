package com.producerservice.repository;

import com.producerservice.entity.ProducerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerLogRepository extends JpaRepository<ProducerLog, Long> {}

