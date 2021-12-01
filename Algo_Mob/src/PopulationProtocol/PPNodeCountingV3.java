package PopulationProtocol;

import io.jbotsim.core.Color;
import io.jbotsim.core.Node;

public class PPNodeCountingV3 extends PPNode {

    int compteur = 1;

    public void onStart() {
        setColor(Color.red);}

    @Override
    public void onSelection() {
    }

    public void interactWith(Node responder) {
        PPNodeCountingV3 q = (PPNodeCountingV3) responder;
        // this = initiateur
        // regle R->R : R -> B
        if (this.getColor() == Color.red && responder.getColor() == Color.red){
            q.setColor(Color.blue);
            this.compteur += q.compteur;
            q.compteur = 0;


        }
        // regle R-> B : B -> R
        if (this.getColor() == Color.red && responder.getColor() == Color.blue){
            q.setColor(Color.red);
            q.compteur = this.compteur;
            this.setColor(Color.blue);
            this.compteur =0;


        }


    }


    @Override
    public String toString() {
        return "PPNodeCountingV3{" + "compteur=" + compteur + '}';
    }
}