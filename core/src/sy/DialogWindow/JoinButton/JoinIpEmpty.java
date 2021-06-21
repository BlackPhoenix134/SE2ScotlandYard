package sy.DialogWindow.JoinButton;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;



public class JoinIpEmpty extends Dialog {

    public JoinIpEmpty(String title, Skin skin) {
        super(title, skin);
    }

    public JoinIpEmpty(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }
    {
        /*
        Add logic here. No matter what constructer will be called code in this brackets will always be entered.
        Add Tickets here and add new PopUp Window for each Ticket with information
         */
        text("Bitte IP eingeben!");
        button("Ok");
        setScale(3);


    }

    @Override
    protected void result(Object object){

    }

    public JoinIpEmpty(String title, Window.WindowStyle windowStyle) {
        super(title, windowStyle);
    }
}


/*
How to add DialogWindow to Screen
1.  Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
2.  TicketDialog ticketDia = new TicketDialog("confirm exit", skin);
3.  addActorsToStage(ticketDia);
4.  ticketDia.setPosition(screenWidth/2f - ticketDia.getWidth() / 2f, screenHeight / 2f - ticketDia.getHeight() / 2f);

Calls this class. See comment above!!
 */

