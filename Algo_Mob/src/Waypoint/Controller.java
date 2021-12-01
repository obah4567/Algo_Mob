package Waypoint;

import Waypoint.WaypointNode;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.CommandListener;
import io.jbotsim.ui.JTopology;
import io.jbotsim.ui.JViewer;
import io.jbotsim.ui.painting.BackgroundPainter;
import io.jbotsim.ui.painting.UIComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements CommandListener, BackgroundPainter {

    public static final String COMMAND_COMPUTE_TSP = "Compute TSP";

    private Topology topology;
    private JTopology jTopology;

    private List<Point> points = new ArrayList<>();

    public Controller() {
        topology = new Topology();
        topology.setDefaultNodeModel(Target.class);

        jTopology = new JTopology(topology);
        jTopology.addBackgroundPainter(this);

        new JViewer(jTopology);

        // Adding the custom command after the creation of
        // the JViewer makes sure it is displayed last.
        topology.addCommand(COMMAND_COMPUTE_TSP);
        topology.addCommandListener(this);
    }

    @Override
    public void onCommand(String command) {
        if (command.equals(COMMAND_COMPUTE_TSP)) {
            points.clear();
            for (Node node : topology.getNodes())
                if (node instanceof Target)
                    points.add(node.getLocation());

            List<Point> itinerary = computeItinerary();

            points = itinerary; // For drawing purposes

            assignToNode(itinerary);
        }
    }

    protected List<Point> computeItineraryV2() {

        // TODO: call TSP algorithms here
        List<Point> itinerary = new ArrayList<>();
        Point point = new Point();
        Double bestDist = Double.POSITIVE_INFINITY;
        //Point bestPoint = new Point();

        itinerary.add(points.get(0));

        for (Point p : points){
            if (point.distance(p) <= bestDist){
                bestDist = point.distance(p);
                //bestPoint = point;
                itinerary.add(point);
                //points.remove(point);
            }
        }
        return itinerary;

    }

    protected Point plusProcheVoisin(Point point, List<Point> points){

        Double bestDist = Double.POSITIVE_INFINITY;
        Point bestPoint = new Point();

        for (Point p : points){
            if (point.distance(p) <= bestDist){
                bestDist = point.distance(p);
                bestPoint = p;
            }
        }
        return bestPoint;

        /*for (int i = 0; i < points.size(); i++) {
            if (point.distance(points.get(i)) <= bestDist){
                bestDist = point.distance(points.get(i));
                bestPoint = points.get(i);
            }
        }
        return bestPoint;*/

    }

    protected List<Point> computeItinerary() {

        List<Point> itinerary = new ArrayList<>();

        Point point_Actuel = new Point();

        //itinerary.add(points.get(0));
        for (int i = 0; i < points.size(); i++){
            point_Actuel = plusProcheVoisin(points.get(i), points);
            itinerary.add(point_Actuel);
            points.remove(point_Actuel);
            //point_Actuel = points.get(i);

        }
        return itinerary;

    }


        protected List<Point> computeItinerary_V2() {

        // TODO: call TSP algorithms here
        List<Point> itinerary = new ArrayList<>();
        Point pt = new Point();

        /*if (! itinerary.isEmpty()){
            //Point startPoint = new Point();
            itinerary.add(points.get(0));
        }*/
        itinerary.add(points.get(0));
        while (itinerary.size() < points.size()){
            for (Point p : points){
                pt = plusProcheVoisin(p, points);
                itinerary.add(pt);
                points.remove(pt);
                pt = p;

            }
        }

        return itinerary;


        //1 - liste des points à visiter

        //je parcours mes points, et regarder qui est le plus proche, que le
        //precedent et l'ajoute à ma nouvelle liste et retourne donc itineraire

    }

    @Override
    public void paintBackground(UIComponent c, Topology tp) {
        Graphics2D g2d = (Graphics2D) c.getComponent();
        for (int i = 0; i < points.size(); i++) {
            Point from = points.get(i);
            Point to = points.get((i + 1) % points.size());
            g2d.drawLine((int) from.getX(), (int) from.getY(),
                    (int) to.getX(), (int) to.getY());
        }
    }

    protected void assignToNode(List<Point> itinerary) {
        if (!itinerary.isEmpty()) {
            WaypointNode node = new WaypointNode();
            node.setLocation(itinerary.get(0));
            for (int i = 1; i < itinerary.size() + 1; i++)
                node.addDestination(itinerary.get(i % itinerary.size()));
            topology.addNode(node);
        }
    }
}