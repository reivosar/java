package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.SeniorCinemaCitizenIdentify;

public class SeniorCinemaCitizenAudience extends IdentifiedAudience<SeniorCinemaCitizenIdentify, SeniorCinemaCitizenAudience>
{
	final SeniorCinemaCitizenIdentify identify;

	public SeniorCinemaCitizenAudience(final SeniorCinemaCitizenIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.SENIOR_CINEMA_CITIZEN;
	}
}
