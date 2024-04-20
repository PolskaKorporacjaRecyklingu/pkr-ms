package com.recykling.report.exception;

import com.recykling.report.employee.Employee;

public class GivenLeaderHasNotAccount extends RuntimeException{
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public GivenLeaderHasNotAccount(Employee employee) {
        super("Given leader " + employee.getFullName() + " with id: " + employee.getEmployeeId() + " has not account!" );
    }
}
