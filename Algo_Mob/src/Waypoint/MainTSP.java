package Waypoint;

import Waypoint.Controller;
import io.jbotsim.core.Topology;

public class MainTSP {
    public static void main(String[] args) {
        Topology topology = new Topology();
        new Controller();
        topology.setDefaultNodeModel(Target.class);
    }
}