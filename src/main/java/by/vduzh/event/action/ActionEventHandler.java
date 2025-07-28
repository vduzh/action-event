package by.vduzh.event.action;

public interface ActionEventHandler<T, E extends ActionEvent<T>> {
    boolean supports(String action);

    void handle(E event);
}
