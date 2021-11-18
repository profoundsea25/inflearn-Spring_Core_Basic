package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 빈 이름이 중복될 경우 자동 vs 자동은 충돌 오류,
    // 자동 vs 수동은 수동이 우선권을 가진다. 수동이 자동을 오버라이딩 한다.
    // 최근에는 스프링 부트가 자동 vs 수동도 오류나도록 기본값이 변경되었다.
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
