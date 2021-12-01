import io.jbotsim.core.Node;
import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class MainTreeNode{
    public static void main(String[] args){
        Topology topology = new Topology();

        topology.addCommand("Update values");
        topology.addCommandListener(command -> {
            if (command.equals("Update values"))
                for (Node node : topology.getNodes())
                    ((SensorNode)node).sense();
        });

        topology.setDefaultNodeModel(SensorNode.class);
        topology.setTimeUnit(500);
        new JViewer(topology);
        topology.start();

    }

}
