package PopulationProtocol;

import io.jbotsim.core.Color;
import io.jbotsim.core.Node;

public class PPNodeCountingV2 extends PPNode {

    int compteur = 1;

    public void onStart() {
        setColor(Color.red);}

    @Override
    public void onSelection() {
    }

    public void interactWith(Node responder) {
        if (this.getColor() == Color.red && responder.getColor() == Color.red){
            responder.setColor(Color.green);
            PPNodeCountingV2 q = (PPNodeCountingV2) responder;
            this.compteur += q.compteur;
            q.compteur = 0;
        }
        System.out.println("Le compteur est : " + compteur);
    }

    @Override
    public String toString() {
        return "PPNodeCountingV2{" +
                "compteur=" + compteur +
                '}';
    }
}