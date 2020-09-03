package com.dev.yn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.dev.yn.Model.Member;
import com.dev.yn.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional
	//@Rollback(false)
	public void testMember() throws Exception {
		//given
		Member member = new Member();
		member.setUsername("memberA");
 
		//when
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId); 
		
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member);
		System.out.println("findMember == member: " + (findMember == member));
	}
}
