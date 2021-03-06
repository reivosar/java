package ticketmodeling.domain.model.audience;

import ticketmodeling.domain.model.audience.price.ChildAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.CinemaCitizenAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.HandicappedAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.JuniorHandicappedAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.NormalAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.SeniorAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.SeniorCinemaCitizenAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.StudentAudiencePriceTable;
import ticketmodeling.domain.model.audience.price.UniversityAudiencePriceTable;
import ticketmodeling.domain.model.price.DateAndTimePriceTable;

public enum AudienceType
{
	CINEMA_CITIZEN("シネマシティズン") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new CinemaCitizenAudiencePriceTable();
		}
	},
	SENIOR_CINEMA_CITIZEN("シネマシティズン（60才以上）") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new SeniorCinemaCitizenAudiencePriceTable();
		}
	},
	NORMAL("一般") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new NormalAudiencePriceTable();
		}
	},
	SENIOR("シニア（70才以上）") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new SeniorAudiencePriceTable();
		}
	},
	UNIVERSITY("学生（大・専）") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new UniversityAudiencePriceTable();
		}
	},
	STUDENT("中・高校生") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new StudentAudiencePriceTable();
		}
	},
	CHILD("幼児（3才以上）・小学生") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new ChildAudiencePriceTable();
		}
	},
	HANDICAPPED("障がい者（学生以上）") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new HandicappedAudiencePriceTable();
		}
	},
	JUNIOR_HANDICAPPED("障がい者（高校以下）") {
		@Override
		public DateAndTimePriceTable priceTable() {
			return new JuniorHandicappedAudiencePriceTable();
		}
	},
	;

	private final String label;

	private AudienceType(final String label) {
		this.label = label;
	}

	public String label() {
		return label;
	}

	public abstract DateAndTimePriceTable priceTable();
}
