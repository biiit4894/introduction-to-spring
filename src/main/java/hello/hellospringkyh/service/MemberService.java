package hello.hellospringkyh.service;

import hello.hellospringkyh.domain.Member;
import hello.hellospringkyh.repository.MemberRepository;
import hello.hellospringkyh.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    // Ctrl + Shift + t -> Create new test
    // 기존 코드 : 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //생성자 주입
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}

    // MemberService 입장에서의 DI(Dependency Injection)

    /*
    회원 가입
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            //같은 이름이 있는 중복 회원X
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
//        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
