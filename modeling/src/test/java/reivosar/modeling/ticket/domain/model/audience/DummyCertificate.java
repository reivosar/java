package reivosar.modeling.ticket.domain.model.audience;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

public class DummyCertificate extends Certificate {

	protected DummyCertificate() {
		super("dummy");
	}

	@Override
	public byte[] getEncoded() throws CertificateEncodingException {
		return null;
	}

	@Override
	public PublicKey getPublicKey() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException,
			NoSuchProviderException, SignatureException {
	}

	@Override
	public void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException,
			InvalidKeyException, NoSuchProviderException, SignatureException {
	}
}
