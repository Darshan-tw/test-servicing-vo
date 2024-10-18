package com.hli.services.repository;

import com.hli.services.repository.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, String> {

    Optional<PlanEntity> findByPolicyNumber(String policyNumber);
}