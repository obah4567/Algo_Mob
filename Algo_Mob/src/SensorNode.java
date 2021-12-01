import com.sun.webkit.ColorChooser;
import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensorNode extends TreeNode {

    private int value;
    private int maxValue;
    private int nombreMessage;

    public void onSelection() {
        super.onSelection();
        setIcon(Icons.STATION);
        setIconSize(25);

    }

    public void sense(){

        Random random = new Random();
        value = random.nextInt(255);
        setColor(new Color(value, 100, value));

        maxValue = value;
        nombreMessage = 0;

        if (children.size() == 0){
            send(parent, new Message(value, "AGGREGATION"));
            System.out.println(this.getID() + " envoie à : " + parent.getID() + " la valeur " + value);
        }


    }
    //a mettre dans sense


    public String toString() {
        return "SensorNode{" + "ID = " + getID() + " | " + "value = " + value + '}';
    }

    //@Override
    public void onMessage_V1(Message message) {
        super.onMessage(message);

        if (message.getFlag().equals("AGGREGATION")) {
            nombreMessage ++;

            if ((int)message.getContent() > maxValue){
                maxValue = (int)message.getContent();

                System.out.println("la valeur maximale du noeud " + this.getID() + " est : " + maxValue);
            }

            if (nombreMessage == children.size() && this.parent != this){
                send(parent, new Message(maxValue, "AGGREGATION"));
                System.out.println("Ici => " + this.getID() + " envoie à : " + parent.getID() + " la valeur : " + maxValue);
                //System.out.println("test 2" + nbMessagesRecu);
            }

            /*if (this.parent != this){
                System.out.println("La valeur maximale du noeud " + this.getID() + " est : " + maxValue);
            }*/
        }
    }

    public void onMessage(Message message) {
        super.onMessage(message);

        if (message.getFlag().equals("AGGREGATION")) {
            nombreMessage ++;

            if ((int)message.getContent() > maxValue){
                maxValue = (int)message.getContent();

                System.out.println("la valeur maximale du noeud " + this.getID() + " est : " + maxValue);
            }

            if (nombreMessage == children.size() && this.parent != this){
                setColor(new Color(255, 0, 0));
                send(parent, new Message(maxValue, "AGGREGATION"));
                System.out.println("Ici => " + this.getID() + " envoie à : " + parent.getID() + " la valeur : " + maxValue);
                //System.out.println("test 2" + nbMessagesRecu);
            }

            /*if (this.parent != this){
                System.out.println("La valeur maximale du noeud " + this.getID() + " est : " + maxValue);
            }*/
        }
    }
}