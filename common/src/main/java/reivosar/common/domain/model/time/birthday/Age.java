package reivosar.common.domain.model.time.birthday;

import reivosar.common.util.model.ValueObject;

public class Age extends ValueObject<Age>
{
	final int value;

	public Age(int value) {
		this.value = value;
	}

	public boolean isSame(Age age) {
		return this.value == age.value;
	}

	public boolean isOver(Age age) {
		return this.value > age.value;
	}

	public boolean isLess(Age age) {
		return this.value < age.value;
	}
}
