package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;

import sy.connection.Listener.ServerListener;
import sy.connection.packages.request.RequestMovement;
import sy.connection.packages.response.ResponseMovement;

public class ServerStart{

    public static void main(String[] args) {
        //For testing purposes
        System.out.println("Starting server");
        Server server = new Server();
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new ServerListener());



        Kryo kryo = server.getKryo();
        kryo.register(ResponseMovement.class);
        kryo.register(RequestMovement.class);

    }




}
