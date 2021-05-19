package sy.connection;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.request.PlayerMovement;

public class ServerHandler extends Listener{
    private NetworkPackageCallbacks callbacks;
    private Server server;

    public ServerHandler(NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
    }


    public void serverStart(int tcpPort, int udpPort) {

        try {
            server = new Server();
            server.start();
            server.bind(tcpPort,udpPort);
            server.addListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* server.addListener(new Listener(){
            public void received (Connection connection, Object object){
                SomeRequest request = (SomeRequest)object;
                System.out.println(request.text);

                SomeResponse response = new SomeResponse();
                response.text = "Thanks";
                connection.sendTCP(response);
            }
        });*/

        Kryo kryo = server.getKryo();
        //kryo.register(SomeRequest.class);
        //kryo.register(SomeResponse.class);
        kryo.register(PlayerMovement.class);

    }


    public void sendAll(Object obj) {
        sendAll(obj, false);
    }

    public void sendAll(Object obj, boolean invokeSelf) {
        if(invokeSelf)
            callbacks.invoke(obj);
        server.sendToAllTCP(obj);
    }


    public void sendTo(int connectionId, Object obj) {
        sendTo(connectionId, obj, false);
    }

    public void sendTo(int connectionId, Object obj, boolean invokeSelf) {
        if(invokeSelf)
            callbacks.invoke(obj);
        server.sendToTCP(connectionId, obj);
    }

    public void sendAllExcept(int connectionId, Object obj) {
        sendAllExcept(connectionId, obj, false);
    }

    public void sendAllExcept(int connectionId, Object obj, boolean invokeSelf) {
        if(invokeSelf)
            callbacks.invoke(obj);
        server.sendToAllExceptTCP(connectionId, obj);
    }

    @Override
    public void connected(Connection connection) {
        Gdx.app.log("SERVER", "ID: " + connection.getID() + " connected!");
    }

    @Override
    public void disconnected(Connection connection) {
        Gdx.app.log("SERVER", "ID: " + connection.getID() + " disconnected!");
    }

    @Override
    public void received(Connection connection, Object object) {
        callbacks.invoke(object);
    }

}
