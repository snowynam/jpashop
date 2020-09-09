package com.dev.yn.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.yn.domain.Member;
import com.dev.yn.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	/** 
	 * 회원 가입
	 */
	@Transactional
	public Long join (Member member) {
		validateDuplicateMember(member); // 중복회원 검증
		memberRepository.join(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// 비지니스 로직이 있더라도 DataBase에 Unique 제약조건을 넣어 주어라
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	//회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	//회원 조회
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
