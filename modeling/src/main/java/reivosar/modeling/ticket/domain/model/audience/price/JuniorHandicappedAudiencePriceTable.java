package reivosar.modeling.ticket.domain.model.audience.price;

import reivosar.modeling.ticket.domain.model.price.DateAndTimePriceTableTemplate;

public class JuniorHandicappedAudiencePriceTable extends DateAndTimePriceTableTemplate
{
	@Override
	protected int weekdayNormalPrimitivePrice() {
		return 900;
	}

	@Override
	protected int weekdayLatePrimitivePrice() {
		return 900;
	}

	@Override
	protected int holidayNormalPrimitivePrice() {
		return 900;
	}

	@Override
	protected int holidayLatePrimitivePrice() {
		return 900;
	}

	@Override
	protected int weekdayMovieDayPrimitivePrice() {
		return 900;
	}
}

