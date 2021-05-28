package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class ClientHandler extends Listener {
    private NetworkPackageCallbacks callbacks;
    private Client client;

    public ClientHandler(NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    public NetworkPackageCallbacks getCallbacks() {
        return callbacks;
    }

    public void clientStart(String hostIp, int tcpPort, int udpPort){
        client = new Client();
        client.start();

        Register.register(client.getKryo());

        try {
            client.connect(5000, hostIp, tcpPort, udpPort);
            client.addListener(this);
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
