package com.recykling.report.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author WiniaR21
 */
@Data @AllArgsConstructor
public class ResponseDto {
    private String statusCode;
    private String statusMsg;
}
