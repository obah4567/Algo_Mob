import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeNode extends Node {
    Node parent = null;
    List<Node> children = new ArrayList<>();

    @Override
    public void onSelection() {
        parent = this;
        setIconSize(20);
        sendAll(new Message("", "CONSTRUCTION"));
    }

    @Override
    public void onMessage(Message message) {
        if (message.getFlag().equals("CONSTRUCTION")) {
            if (parent == null) {
                parent = message.getSender();
                getCommonLinkWith(parent).setWidth(4);
                send(parent, new Message("", "ADOPTION"));
                sendAll(new Message(message));
            }
        }else if (message.getFlag().equals("ADOPTION")) {
            children.add(message.getSender());
        }
    }
}