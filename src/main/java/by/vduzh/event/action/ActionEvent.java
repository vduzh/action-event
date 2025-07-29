package by.vduzh.event.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ActionEvent<K, T> {

    private K id;

    private String action;

    private T payload;

    @Override
    public String toString() {
        return "%s(id=%s, action=%s, payload=%s)".formatted(getClass().getSimpleName(), id, action, payload);
    }
}

