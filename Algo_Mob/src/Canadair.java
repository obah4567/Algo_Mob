import Waypoint.WaypointNode;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.ui.icons.Icons;

public class Canadair extends WaypointNode {
    Point parking;

    public Canadair(){
        setIcon(Icons.CANADAIR);
        setIconSize(16);
        setCommunicationRange(120);
        setSensingRange(30);
    }

    @Override
    public void onStart() {
        parking = getLocation();
        super.setSpeed(2);
    }

    @Override
    public void onSensingIn(Node node) {
        super.onSensingIn(node);
        if (node instanceof Fire ){
            node.die();
        }

    }
}