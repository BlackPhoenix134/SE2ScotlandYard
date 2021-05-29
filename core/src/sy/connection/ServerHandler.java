package sy.connection;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.CreatePlayer;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.PlayerJoinLobbyRequest;
import sy.connection.packages.SpawnObject;
import sy.core.Consumer;

public class ServerHandler extends Listener{
    private NetworkPackageCallbacks callbacks;
    private Server kryonetServer;
    private Consumer<Connection> clientConnected;
    private Consumer<Connection> clientDisconnected;

    public ServerHandler() {
    }


    public ServerHandler(NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
    }


    public void serverStart(int tcpPort, int udpPort) {

        kryonetServer = new Server();
        Kryo kryo = kryonetServer.getKryo();
        kryo.register(ClientMoveRequest.class);
        kryo.register(MovePlayerObject.class);
        kryo.register(SpawnObject.class);
        kryo.register(PlayerJoinLobbyRequest.class);
        kryo.register(CreatePlayer.class);
        kryonetServer.addListener(this);

        try {
            kryonetServer.start();
            kryonetServer.bind(tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }



    }

    public void setClientConnected(Consumer<Connection> clientConnected) {
        this.clientConnected = clientConnected;
    }

    public void setClientDisconnected(Consumer<Connection> clientDisconnected) {
        this.clientDisconnected = clientDisconnected;
    }

    public NetworkPackageCallbacks getCallbacks() {
        return callbacks;
    }

    public void sendAll(Object obj) {
        sendAll(obj, false);
    }

    public void sendAll(Object obj, boolean invokeSelf) {
        if(invokeSelf)
            callbacks.invoke(obj);
        kryonetServer.sendToAllTCP(obj);
    }


    public void sendTo(int connectionId, Object obj) {
        sendTo(connectionId, obj, false);
    }

    public void sendTo(int connectionId, Object obj, boolean invokeSelf) {
        if(invokeSelf)
            callbacks.invoke(obj);
        kryonetServer.sendToTCP(connectionId, obj);
    }

    public void sendAllExcept(int connectionId, Object obj) {
        sendAllExcept(connectionId, obj, false);
    }

    public void sendAllExcept(int connectionId, Object obj, boolean invokeSelf) {
        if(invokeSelf)
            callbacks.invoke(obj);
        kryonetServer.sendToAllExceptTCP(connectionId, obj);
    }

    @Override
    public void connected(Connection connection) {
        Gdx.app.log("SERVER", "ID: " + connection.getID() + " connected!");
        if(clientConnected != null)
            clientConnected.call(connection);
    }

    @Override
    public void disconnected(Connection connection) {
        Gdx.app.log("SERVER", "ID: " + connection.getID() + " disconnected!");
        if(clientDisconnected != null)
            clientDisconnected.call(connection);
    }

    @Override
    public void received(Connection connection, Object object) {
        callbacks.invoke(object);
    }

    public Server getKryonetServer() {
        return this.kryonetServer;
    }
}
