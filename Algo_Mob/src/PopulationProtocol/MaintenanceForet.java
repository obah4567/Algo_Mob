package PopulationProtocol;

import io.jbotsim.core.Color;
import io.jbotsim.core.Node;

public class MaintenanceForet extends PPNode {

    int compteur = 1;
    private Node parent = null;

    public void onStart() {
        setColor(Color.red);}

    @Override
    public void onSelection() {
    }

    public void interactWith(Node responder) {
        PPNodeCountingV3 q = (PPNodeCountingV3) responder;
        // this = initiateur

        if (this.getColor() == Color.red && responder.getColor() == Color.red){
            q.setColor(Color.blue);
            parent = this;
            getCommonLinkWith(q).setWidth(4);
        }

        else if (this.getColor() == Color.blue && responder.getColor() == Color.red){
            if (parent == q){
                q.setColor(Color.blue);
                //this.compteur += q.compteur;
                getCommonLinkWith(q).setWidth(4);
            }
        }

    }


    @Override
    public String toString() {
        return "PPNodeCountingV3{" + "compteur=" + compteur + '}';
    }
}