package reivosar.modeling.ticket.domain.model.audience;

import java.util.Currency;
import java.util.Locale;

import reivosar.common.domain.model.money.Money;
import reivosar.common.domain.model.time.HourMinute;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.modeling.ticket.domain.model.price.Price;
import reivosar.modeling.ticket.domain.model.schedule.ScheduledTime;

abstract class AudienceTestTemplate<T extends Audience<T>>
{
	protected abstract T getAudience();

	private static abstract class ThisTestCaseBehavior
	{
		protected abstract YearMonthDay getYearMonthDay();

		protected ScheduledTime scheduledTime_1959 = new ScheduledTime(getYearMonthDay(), new HourMinute(19, 59));
		protected ScheduledTime scheduledTime_2000 = new ScheduledTime(getYearMonthDay(), new HourMinute(20, 00));
		protected ScheduledTime scheduledTime_2001 = new ScheduledTime(getYearMonthDay(), new HourMinute(20, 01));

		protected Price getAssertionPrice (int price) {
			return new Price(new Money(price, Currency.getInstance(Locale.JAPAN)));
		}

		abstract void 時間が1959の場合();
		abstract void 時間が2000の場合();
		abstract void 時間が2001の場合();
	}

	static abstract class 映画の日_平日 extends ThisTestCaseBehavior {
		@Override
		protected YearMonthDay getYearMonthDay() {
			return YearMonthDay.of(2020, 10, 1);
		}
	}

	static abstract class 映画の日_休日 extends ThisTestCaseBehavior {
		@Override
		protected YearMonthDay getYearMonthDay() {
			return YearMonthDay.of(2020, 3, 1);
		}
	}

	static abstract class 平日 extends ThisTestCaseBehavior {
		@Override
		protected YearMonthDay getYearMonthDay() {
			return YearMonthDay.of(2020, 10, 23);
		}
	}

	static abstract class 週末 extends ThisTestCaseBehavior {
		@Override
		protected YearMonthDay getYearMonthDay() {
			return YearMonthDay.of(2020, 10, 24);
		}
	}

	static abstract class 祝日 extends ThisTestCaseBehavior {
		@Override
		protected YearMonthDay getYearMonthDay() {
			return YearMonthDay.of(2020, 11, 03);
		}
	}

	static abstract class 振替休日 extends ThisTestCaseBehavior {
		@Override
		protected YearMonthDay getYearMonthDay() {
			return YearMonthDay.of(2020, 2, 24);
		}
	}
}

