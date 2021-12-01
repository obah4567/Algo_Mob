import ReseauxCapteurs.Bundle;
import io.jbotsim.core.Color;
import io.jbotsim.core.Node;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.SelectionListener;
import io.jbotsim.ui.JViewer;
import io.jbotsim.ui.icons.Icons;

public class MainGeorouting implements SelectionListener{
    Topology tp;
    GeoNode sourceNode;
    GeoNode targetNode;

    public MainGeorouting() {
        tp = new Topology();
        tp.setTimeUnit(100); // slow down for visualization
        tp.setDefaultNodeModel(GeoNode.class);
        tp.addSelectionListener(this);
        new JViewer(tp);
        tp.start();
    }

    @Override
    public void onSelection(Node node) {
        GeoNode selectedNode = (GeoNode) node;
        if (sourceNode == null) {
            sourceNode = selectedNode;
            sourceNode.setColor(Color.red);
        } else if (targetNode == null){
            targetNode = selectedNode;
            targetNode.setIcon(Icons.FLAG);
            targetNode.setIconSize(14);
            sourceNode.route(new Bundle(targetNode.getLocation(), "HELLO"));
        }
    }

    public static void main(String[] args) {
        new MainGeorouting();
    }
}