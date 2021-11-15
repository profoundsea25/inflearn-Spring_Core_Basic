package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    int discount(Member menber, int price);

}
