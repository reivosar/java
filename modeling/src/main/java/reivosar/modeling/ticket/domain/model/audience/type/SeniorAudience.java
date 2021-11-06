package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.SeniorIdentify;

public class SeniorAudience extends IdentifiedAudience<SeniorIdentify, SeniorAudience>
{
	final SeniorIdentify identity;

	public SeniorAudience(final SeniorIdentify identity) {
		super(identity);
		this.identity = identity;
	}

	@Override
	public AudienceType type() {
		return AudienceType.SENIOR;
	}
}
