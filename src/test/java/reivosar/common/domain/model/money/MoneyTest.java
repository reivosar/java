package reivosar.common.domain.model.money;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Currency;

class MoneyTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateMoneyObjectWithIntegerValue() {
            Money money = new Money(100, Currency.getInstance("USD"));
            Assertions.assertEquals(BigDecimal.valueOf(100), money.getValue());
            Assertions.assertEquals(Currency.getInstance("USD"), money.getCurrency());
        }

        @Test
        void shouldCreateMoneyObjectWithDecimalValue() {
            Money money = new Money(BigDecimal.valueOf(100.50), Currency.getInstance("EUR"));
            Assertions.assertEquals(BigDecimal.valueOf(100.50), money.getValue());
            Assertions.assertEquals(Currency.getInstance("EUR"), money.getCurrency());
        }
    }

    @Nested
    class PlusTest {

        @Test
        void shouldAddTwoMoneyObjects() {
            Money money1 = new Money(50, Currency.getInstance("USD"));
            Money money2 = new Money(25, Currency.getInstance("USD"));
            Money result = money1.plus(money2);
            Assertions.assertEquals(BigDecimal.valueOf(75), result.getValue());
            Assertions.assertEquals(Currency.getInstance("USD"), result.getCurrency());
        }

        @Test
        void shouldAddMultipleMoneyObjects() {
            Money money1 = new Money(50, Currency.getInstance("USD"));
            Money money2 = new Money(25, Currency.getInstance("USD"));
            Money money3 = new Money(30, Currency.getInstance("USD"));
            Money result = money1.plus(money2, money3);
            Assertions.assertEquals(BigDecimal.valueOf(105), result.getValue());
            Assertions.assertEquals(Currency.getInstance("USD"), result.getCurrency());
        }
    }

    @Nested
    class MinusTest {

        @Test
        void shouldSubtractTwoMoneyObjects() {
            Money money1 = new Money(100, Currency.getInstance("USD"));
            Money money2 = new Money(50, Currency.getInstance("USD"));
            Money result = money1.minus(money2);
            Assertions.assertEquals(BigDecimal.valueOf(50), result.getValue());
            Assertions.assertEquals(Currency.getInstance("USD"), result.getCurrency());
        }

        @Test
        void shouldSubtractMultipleMoneyObjects() {
            Money money1 = new Money(100, Currency.getInstance("USD"));
            Money money2 = new Money(50, Currency.getInstance("USD"));
            Money money3 = new Money(25, Currency.getInstance("USD"));
            Money result = money1.minus(money2, money3);
            Assertions.assertEquals(BigDecimal.valueOf(25), result.getValue());
            Assertions.assertEquals(Currency.getInstance("USD"), result.getCurrency());
        }
    }
}
