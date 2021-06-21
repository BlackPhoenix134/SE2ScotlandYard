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
        //packageHandler.put(PlayerTurn.class, packageObject->{

        //});
    }

    public void invoke(Object packageObj) {
        Consumer<Object> consumer = packageHandler.get(packageObj.getClass());
        if(consumer != null)
           consumer.call(packageObj);
    }

    public void registerCallback(Class c, Consumer<Object> consumer){
        packageHandler.put(c, consumer);
    }
}
