package reivosar.modeling.ticket.domain.model.audience.identify;

import java.security.cert.Certificate;

public class HandicappedIdentify extends CertificateAudienceIdentify<HandicappedIdentify>
{
	public HandicappedIdentify(final Certificate certificate) {
		super(certificate);
	}
}
