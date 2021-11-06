package reivosar.modeling.ticket.domain.model.audience;

import reivosar.common.domain.model.Identity;

public class AudienceId extends Identity<AudienceId>
{
	final String value;

	public AudienceId(final String value) {
		this.value = value;
	}

	public static AudienceId genereteId() {
		return new AudienceId(genereteNativeIdByUUID());
	}
}
