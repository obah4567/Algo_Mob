package Coloration;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;

public class ColoringNode extends Node {
    public Node parent;
    private int x, y, sommeTopo, l,ll, n = 0;

    @Override
    public void onStart() {
        sommeTopo = getTopology().getNodes().size();
        x = this.getID();
        l = clog2(sommeTopo);
        setColor(Color.getColorAt(x));
        sendAll(new Message(x));
    }

    @Override
    public void onClock(){

        for (Message m : getMailbox()){
            if (m.getSender().equals(parent)){
                n = getTopology().getNodes().size();
                x = posDiff(x, (Integer) m.getContent());
                setColor(Color.getColorAt(x));
                ll = l;
                l = 1 + clog2(l);
                if ((ll == l)){
                    System.out.println("Fin");
                    return;
                }
            }
            sendAll( new Message(x));
        }
    }

    private static int posDiff(int x, int y){
        for (int p = 0;  ; p++) {
            if ((x & 1) != (y & 1)) {
                return 2 * p + (x & 1);
            }
            x >>= 1;
            y >>= 1;
        }
    }

    private int clog2(int x){
        int result = 0;
        --x;
        while (x > 0) {
            ++result;
            x >>= 1;
        }
        return result;
    }


}