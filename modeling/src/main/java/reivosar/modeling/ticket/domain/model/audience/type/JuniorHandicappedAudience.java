package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.JuniorHandicappedIdentify;

public class JuniorHandicappedAudience extends IdentifiedAudience<JuniorHandicappedIdentify,JuniorHandicappedAudience>
{
	final JuniorHandicappedIdentify identify;

	public JuniorHandicappedAudience(final JuniorHandicappedIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.JUNIOR_HANDICAPPED;
	}
}
