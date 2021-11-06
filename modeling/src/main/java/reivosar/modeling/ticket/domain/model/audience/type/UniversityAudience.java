package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.UniversityIdentify;

public class UniversityAudience extends IdentifiedAudience<UniversityIdentify, UniversityAudience> {

	final UniversityIdentify identify;

	public UniversityAudience(final UniversityIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.UNIVERSITY;
	}
}
