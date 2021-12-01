import io.jbotsim.core.Color;
import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

import java.util.ArrayList;
import java.util.List;

public class Sensor extends Node {
    Node parent = null;
    List<Fire> sensedFires = new ArrayList<>();

    @Override
    public void onStart() {
        setIcon(Icons.SENSOR);
        setIconSize(16);
        setCommunicationRange(120);
        setSensingRange(60);
    }

    @Override
    public void onSensingIn(Node node) {
        super.onSensingIn(node);

        //ArrayList<Fire> allFires = new ArrayList<Fire>();
        if (node instanceof Fire ){
             setColor(new Color(Color.red));
        }



    }

    @Override
    public void onSensingOut(Node node) {
        super.onSensingOut(node);

    }
}