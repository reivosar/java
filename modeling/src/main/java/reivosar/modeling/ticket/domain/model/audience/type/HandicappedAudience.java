package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.HandicappedIdentify;

public class HandicappedAudience extends IdentifiedAudience<HandicappedIdentify, HandicappedAudience>
{
	final HandicappedIdentify identify;

	public HandicappedAudience(final HandicappedIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.HANDICAPPED;
	}
}
