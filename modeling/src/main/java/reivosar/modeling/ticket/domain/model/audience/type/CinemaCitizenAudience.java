package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.CinemaCitizenIdentify;

public class CinemaCitizenAudience extends IdentifiedAudience<CinemaCitizenIdentify, CinemaCitizenAudience>
{
	final CinemaCitizenIdentify identify;

	public CinemaCitizenAudience(final CinemaCitizenIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.CINEMA_CITIZEN;
	}
}
