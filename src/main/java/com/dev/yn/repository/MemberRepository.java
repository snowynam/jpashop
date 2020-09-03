package com.dev.yn.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.dev.yn.domain.Member;

@Repository
public class MemberRepository {
	
	@PersistenceContext //스프링부트가 엔티티 매니저를 알아서 생성해줌.
	private EntityManager em;
	
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
