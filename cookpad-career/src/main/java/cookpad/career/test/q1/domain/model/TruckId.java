package cookpad.career.test.q1.domain.model;

import reivosar.common.util.model.Identity;

public class TruckId extends Identity<TruckId>
{
	private final Integer value;

	public TruckId(Integer value) {
		this.value = value;
	}

	public String getAsString() {
		return value.toString();
	}
}
