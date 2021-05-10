package sy.connection.Listener;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import sy.connection.packages.request.RequestMovement;
import sy.connection.packages.response.ResponseMovement;

public class ServerListener extends Listener {
    public void connected (Connection connection) {
    }

    public void disconnected (Connection connection) {
    }


    public void received (Connection connection, Object object) {
        if (object instanceof RequestMovement){
            RequestMovement requestMove = (RequestMovement) object;
            ResponseMovement responseMove = new ResponseMovement();
            connection.sendTCP(responseMove);
        }
    }

}

