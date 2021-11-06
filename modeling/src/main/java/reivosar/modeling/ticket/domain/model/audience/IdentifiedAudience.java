package reivosar.modeling.ticket.domain.model.audience;

import reivosar.modeling.ticket.domain.model.audience.identify.AudienceIdentify;

public abstract class IdentifiedAudience<I extends AudienceIdentify, ENTITY extends IdentifiedAudience<I, ENTITY>>
		extends Audience<ENTITY> {

	protected IdentifiedAudience(final I identify) {
		super(identify.getId());
		verify(identify);
	}

	private void verify(final I identify) {
		identify.verify();
	}
}
