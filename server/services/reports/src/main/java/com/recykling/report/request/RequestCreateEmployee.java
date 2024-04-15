package com.recykling.report.request;

import com.recykling.report.valueObjects.FullName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WiniaR21
 */
@Data
@NoArgsConstructor
public class RequestCreateEmployee {
    @Valid
    @NotNull(message = "FullName can not be null")
    private FullName fullName;
}
