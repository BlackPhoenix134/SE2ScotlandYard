package sy.connection;

import java.util.HashMap;
import java.util.Map;

import sy.core.Consumer;

public class NetworkPackageCallbacks {
    private Map<Class, Consumer<Object>> packageHandler = new HashMap<>();

    public NetworkPackageCallbacks() {
        setupHandler();
    }

    private void setupHandler() {

    }

    public void invoke(Object packageObj) {
        packageHandler.get(packageObj.getClass()).call(packageObj);
    }
}
