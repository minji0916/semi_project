package com.example.minji0708;

import com.example.minji0708.repository.JpaMemberRepository;
import com.example.minji0708.repository.MemberRepository;
import com.example.minji0708.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(EntityManager em){
        return new MemberService(new JpaMemberRepository(em));
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
