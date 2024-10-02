package org.jboss.as.quickstarts.kitchensink.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.jboss.as.quickstarts.kitchensink.model.Member;

/**
 * User: rmukkama
 * Date: 10/2/2024
 */
public interface MemberValidationService {

    void validateMember(Member member) throws ConstraintViolationException, ValidationException;
    boolean emailAlreadyExists(String email);

}
