package org.jboss.as.quickstarts.kitchensink.controller;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: rmukkama
 * Date: 10/1/2024
 */
public interface MemberResourceRESTController {

    @GetMapping
    ResponseEntity<?> listAllMembers();

    @GetMapping("/{id}")
    ResponseEntity<?> lookupMemberById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<?> createMember(@RequestBody Member member);

}
