package sy.DialogWindow.HostButton;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class HostBothEmpty extends Dialog {
    public HostBothEmpty(String title, Skin skin) {
        super(title, skin);
    }

    public HostBothEmpty(String title, Skin skin, String windowStyleName) {
        super(title, skin, windowStyleName);
    }
    {
        /*
        Add logic here. No matter what constructer will be called code in this brackets will always be entered.
        Add Tickets here and add new PopUp Window for each Ticket with information
         */
        text("Bitte Name eingeben!");
        button("Ok");
        setScale(3);


    }

    @Override
    protected void result(Object object){

    }

    public HostBothEmpty(String title, Window.WindowStyle windowStyle) {
        super(title, windowStyle);
    }
}
