import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
public class BackTrack extends Algorithm{

    public BackTrack(MazeBoard mazeboard) {
        super(mazeboard);

    }

    @Override
    public void SolveMaze(Node startPoint,Node endPoint) {
        Stack<Node> nodes = new Stack<>();
        nodes.push(startPoint);

        while (!nodes.empty()) {
            Node curNode = nodes.pop();
            if (!curNode.isEnd()) {

                curNode.setColor(Color.ORANGE);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                curNode.setColor(Color.BLUE);

                for (Node adjacent : curNode.getNeighbours()) {

                    nodes.push(adjacent);
                }
            } else {
                curNode.setColor(Color.MAGENTA);
                break;
            }
        }


    }
}
