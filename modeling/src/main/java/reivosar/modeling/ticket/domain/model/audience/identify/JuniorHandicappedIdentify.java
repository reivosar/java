package reivosar.modeling.ticket.domain.model.audience.identify;

import java.security.cert.Certificate;

public class JuniorHandicappedIdentify extends CertificateAudienceIdentify<JuniorHandicappedIdentify>
{
	public JuniorHandicappedIdentify(final Certificate certificate) {
		super(certificate);
	}
}
