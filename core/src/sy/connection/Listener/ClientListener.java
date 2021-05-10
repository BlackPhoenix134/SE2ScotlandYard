package sy.connection.Listener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import sy.connection.packages.request.RequestMovement;
import sy.connection.packages.response.ResponseMovement;

public class ClientListener extends Listener {
    public void connected(Connection connection) {
    }

    public void disconnected(Connection connection) {
    }


    public void received(Connection connection, Object object) {
        if (object instanceof ResponseMovement) {
            ResponseMovement responseMove = (ResponseMovement) object;
            RequestMovement requestMove = new RequestMovement();
            connection.sendTCP(requestMove);
        }

    }
}
