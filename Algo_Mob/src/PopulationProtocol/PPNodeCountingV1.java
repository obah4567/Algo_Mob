package PopulationProtocol;

import io.jbotsim.core.Color;
import io.jbotsim.core.Node;

public class PPNodeCountingV1 extends PPNode {

    int compteur = 0;

    public void onStart() {
        setColor(Color.blue);}

    public void onSelection() {
        setColor(Color.red);}

    public void interactWith(Node responder) {
        if (this.getColor() == Color.red && responder.getColor() == Color.blue){
            responder.setColor(Color.green);
            compteur = compteur +1;
        }
        System.out.println("Le compteur est : " + compteur);
    }

    @Override
    public String toString() {
        return "PPNodeCountingV1{" +
                "compteur=" + compteur +
                '}';
    }
}