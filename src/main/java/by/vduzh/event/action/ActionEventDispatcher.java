package by.vduzh.event.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ActionEventDispatcher {
    private final List<? extends ActionEventHandler<?, ?>> handlers;

    public ActionEventDispatcher(List<? extends ActionEventHandler<?, ?>> handlers) {
        this.handlers = handlers;
    }

    @SuppressWarnings("unchecked")
    public void dispatch(ActionEvent<?> event) {
        handlers.stream()
                .filter(handler -> handler.supports(event.getAction()))
                .findFirst()
                .ifPresentOrElse(
                        handler -> ((ActionEventHandler<Object, ActionEvent<Object>>) handler).handle((ActionEvent<Object>) event),
                        () -> log.debug("There is no handler for action: {}", event.getAction())
                );
    }
}
