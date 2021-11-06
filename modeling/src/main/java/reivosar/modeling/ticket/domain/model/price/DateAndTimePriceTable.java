package reivosar.modeling.ticket.domain.model.price;

public interface DateAndTimePriceTable
{
	Price weekdayNormalPrice();

	Price weekdayLatePrice();

	Price holidayNormalPrice();

	Price holidayLatePrice();

	Price weekdayMovieDayPrice();

	Price holidayMovieDayPrice();
}
