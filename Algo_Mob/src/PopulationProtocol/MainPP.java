package PopulationProtocol;

import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class MainPP {
    public static void main(String[] args) {
        Topology tp = new Topology();
        //tp.setDefaultNodeModel(PPNodeAlgo1.class);
        //tp.setDefaultNodeModel(PPNodeCountingV1.class);
        //tp.setDefaultNodeModel(PPNodeCountingV2.class);
        //tp.setDefaultNodeModel(PPNodeCountingV3.class);
        tp.setDefaultNodeModel(MaintenanceForet.class);
        tp.setTimeUnit(100);
        new PPScheduler(tp);
        new JViewer(tp);
        tp.start();
    }
}