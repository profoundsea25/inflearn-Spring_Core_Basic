package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // AppConfig 같은 생성 주입 클래스는 역할이 드러나게 코딩하는 것이 중요
    // 생성자 편집은 ctrl+alt+m

    // 생성자 주입 (생성자를 통해 객체를 주입한다)
    // 이로써 DIP가 성립한다.

    // Bean에 등록하면 스프링이 컨테이너에 넣어서 관리해준다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    // 역할과 구현이 한 눈에 보이도록 편집했음.
    // 이러면 사용 영역을 고칠 필요 없이, 구성 영역만 고쳐서 확장/변경 가능.
}
