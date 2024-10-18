package com.hli.services.repository;

import com.hli.services.repository.entity.MemberDetailsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailsRepository extends JpaRepository<MemberDetailsEntity, String> {
    Optional<MemberDetailsEntity> findByPolicyNumberAndMemberNumber(String policyNumber, String memberNumber);
}