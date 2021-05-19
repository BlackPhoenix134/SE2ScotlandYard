package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.request.PlayerMovement;

public class ClientHandler extends Listener {
    private NetworkPackageCallbacks callbacks;
    private Client client;

    public ClientHandler(NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    public void clientStart(String hostIp, int tcpPort, int udpPort){
        client = new Client();
        client.start();

        Kryo kryo = client.getKryo();
        //kryo.register(SomeRequest.class);
        //kryo.register(SomeResponse.class);
        kryo.register(PlayerMovement.class);

        try {
            client.connect(5000, hostIp, tcpPort, udpPort);
            client.addListener(this);
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


    public void send(Object object){
        send(object, false);
    }

    public void send(Object object, boolean invokeSelf){
        if(invokeSelf)
            callbacks.invoke(object);
        client.sendTCP(object);
    }


    @Override
    public void received(Connection connection, Object object) {
        callbacks.invoke(object);
    }

    public void close(){
        client.close();
    }
}
