package Coloration;

import io.jbotsim.core.Topology;
import io.jbotsim.gen.basic.TopologyGenerators;
import io.jbotsim.ui.JTopology;
import io.jbotsim.ui.JViewer;

public class Main {
    public final static int n = 11;
    public static void main(String[] args) {
        Topology tp = new Topology();
        tp.setDefaultNodeModel(ColoringNode.class);
        TopologyGenerators.generateRing(tp, n);

        for (int i = 0; i < n; i++){
            ColoringNode node = (ColoringNode) tp.getNodes().get(i);
            node.setLocation(node.getX()+250, node.getY()+100); // set node position
            node.parent = tp.getNodes().get((i+1) % n); // parent selection
            node.setID((int) Math.floor(Math.random() * n*n*n)); // random ID in [0..n^2[
        }

        JTopology jtp = new JTopology(tp);
        jtp.addLinkPainter(new JParentLinkPainter());
        new JViewer(jtp);
        tp.start();
        tp.pause();
    }
}