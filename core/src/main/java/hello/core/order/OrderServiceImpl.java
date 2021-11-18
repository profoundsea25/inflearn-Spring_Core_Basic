package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // : final 붙은 멤버 생성자를 자동으로 만들어준다.
// 현재 생성자가 하나이기 때문에 Autowired가 자동으로 붙는다. (깔끔)
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 조회 빈이 2개 이상인 경우
//    @Autowired
//    private final DiscountPolicy rateDiscountPolicy; 이런 식의 표현 가능

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // FixDiscountPolicy 에서 RateDiscountPolicy 로 코드를 수정하는 순간 OCP 위반!
    // 뿐만 아니라 추상(인터페이스, 여기선 DiscountPolicy) 뿐만 아니라 구체(구현) 클래스(RateDiscountPolicy)에도 의존하고 있음. DIP 위반!


    // 어노테이션 추가 (Qualifier의 대안)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 생성자 주입
    // @Component이고, 생성자가 딱 1개만 있으면, 생성자가 자동으로 @Autowired (자동 주입) 된다.
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 수정자 주입 setter 주입
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }


    // 필드 주입 (사용하지 말자!)
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;


    // 일반 메서드 주입 (사용 잘 안 함)
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
