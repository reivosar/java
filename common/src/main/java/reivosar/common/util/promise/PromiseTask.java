package reivosar.common.util.promise;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

class PromiseTask<T>
{
	private final ConcurrentLinkedQueue<Supplier<T>> suppliers;

	PromiseTask() {
		this.suppliers = new ConcurrentLinkedQueue<>();
	}

	PromiseTask<T> addTask(final Supplier<T> supplier) {
		return addTask(Arrays.asList(supplier));
	}

	PromiseTask<T> addTask(final Collection<Supplier<T>> suppliers) {
		this.suppliers.addAll(suppliers);
		return this;
	}

	void forEach(final Consumer<? super Supplier<T>> action) {
		this.suppliers.stream().forEach(action);
	}
}
