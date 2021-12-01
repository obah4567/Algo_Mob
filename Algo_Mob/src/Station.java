import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

public class Station extends Node {

    public Station() {
        setIcon(Icons.STATION);
        setIconSize(25);
        setCommunicationRange(120);
    }

    @Override
    public void onStart() {
        sendAll(new Message());
    }
}