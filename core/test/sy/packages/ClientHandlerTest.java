package sy.packages;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.connection.packages.PlayerTurn;
import sy.core.Consumer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest implements Consumer {
    //values
    private NetworkPackageCallbacks callbacks;
    private Client kryonetClient;
    private Consumer<Connection> onConnected;
    private Consumer<Connection> onDisconnected;
    //instance
    private ClientHandler instance;
    @BeforeEach
    void setUp() {
        callbacks = new NetworkPackageCallbacks();
        callbacks.registerCallback(this.getClass(), this);

        kryonetClient = new Client();

        onConnected = this;
        onDisconnected = this;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void defaultConstructor(){
        instance = new ClientHandler();
        assertNotNull(instance);
    }

    @Test
    void callBackConstructor(){
        instance = new ClientHandler(callbacks);
        assertTrue(instance.getCallbacks() == callbacks);
    }

    @Test
    void getCallbacks(){
        instance = new ClientHandler(callbacks);
        assertTrue(instance.getCallbacks() == callbacks);
    }

    @Test
    void clientStartTest() throws IOException {
        instance = new ClientHandler(callbacks);
        //you have to run main methode first for starting server on localhost!!

        assertDoesNotThrow(() -> instance.clientStart("0.0.0.0", 2018,2018));
        instance.close();
        instance.getKryonetClient().dispose();
        instance = null;
        assertTrue(true);
    }

    @Test
    void sendTest(){
        instance = new ClientHandler(callbacks);
        assertDoesNotThrow(() -> {
            instance.clientStart("0.0.0.0", 2018,2018);
            instance.send(new PlayerTurn());
            instance.close();
            instance.getKryonetClient().dispose();
            instance = null;
            assertTrue(true);
        });
    }

    @Override
    public void call(Object o) {

    }
    public static void main(String[] args){
        new ServerHandler().serverStart(2018,2018);
    }
}