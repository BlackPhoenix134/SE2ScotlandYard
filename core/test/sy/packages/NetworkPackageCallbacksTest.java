package sy.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sy.core.Consumer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NetworkPackageCallbacksTest implements Consumer {
    //values
    //no values
    //instance
    private NetworkPackageCallbacks instance;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerCallbackTest(){
        instance = new NetworkPackageCallbacks();
        assertDoesNotThrow(()->{
            instance.registerCallback(this.getClass(), this);
        });
    }

    @Test
    void invokeTest(){
        instance = new NetworkPackageCallbacks();
        assertDoesNotThrow(()->{
            instance.registerCallback(this.getClass(), this);
            instance.invoke(this);
        });
    }

    @Override
    public void call(Object o) {

    }
}