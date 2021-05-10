package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;

import sy.connection.Listener.ClientListener;
import sy.connection.packages.request.RequestMovement;
import sy.connection.packages.response.ResponseMovement;

public class ClientStart {
    public static void main(String[] args) {
        //For testing purposes
        System.out.println("Starting client");
        Client client = new Client();
        client.start();
        try {
            client.connect(5000,"192.168.0.4", 54555,54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.addListener(new ClientListener());

        Kryo kryo = client.getKryo();
        kryo.register(RequestMovement.class);
        kryo.register(ResponseMovement.class);
    }


}
