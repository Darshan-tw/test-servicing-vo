package com.hli.services.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "VO_SERVICE_REQUEST")
public class ServiceRequestEntity {
    @Id
    @Column(name = "SERVICE_REQUEST_ID")
    private String serviceRequestId;

    @Column(name = "POLICY_NUMBER")
    private String policyNumber;

    @Column(name = "MEMBER_NUMBER")
    private String memberNumber;

    @Column(name = "SERVICE_REQUEST_TYPE")
    private String serviceRequestType;

    @Column(name = "SERVICE_REQUEST_VALUE")
    private String serviceRequestValue;

    @Column(name = "SERVICE_REQUEST_STATUS")
    private String serviceRequestStatus;

    @Column(name = "SERVICE_REQUEST_DATE")
    @Temporal(TemporalType.DATE)
    private Date serviceRequestDate;

    @Column(name = "SERVICE_REQUEST_CREATED_BY", nullable = false)
    private String serviceRequestCreatedBy;

    @Column(name = "SERVICE_REQUEST_UPDATED_BY", nullable = false)
    private String serviceRequestUpdatedBy;

    @Column(name = "SERVICE_REQUEST_CREATED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date serviceRequestCreatedDate;

    @Column(name = "SERVICE_REQUEST_UPDATED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date serviceRequestUpdatedDate;

    @Column(name = "SERVICE_REQUEST_APPROVED_BY")
    private String serviceRequestApprovedBy;

    @Column(name = "SERVICE_REQUEST_APPROVED_DATE")
    private Date serviceRequestApprovedDate;

    @Column(name = "SERVICE_REQUEST_APPROVED_STATUS")
    private String serviceRequestApprovedStatus;

    @Column(name = "SERVICE_REQUEST_APPROVED_REMARKS")
    private String serviceRequestApprovedRemarks;


}
