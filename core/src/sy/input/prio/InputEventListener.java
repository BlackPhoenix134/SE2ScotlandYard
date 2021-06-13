package sy.input.prio;


import sy.core.Consumer;

public class InputEventListener {
    private Consumer<InputEvent> callback;
    private int priority;

    public InputEventListener(Consumer<InputEvent> callback, int priority) {
        this.callback = callback;
        this.priority = priority;
    }

    public void invoke(InputEvent event) {
        callback.call(event);
    }

    public int getPriority() {
        return priority;
    }
}
