package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

import sy.connection.packages.request.PlayerMovement;
import sy.connection.packages.request.SomeRequest;
import sy.connection.packages.response.SomeResponse;
import sy.screens.GameScreen;
import sy.screens.ScreenManager;

public class ClientHandler extends Listener {

    static Client client;

    public void clientStart(){
        client = new Client();
        client.start();

        Kryo kryo = client.getKryo();
        //kryo.register(SomeRequest.class);
        //kryo.register(SomeResponse.class);
        kryo.register(PlayerMovement.class);

        try {
            client.connect(5000, "192.168.0.136", 54555, 54777);
            client.addListener(new ClientHandler());

        } catch (IOException e) {
            e.printStackTrace();
        }

       /* SomeRequest request = new SomeRequest();
        request.text = "Here is the request";
        client.sendTCP(request);*/

       /* client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse)object;
                    System.out.println(response.text);
                }
            }
        });*/

    }

    public static void send(Object object){
        if(object instanceof PlayerMovement){
            PlayerMovement playerMovement = (PlayerMovement) object;
            client.sendTCP(playerMovement);
        }
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof PlayerMovement){
            //Update map?
        }
    }

    public void close(){
        client.close();
    }
}
