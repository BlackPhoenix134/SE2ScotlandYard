package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.SpawnObject;
import sy.core.Consumer;

public class ClientHandler extends Listener {
    private NetworkPackageCallbacks callbacks;
    private Client kryonetClient;
    private Consumer<Connection> onConnected;
    private Consumer<Connection> onDisconnected;

    public ClientHandler(NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    public NetworkPackageCallbacks getCallbacks() {
        return callbacks;
    }

    public Client getKryonetClient() {
        return kryonetClient;
    }

    public void clientStart(String hostIp, int tcpPort, int udpPort){
        kryonetClient = new Client();
        kryonetClient.start();

        Kryo kryo = kryonetClient.getKryo();
        kryo.register(ClientMoveRequest.class);
        kryo.register(MovePlayerObject.class);
        kryo.register(SpawnObject.class);

        try {
            kryonetClient.connect(5000, hostIp, tcpPort, udpPort);
            kryonetClient.addListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(Object object){
        send(object, false);
    }

    public void send(Object object, boolean invokeSelf){
        if(invokeSelf)
            callbacks.invoke(object);
        kryonetClient.sendTCP(object);
    }


    @Override
    public void received(Connection connection, Object object) {
        callbacks.invoke(object);
    }

    @Override
    public void connected(Connection connection) {
        super.connected(connection);
        if(onConnected != null)
            onConnected.call(connection);
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        if(onDisconnected != null)
            onDisconnected.call(connection);
    }


    public void close(){
        kryonetClient.close();
    }
}
