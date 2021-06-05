package sy.connection;

import com.esotericsoftware.kryo.Kryo;

import sy.connection.packages.AddPawnObject;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.CreateLobbyPlayer;
import sy.connection.packages.GameplayReady;
import sy.connection.packages.LobbyPlayerReady;
import sy.connection.packages.LobbyPlayerReadySync;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.PlayerJoinLobbyRequest;
import sy.connection.packages.SpawnObject;
import sy.connection.packages.PlayerTurn;
import sy.connection.packages.RemoveTicket;
import sy.connection.packages.LobbyToStartGame;
import sy.connection.packages.TransitionScene;
import sy.core.Tickets.TicketType;

public class PackageRegisterer {
    public static void register(Kryo kryo){
        kryo.register(ClientMoveRequest.class);
        kryo.register(MovePlayerObject.class);
        kryo.register(SpawnObject.class);
        kryo.register(RemoveTicket.class);
        kryo.register(PlayerTurn.class);
        kryo.register(PlayerJoinLobbyRequest.class);
        kryo.register(CreateLobbyPlayer.class);
        kryo.register(LobbyPlayerReady.class);
        kryo.register(LobbyPlayerReadySync.class);
        kryo.register(TransitionScene.class);
        kryo.register(AddPawnObject.class);
        kryo.register(LobbyToStartGame.class);
        kryo.register(GameplayReady.class);
        kryo.register(TicketType.class);
    }
}
