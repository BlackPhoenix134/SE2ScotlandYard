package sy.connection;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

import sy.core.Consumer;

public class ClientHandler extends Listener {
    private NetworkPackageCallbacks callbacks;
    private Client kryonetClient;
    private Consumer<Connection> onConnected;
    private Consumer<Connection> onDisconnected;

    public ClientHandler() {
    }

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
        Kryo kryo = kryonetClient.getKryo();
        PackageRegisterer.register(kryo);
        kryonetClient.addListener(this);

        try {
            kryonetClient.start();
            kryonetClient.connect(5000, hostIp, tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
            Gdx.app.exit();
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
