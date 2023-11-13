package christmas.domain;

import java.util.List;

public class EventHandler {
    private final List<Event> events;
    private final OrderMenu orderMenu;

    public EventHandler(List<Event> events, OrderMenu orderMenu) {
        this.events = events;
        this.orderMenu = orderMenu;
    }
}
