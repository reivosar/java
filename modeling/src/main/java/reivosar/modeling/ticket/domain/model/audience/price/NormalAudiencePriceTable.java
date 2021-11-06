package reivosar.modeling.ticket.domain.model.audience.price;

import reivosar.modeling.ticket.domain.model.price.DateAndTimePriceTableTemplate;

public class NormalAudiencePriceTable extends DateAndTimePriceTableTemplate
{
	@Override
	protected int weekdayNormalPrimitivePrice() {
		return 1800;
	}

	@Override
	protected int weekdayLatePrimitivePrice() {
		return 1300;
	}

	@Override
	protected int holidayNormalPrimitivePrice() {
		return 1800;
	}

	@Override
	protected int holidayLatePrimitivePrice() {
		return 1300;
	}

	@Override
	protected int weekdayMovieDayPrimitivePrice() {
		return 1100;
	}
}
