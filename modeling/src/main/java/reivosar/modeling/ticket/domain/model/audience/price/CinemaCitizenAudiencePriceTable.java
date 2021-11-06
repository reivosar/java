package reivosar.modeling.ticket.domain.model.audience.price;

import reivosar.modeling.ticket.domain.model.price.DateAndTimePriceTableTemplate;

public class CinemaCitizenAudiencePriceTable extends DateAndTimePriceTableTemplate
{
	@Override
	protected int weekdayNormalPrimitivePrice() {
		return 1000;
	}

	@Override
	protected int weekdayLatePrimitivePrice() {
		return 1000;
	}

	@Override
	protected int holidayNormalPrimitivePrice() {
		return 1300;
	}

	@Override
	protected int holidayLatePrimitivePrice() {
		return 1000;
	}

	@Override
	protected int weekdayMovieDayPrimitivePrice() {
		return 1100;
	}

	@Override
	protected int holidayMovieDayPrimitivePrice() {
		return 1000;
	}
}

