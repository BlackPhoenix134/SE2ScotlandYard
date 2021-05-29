package sy.connection;

import com.esotericsoftware.kryo.Kryo;

import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.CreatePlayer;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.PlayerJoinLobbyRequest;
import sy.connection.packages.SpawnObject;
import sy.connection.packages.PlayerTurn;
import sy.connection.packages.RemoveTicket;
import sy.connection.packages.UpdateTickets;

public class PackageRegisterer {
    public static void register(Kryo kryo){
        kryo.register(ClientMoveRequest.class);
        kryo.register(MovePlayerObject.class);
        kryo.register(SpawnObject.class);
        kryo.register(UpdateTickets.class);
        kryo.register(RemoveTicket.class);
        kryo.register(PlayerTurn.class);
        kryo.register(PlayerJoinLobbyRequest.class);
        kryo.register(CreatePlayer.class);
    }
}
