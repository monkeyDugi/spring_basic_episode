package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    @Test
    void vip_o() {
        // given
        Member member = new Member(1L, "memebrA", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10_000);

        // then
        assertThat(discount).isEqualTo(1_000);
    }

    @DisplayName("VIP 아니면 할인이 적용되지 않는다.")
    @Test
    void vip_x() {
        // given
        Member member = new Member(1L, "memebrA", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10_000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}