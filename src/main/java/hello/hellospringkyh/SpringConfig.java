package hello.hellospringkyh;

import hello.hellospringkyh.repository.MemberRepository;
import hello.hellospringkyh.repository.MemoryMemberRepository;
import hello.hellospringkyh.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
