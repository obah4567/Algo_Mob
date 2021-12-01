package ReseauxCapteurs;

import io.jbotsim.core.Point;

public class Bundle {
    public Point target;
    public String text;

    public Bundle(Point target, String text) {
        this.target = target;
        this.text = text;
    }
}