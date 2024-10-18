package com.hli.services.repository;

import com.hli.services.repository.entity.ServiceRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRequestsRepository extends JpaRepository<ServiceRequestEntity, String> {
    Optional<ServiceRequestEntity> findByPolicyNumberAndMemberNumberAndServiceRequestTypeAndServiceRequestStatus(
            String policyNumber, String memberNumber, String serviceRequestType, String serviceRequestStatus);
}