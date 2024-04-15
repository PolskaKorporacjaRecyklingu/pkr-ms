package com.recykling.report.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Embeddable
public record FullName(
        @Size(min = 3, max = 13, message = "FirstName contains between 3 and 13 letters")
        @NotEmpty(message = "FirstName can not be empty")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName must contain only letters")
        String firstName,
        @Size(min = 3, max = 15, message = "LastName contains between 3 and 13 letters")
        @NotEmpty(message = "LastName can not be empty")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "LastName must contain only letters")
        String lastName
){
        /**
         *
         * @param firstName - First name of employee.
         * @param lastName - Last Name of employee.
         */
        public FullName {
        }
}
