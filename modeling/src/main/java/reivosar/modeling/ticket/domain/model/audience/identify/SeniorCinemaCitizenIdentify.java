package reivosar.modeling.ticket.domain.model.audience.identify;

import java.security.cert.Certificate;

public class SeniorCinemaCitizenIdentify extends CertificateAudienceIdentify<SeniorCinemaCitizenIdentify>
{
	public SeniorCinemaCitizenIdentify(final Certificate certificate) {
		super(certificate);
	}
}
