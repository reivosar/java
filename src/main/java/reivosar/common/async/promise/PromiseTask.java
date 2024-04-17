package reivosar.common.async.promise;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

class PromiseTask<T> {
    private final ConcurrentLinkedQueue<Supplier<T>> suppliers;
    
    PromiseTask() {
        this.suppliers = new ConcurrentLinkedQueue<>();
    }
    
    PromiseTask<T> addTask(final Supplier<T> supplier) {
        return addTask(List.of(supplier));
    }
    
    PromiseTask<T> addTask(final Collection<Supplier<T>> suppliers) {
        this.suppliers.addAll(suppliers);
        return this;
    }
    
    int supplierCount() {
        return suppliers.size();
    }
    
    void forEach(final Consumer<? super Supplier<T>> action) {
        this.suppliers.forEach(action);
    }
}
