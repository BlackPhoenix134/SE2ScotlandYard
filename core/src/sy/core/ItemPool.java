package sy.core;

public interface ItemPool<T extends ItemPoolObject> {
    void returnToPool(ItemPoolObject itemPoolObject);
    T get();
}
