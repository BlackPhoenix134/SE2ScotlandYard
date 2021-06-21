package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageRegistererTest {
    Kryo instance = new Kryo();
    @BeforeEach
    void setUp() {
    }

    @Test
    void generalTest(){
        assertDoesNotThrow(()->{
            PackageRegisterer.register(instance);
        });
    }

    @AfterEach
    void tearDown() {
    }
}