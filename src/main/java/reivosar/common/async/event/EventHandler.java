package reivosar.common.async.event;

abstract class EventHandler<E extends Event> {

    abstract void handle(E event);
}
