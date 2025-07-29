package by.vduzh.event.action;

public interface ActionEventHandler<K, T, E extends ActionEvent<K, T>> {
    boolean supports(String action);

    void handle(E event);
}
