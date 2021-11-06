package reivosar.modeling.ticket.domain.model.audience.type;

import reivosar.modeling.ticket.domain.model.audience.Audience;
import reivosar.modeling.ticket.domain.model.audience.AudienceId;
import reivosar.modeling.ticket.domain.model.audience.AudienceType;

public class NormalAudience extends Audience<NormalAudience>
{
	public NormalAudience() {
		super(AudienceId.genereteId());
	}

	@Override
	public AudienceType type() {
		return AudienceType.NORMAL;
	}
}
