import ReseauxCapteurs.Bundle;
import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;

public class GeoNode extends Node {

    Node v;
    @Override
    public void onMessage(Message message) {
        Bundle bundle = (Bundle)message.getContent();
        if (bundle.target.equals(getLocation())) {
            System.out.println("Message reçu : " + bundle.text);
        }else{
            route(bundle);
        }
    }



    public void route(Bundle bundle){
        setColor(Color.red);

        Double bestDistance = Double.POSITIVE_INFINITY;

        for (Node u : getNeighbors()) {
            if (u.distance(bundle.target) < bestDistance){
                bestDistance = u.distance(bundle.target);

            }
            send(u, new Message(bundle.target, "AGGREGATION"));
        }

    }


}