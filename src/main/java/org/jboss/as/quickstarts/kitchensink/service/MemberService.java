package org.jboss.as.quickstarts.kitchensink.service;

import org.jboss.as.quickstarts.kitchensink.model.Member;

import java.util.List;
import java.util.Optional;

/**
 * User: rmukkama
 * Date: 10/1/2024
 */
public interface MemberService {

    List<Member> findAllOrderedByName();
    Optional<Member> findByMemberId(Long id);
    void register(Member member);

}
