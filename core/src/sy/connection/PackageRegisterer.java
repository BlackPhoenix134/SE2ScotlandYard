package sy.connection;

import com.esotericsoftware.kryo.Kryo;
import sy.connection.packages.*;
import sy.core.Tickets.TicketType;

public class PackageRegisterer {
    public static void register(Kryo kryo) {
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
        kryo.register(DetectivesWonRequest.class);
        kryo.register(DetectivesWon.class);
        kryo.register(DetectiveDies.class);
        kryo.register(MisterXwon.class);
    }
}
