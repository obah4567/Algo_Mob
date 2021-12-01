import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class HelloWorld{
    public static void main(String[] args){
        Topology tp = new Topology();
        //tp.setDefaultNodeModel(EmptyNode.class);
        //tp.setDefaultNodeModel(BroadcastNode.class);
        tp.setDefaultNodeModel(MovingNode.class);
        //tp.setDefaultNodeModel(TreeNode.class);
        tp.setTimeUnit(500);
        new JViewer(tp);
        tp.start();

    }

}
