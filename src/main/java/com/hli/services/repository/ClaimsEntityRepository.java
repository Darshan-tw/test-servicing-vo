package com.hli.services.repository;

import com.hli.services.repository.entity.ClaimEntity;
import com.hli.services.repository.entity.MemberDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimsEntityRepository extends JpaRepository<ClaimEntity, String> {
    Optional<ClaimEntity> findByPolicyNumber(String policyNumber);
}
