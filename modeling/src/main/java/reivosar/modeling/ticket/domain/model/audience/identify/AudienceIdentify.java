package reivosar.modeling.ticket.domain.model.audience.identify;

import reivosar.modeling.ticket.domain.model.audience.AudienceId;

public interface AudienceIdentify
{
	public void verify();

	public AudienceId getId();
}
