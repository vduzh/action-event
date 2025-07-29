package by.vduzh.event.action;

import by.vduzh.event.action.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class ActionEventDispatcherTest {

    @MockitoBean
    private ActionEventHandler<String, ActionEvent<String>> stringHandler;

    @MockitoBean
    private ActionEventHandler<Integer, ActionEvent<Integer>> integerHandler;

    @Autowired
    private ActionEventDispatcher dispatcher;

    @Test
    void shouldHandleActionEvent() {
        // Given - mock event
        var event = mock(ActionEvent.class);
        doReturn("string.action").when(event).getAction();

        // Given - mock string handler
        doReturn(true).when(stringHandler).supports("string.action");
        doNothing().when(stringHandler).handle(any());
        // Given - mock integer handler
        doReturn(false).when(integerHandler).supports("string.action");

        // When
        dispatcher.dispatch(event);

        // Then
        verify(stringHandler, times(1)).handle(any());
        verify(integerHandler, never()).handle(any());
    }

    @Test
    void shouldLogDebugWhenActionIsUnknown() {
        // Given - mock event
        var event = mock(ActionEvent.class);
        doReturn("dummy").when(event).getAction();

        // When - should not throw exception, just log debug
        dispatcher.dispatch(event);

        // Then - no exception thrown, just debug log
        verify(stringHandler, never()).handle(any());
        verify(integerHandler, never()).handle(any());
    }
}