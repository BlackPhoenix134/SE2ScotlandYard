package sy.packages;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.connection.packages.PlayerTurn;
import sy.core.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class ServerHandlerTest implements Consumer {
    //values
    private NetworkPackageCallbacks callbacks;
    private Consumer<Connection> clientConnected;
    private Consumer<Connection> clientDisconnected;
    //instance
    private ServerHandler instance;
    @BeforeEach
    void setUp() {
        callbacks = new NetworkPackageCallbacks();
        callbacks.registerCallback(this.getClass(), this);

        clientConnected = this;
        clientDisconnected = this;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void defaultConstructor(){
        assertNotNull(new ServerHandler());
    }

    @Test
    void callbacksConstructor(){
        instance = new ServerHandler(callbacks);
        assertTrue(instance.getCallbacks() == callbacks);
    }

    @Test
    void serverStart(){
        instance = new ServerHandler();

        instance.serverStart(2012, 2012);
    }

    @Test
    void sendAllTest(){
        assertDoesNotThrow(() -> {
            ServerHandler serverHandler = new ServerHandler();
            serverHandler.serverStart(2018, 2018);
            serverHandler.sendAll(new PlayerTurn());
        });
    }

    @Test
    void sentToTest(){
        assertDoesNotThrow(() -> {
            ServerHandler serverHandler = new ServerHandler();
            serverHandler.serverStart(2018, 2018);

            serverHandler.sendTo(0, new PlayerTurn());
        });
    }

    @Test
    void sendAllExceptTest(){
        assertDoesNotThrow(() -> {
            ServerHandler serverHandler = new ServerHandler();
            serverHandler.serverStart(2018, 2018);

            serverHandler.sendAllExcept(0, new PlayerTurn());
        });
    }

    @Override
    public void call(Object o) {

    }

    public static void main(String[] args){
        new ServerHandler().serverStart(2018,2018);
    }
}