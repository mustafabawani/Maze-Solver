import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Astar extends Algorithm{
    public Astar(MazeBoard mazeboard) {
        super(mazeboard);
    }

    @Override
    public void SolveMaze(Node startPoint, Node endPoint) {
        List<Node> openList = new ArrayList<Node>();
        Node[][] prev = new Node[getNode_width()][getNode_height()];
        openList.add(startPoint);

        while(!openList.isEmpty()) {

            Node curNode = getLeastHeuristic(openList,endPoint,startPoint);
            openList.remove(curNode);

            if(curNode.isEnd()) {
                curNode.setColor(Color.MAGENTA);
                break;
            }
            curNode.setColor(Color.ORANGE);
            curNode.setColor(Color.BLUE);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Node adjacent : curNode.getNeighbours()) {
                if(adjacent.isSearched()) {
                    continue;
                }
                double f1 = Node.distance(adjacent, endPoint);
                double h1 = Node.distance(curNode, startPoint);

                double f2 = Node.distance(adjacent, endPoint);
                double h2 = Node.distance(curNode, startPoint);

                if(!openList.contains(adjacent) || (f1 + h1 < f2 + h2)) {
                    prev[adjacent.getX()][adjacent.getY()] = curNode;
                    if(!openList.contains(adjacent)){
                        openList.add(adjacent);
                    }
                }
            }

        }
        shortpath(prev, endPoint);
    }
    private Node getLeastHeuristic(List<Node> nodes,Node end,Node start) {
        if(!nodes.isEmpty()) {
            Node leastH = nodes.get(0);
            for(int i = 1; i < nodes.size();i++) {
                double f1 = Node.distance(nodes.get(i), end);
                double h1 = Node.distance(nodes.get(i), start);

                double f2 = Node.distance(leastH, end);
                double h2 = Node.distance(leastH, start);
                if(f1 + h1 < f2 + h2) {
                    leastH = nodes.get(i);
                }
            }
            return leastH;
        }
        return null;
    }
    private void shortpath(Node[][] prev, Node end) {
        Node pathConstructor = end;
        while(pathConstructor != null) {
            pathConstructor = prev[pathConstructor.getX()][pathConstructor.getY()];

            if(pathConstructor != null) {
                pathConstructor.setColor(Color.ORANGE);
            }
        }
    }
}
