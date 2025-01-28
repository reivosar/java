package reivosar.common.async.event;

import reivosar.common.lang.ObjectUtil;

/**
 * An abstract implementation of {@link EventPublisherTemplate} that provides
 * event publishing functionality by dispatching events using an {@link EventDispatcher}.
 * <p>
 * This class defines the {@link #doPublishEvent(EventConfig, Event)} method to publish events
 * by delegating them to the {@link EventDispatcher} obtained from the {@link EventConfig}.
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 */
public abstract class DispatchEventPublisherTemplate<E extends Event> extends EventPublisherTemplate<E> {

    /**
     * Creates a new instance of {@code DispatchEventPublisherTemplate}.
     *
     * @param eventConfig the event configuration to be used for publishing events
     * @throws IllegalArgumentException if {@code eventConfig} is null
     */
    protected DispatchEventPublisherTemplate(final EventConfig<E> eventConfig) {
        super(eventConfig);
    }

    /**
     * Publishes a single event by dispatching it using the {@link EventDispatcher}.
     * <p>
     * This method retrieves the {@link EventDispatcher} from the provided {@link EventConfig}
     * and invokes its {@code dispatch} method with the specified event.
     *
     * @param eventConfig the configuration used to obtain the {@link EventDispatcher}
     * @param event       the event to be dispatched
     * @throws NullPointerException if {@code eventConfig} or {@code event} is null
     */
    @Override
    protected final void doPublishEvent(final EventConfig<E> eventConfig, final E event) {
        ObjectUtil.requireNonNull("eventConfig", eventConfig);
        ObjectUtil.requireNonNull("event", event);
        eventConfig.getEventDispatcher().dispatch(event);
    }
}
