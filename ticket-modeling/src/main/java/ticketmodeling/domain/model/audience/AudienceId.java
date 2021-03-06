package ticketmodeling.domain.model.audience;

import reivosar.common.util.model.Identity;

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
