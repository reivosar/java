package reivosar.modeling.ticket.domain.model.audience.identify;

import java.security.cert.Certificate;

public class UniversityIdentify extends CertificateAudienceIdentify<UniversityIdentify>
{
	public UniversityIdentify(final Certificate certificate) {
		super(certificate);
	}
}
