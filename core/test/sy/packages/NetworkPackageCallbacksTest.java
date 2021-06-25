package sy.packages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sy.connection.NetworkPackageCallbacks;
import static org.junit.jupiter.api.Assertions.*;

class NetworkPackageCallbacksTest {
    private NetworkPackageCallbacks instance;

    @BeforeEach
    void setUp() {
        instance = new NetworkPackageCallbacks();
        instance.registerCallback(Package1.class, pckg -> {
            assertEquals(pckg.getClass(), Package1.class);
        });
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerTest(){
        assertDoesNotThrow(() -> instance.registerCallback(Package2.class, pckg -> { }));
    }

    @Test
    void invokeTest(){
        instance.invoke(new Package1());
    }

    @Test
    void invokeUnregisteredTest(){
        assertDoesNotThrow(() ->  instance.invoke(new Package2()));
    }

    private class Package1 {

    }

    private class Package2 {

    }
}