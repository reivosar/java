package reivosar.modeling.ticket.domain.model.audience;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import reivosar.modeling.ticket.domain.model.audience.identify.CinemaCitizenIdentify;
import reivosar.modeling.ticket.domain.model.audience.type.CinemaCitizenAudience;

class CinemaCitizenAudienceTest extends AudienceTestTemplate<CinemaCitizenAudience> {

	@Override
	protected CinemaCitizenAudience getAudience() {
		return new CinemaCitizenAudience(new CinemaCitizenIdentify(new DummyCertificate()));
	}

	@Nested
	class 映画の日_平日 extends AudienceTestTemplate.映画の日_平日 {
		@Test
		void 時間が1959の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_1959), is(getAssertionPrice(1100)));
		}
		@Test
		void 時間が2000の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2000), is(getAssertionPrice(1100)));
		}
		@Test
		void 時間が2001の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2001), is(getAssertionPrice(1100)));
		}
	}
	@Nested
	class 映画の日_休日 extends AudienceTestTemplate.映画の日_休日 {
		@Test
		void 時間が1959の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_1959), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2000の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2000), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2001の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2001), is(getAssertionPrice(1000)));
		}
	}
	@Nested
	class 平日 extends AudienceTestTemplate.平日 {
		@Test
		void 時間が1959の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_1959), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2000の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2000), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2001の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2001), is(getAssertionPrice(1000)));
		}
	}
	@Nested
	class 週末 extends AudienceTestTemplate.週末 {
		@Test
		void 時間が1959の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_1959), is(getAssertionPrice(1300)));
		}
		@Test
		void 時間が2000の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2000), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2001の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2001), is(getAssertionPrice(1000)));
		}
	}
	@Nested
	class 祝日 extends AudienceTestTemplate.祝日 {
		@Test
		void 時間が1959の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_1959), is(getAssertionPrice(1300)));
		}
		@Test
		void 時間が2000の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2000), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2001の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2001), is(getAssertionPrice(1000)));
		}
	}
	@Nested
	class 振替休日 extends AudienceTestTemplate.振替休日 {
		@Test
		void 時間が1959の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_1959), is(getAssertionPrice(1300)));
		}
		@Test
		void 時間が2000の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2000), is(getAssertionPrice(1000)));
		}
		@Test
		void 時間が2001の場合() {
			assertThat(getAudience().calcScreenPrice(scheduledTime_2001), is(getAssertionPrice(1000)));
		}
	}

}
