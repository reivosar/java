package cookpad.career.test.q1.domain.model;

import java.util.Arrays;

import reivosar.common.util.model.ValueObject;

public class CargoWeight extends ValueObject<CargoWeight>
{
	private final Integer value;

	public CargoWeight(final Integer value) {
		this.value = value;
	}

	public CargoWeight sum(final CargoWeight... others) {
		return new CargoWeight(this.value + Arrays.asList(others)
                                                .stream()
                                                .mapToInt(other -> other.value)
                                                .sum());
	}

	public String getAsString() {
		return value.toString();
	}
}
