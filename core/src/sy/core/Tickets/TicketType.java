package sy.core.Tickets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import sy.assets.AssetDescriptors;

public enum TicketType {
    HORSE, BIKE, DRAGON, BLACK_TICKET, DOUBLETURN_TICKET;

    //make ticket asset name = Ticket name :))))
    public static AssetDescriptor<Texture> toAsset(TicketType ticketType) {
        switch (ticketType) {
            case HORSE:
                return AssetDescriptors.HORSE;
            case BIKE:
                return AssetDescriptors.BIKE;
            case DRAGON:
                return AssetDescriptors.DRAGON;
            case BLACK_TICKET:
                return AssetDescriptors.BLACK_TICKET;
            case DOUBLETURN_TICKET:
                return AssetDescriptors.DOUBLE_TICKET;
            default:
                return null;
        }
    }
}
