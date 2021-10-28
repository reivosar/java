package reivosar.common.domain.model;

import java.util.UUID;

/**
 * A value object representing an identity.
 * 
 * @param <ID>
 */
public abstract class Identity<ID> extends ValueObject<ID>
{
	protected static String genereteNativeIdByUUID () {
		return UUID.randomUUID().toString();
	}

	/**
	 * Return an empty identity object.
	 * 
	 * @param <ID>
	 * @return empty identity object
	 */
	@SuppressWarnings("unchecked")
	public static <ID> ID empty() {
		return (ID) new Identity<ID>() {};
	}
}
