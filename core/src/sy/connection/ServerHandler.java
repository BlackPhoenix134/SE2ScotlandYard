package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import sy.connection.packages.request.SomeRequest;
import sy.connection.packages.response.SomeResponse;

public class ServerHandler {

    public void serverStart(){

        Server server = new Server();
        server.start();
        try {
            server.bind(54555,54777);
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
}
