package com.hli.services.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponseEntity {
    private String status;
    private int statusCode;
    private ErrorDetails error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String documentationUrl;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class ErrorDetails {
        private String code;
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String details;
        private LocalDateTime timestamp;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String path;
    }
}