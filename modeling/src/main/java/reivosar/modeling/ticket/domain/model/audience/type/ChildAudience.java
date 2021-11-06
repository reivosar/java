package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.AudienceType;
import reivosar.modeling.ticket.domain.model.audience.IdentifiedAudience;
import reivosar.modeling.ticket.domain.model.audience.identify.ChildIdentify;

public class ChildAudience extends IdentifiedAudience<ChildIdentify, ChildAudience>
{
	final ChildIdentify identify;

	public ChildAudience(final ChildIdentify identify) {
		super(identify);
		this.identify = identify;
	}

	@Override
	public AudienceType type() {
		return AudienceType.CHILD;
	}
}
