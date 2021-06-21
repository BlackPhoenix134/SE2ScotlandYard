package sy.core;

@FunctionalInterface
public interface Function<T, R> {
    R call(T t);
}