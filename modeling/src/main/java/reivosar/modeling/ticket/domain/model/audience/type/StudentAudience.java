package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.StudentIdentify;

public class StudentAudience extends IdentifiedAudience<StudentIdentify, StudentAudience> {

	final StudentIdentify identify;

	public StudentAudience(final StudentIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.STUDENT;
	}
}
