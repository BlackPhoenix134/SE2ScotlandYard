package sy.core.clickHandling;

public class ObjectClickBinding {
    private ObjectClickInformation objectClickInformation;
    private ObjectClickHandler objectClickHandler;

    public ObjectClickBinding(ObjectClickInformation objectClickInformation, ObjectClickHandler objectClickHandler) {
        this.objectClickInformation = objectClickInformation;
        this.objectClickHandler = objectClickHandler;
    }

    ObjectClickInformation getObjectClickInformation() {
        return objectClickInformation;
    }

    public void unsubscribe() {
        objectClickHandler.unsubscribe(this);
    }
}
