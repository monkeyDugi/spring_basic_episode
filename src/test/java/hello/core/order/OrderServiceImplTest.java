package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        String itemName = "itemA";
        int itemPrice = 10_000;

        memberService.join(new Member(memberId, "memberA", Grade.VIP));

        // when
        Order order = orderService.createOrder(memberId, itemName, itemPrice);

        // then
        assertThat(order).isEqualTo(new Order(memberId, itemName, itemPrice, 1_000));
    }
}