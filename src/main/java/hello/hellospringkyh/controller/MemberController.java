package hello.hellospringkyh.controller;

import hello.hellospringkyh.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private MemberService memberService;
    //필드 주입 : 권장 x
    //@Autowired private final MemberService memberService;

    //Setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }


    //생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        memberService.setMemberRepository();
    }
}
