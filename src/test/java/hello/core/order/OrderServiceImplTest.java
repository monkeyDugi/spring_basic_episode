package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {


    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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