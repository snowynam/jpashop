package com.dev.yn.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dev.yn.domain.Member;
import com.dev.yn.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
//@ActiveProfiles("test") // Like this
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	//tm ctrl + space, @test 생성
    @Test
    //@Rollback(false)
	public void 회원가입() {
		// given 
    	Member member = new Member();
    	member.setName("kim");
		// when
    	Long savedId = memberRepository.join(member);
    	
		// then
    	//em.flush();
    	assertEquals(member, memberRepository.findOne(savedId));
		
	}
    
    @Test
	public void 중복_회원_예약() {
		// given 
    	Member member1 = new Member();
    	member1.setName("kim");
    	Member member2 = new Member();
    	member2.setName("kim");
    	
		// when
    	memberService.join(member1);
    	IllegalStateException exception = assertThrows(IllegalStateException.class,
	           () -> memberService.join(member2),
	           "이름 중복");
		// then
        assertEquals("이미 존재하는 회원입니다.", exception.getMessage());
		//fail("예외가 발생해야 한다.");
	}
    
}
