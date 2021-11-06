package reivosar.modeling.ticket.domain.model.audience.identify;

import reivosar.common.domain.model.time.birthday.Age;
import reivosar.common.domain.model.time.birthday.BirthDay;
import reivosar.common.domain.model.ValueObject;
import reivosar.modeling.ticket.domain.model.audience.AudienceId;

public class ChildIdentify extends ValueObject<ChildIdentify>
	implements AudienceIdentify
{
	final BirthDay birthDay;

	public ChildIdentify(final BirthDay birthDay) {
		this.birthDay = birthDay;
	}

	public AudienceId getId() {
		return AudienceId.genereteId();
	}

	@Override
	public void verify() {
		if (birthDay.isOverAge(new Age(12))) {
			throw new AudienceIdentifyException("It's no older than grade school.");
		}
	}
}
