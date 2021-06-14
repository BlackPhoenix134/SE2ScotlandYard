package sy.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.List;

import sy.core.Tickets.TicketType;

public class TicketSelectDialog extends Dialog {

    public TicketSelectDialog(String title, Skin skin) {
        super(title, skin);
    }

    public TicketSelectDialog(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }

    public TicketSelectDialog(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }

    public TicketSelectDialog(String title, Skin skin, List<TicketType> ticketTypes) {
        super(title, skin);
        text("Select ticket to use");
        button("Exit", null);
        for(TicketType ticketType : ticketTypes) {
            button(ticketType.toString(), ticketType);
        }
        setScale(3);
    }
}
