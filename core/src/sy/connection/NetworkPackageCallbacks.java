package sy.connection;


import java.util.HashMap;
import java.util.Map;

import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.request.SomeRequest;
import sy.core.Consumer;

public class NetworkPackageCallbacks {
    private Map<Class, Consumer<Object>> packageHandler = new HashMap<>();

    public NetworkPackageCallbacks() {
        setupHandler();
    }

    private void setupHandler() {
        packageHandler.put(MovePlayerObject.class, packageObject->{

        });

        packageHandler.put(ClientMoveRequest.class, packageObject->{

        });
    }

    public void invoke(Object packageObj) {
        packageHandler.get(packageObj.getClass()).call(packageObj);
    }
}
