package org.jboss.as.quickstarts.kitchensink.service.impl;

import lombok.RequiredArgsConstructor;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.repository.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.service.MemberService;
import org.jboss.as.quickstarts.kitchensink.service.MemberValidationService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * User: rmukkama
 * Date: 10/1/2024
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAllOrderedByName() {
        return memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Optional<Member> findByMemberId(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    @Override
    public void register(Member member) {
        memberRepository.saveAndFlush(member);
    }
}
