package Waypoint;

import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

//TD 2


public class Target extends Node {
    public Target() {
        disableWireless();
        setIcon(Icons.PLUS);
    }
}