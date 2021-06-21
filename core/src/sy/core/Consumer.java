package sy.core;

@FunctionalInterface
public interface Consumer<T> {
    void call(T t);
}