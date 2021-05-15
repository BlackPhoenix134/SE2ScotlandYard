package sy.connection;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import sy.connection.packages.request.PlayerMovement;

public class ServerHandler extends Listener{

    static Server server;

    public void serverStart(){

        try {
            server = new Server();
            server.start();
            server.bind(54555,54777);
            server.addListener(new ServerHandler());
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

     /*   Kryo kryo = server.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);*/
    }

    @Override
    public void connected(Connection connection) {
        Gdx.app.log("SERVER", connection.getRemoteAddressTCP().getHostName() + " connected!");
    }

    @Override
    public void disconnected(Connection connection) {
        Gdx.app.log("SERVER", connection.getRemoteAddressTCP().getHostName() + " disconnected!");
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof PlayerMovement){
            PlayerMovement playerMovement = (PlayerMovement) object;
            server.sendToAllExceptTCP(connection.getID(),playerMovement); //Send to all TCP except the one who sent it
        }
    }

}
