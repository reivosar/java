package reivosar.common.util.model;

import java.util.UUID;

public abstract class Identity<ID> extends ValueObject<ID>
{
	protected static String genereteNativeIdByUUID () {
		return UUID.randomUUID().toString();
	}

	@SuppressWarnings("unchecked")
	public static <ID> ID empty() {
		return (ID) new Identity<ID>() {};
	}
}
