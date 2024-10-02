package org.jboss.as.quickstarts.kitchensink.controller.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.jboss.as.quickstarts.kitchensink.controller.MemberResourceRESTController;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberService;
import org.jboss.as.quickstarts.kitchensink.service.MemberValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * User: rmukkama
 * Date: 10/1/2024
 */
@RestController
@RequestMapping("/rest/members")
@RequiredArgsConstructor
public class MemberResourceRESTControllerImpl implements MemberResourceRESTController {

    private final MemberService memberService;
    private final MemberValidationService memberValidationService;

    @Override
    public ResponseEntity<?> listAllMembers() {
        List<Member> members = memberService.findAllOrderedByName();

        return ResponseEntity.status(HttpStatus.OK)
                .body(members);
    }

    @Override
    public ResponseEntity<?> lookupMemberById(Long id) {
        Optional<Member> member = memberService.findByMemberId(id);

        if(member.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(member.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("not found");
    }

    @Override
    public ResponseEntity<?> createMember(Member member) {
        ResponseEntity<?> responseEntity = null;

        try{
            memberValidationService.validateMember(member);
            memberService.register(member);

            responseEntity = ResponseEntity.status(HttpStatus.OK).body("");
        } catch (ConstraintViolationException ce){
            // Handle bean validation issues
            responseEntity = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e){
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("email", "Email taken");
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(responseObj);
        } catch (Exception e){
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObj);
        }

        return responseEntity;
    }

    private ResponseEntity<?> createViolationResponse(Set<ConstraintViolation<?>> violations) {
        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObj);
    }
}
