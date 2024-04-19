package com.recykling.report.employee.controller.request;

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
public class RequestUpdateEmployee {
    
    @Valid @NotNull(message = "FullName can not be null")
    private FullName fullName;

    @NotNull(message = "Active can not be null")
    private Boolean active;
}
