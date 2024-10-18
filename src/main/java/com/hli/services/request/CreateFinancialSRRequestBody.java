package com.hli.services.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Data
public class CreateFinancialSRRequestBody {
    private String policyNumber;
    private String memberNumber;
    private List<ModifiedFields> modifiedFields;
    private List<MultipartFile> uploadedDocuments;

}