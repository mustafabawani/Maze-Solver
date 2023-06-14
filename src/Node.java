
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics2D;

public class Node {
    private int Xpos;
    private int Ypos;
    private Color nodeColor = Color.LIGHT_GRAY;
    private final int WIDTH = 35;
    private final int HEIGHT = 35;
    private Node left, right, up, down;

    private double fcost;


    public Node(int x, int y) {
        Xpos = x;
        Ypos = y;
    }

    public Node() {
    }

    public static double distance(Node a, Node b) {
        double x = Math.pow(a.Xpos - b.Xpos, 2);
        double y = Math.pow(a.Ypos - b.Ypos, 2);

        return Math.sqrt(x + y);
    }

    public void setColor(Color c) {
        nodeColor = c;
    }
    public Color getNodeColor(){
        return nodeColor;
    }

    public List<Node> getNeighbours() {
        List<Node> neighbours = new ArrayList<>();

        if (right != null && right.isPath())
            neighbours.add(right);
        if (down != null && down.isPath())
            neighbours.add(down);
        if (left != null && left.isPath())
            neighbours.add(left);
        if (up != null && up.isPath())
            neighbours.add(up);

        return neighbours;
    }

    public void setDirections(Node l, Node r, Node u, Node d) {
        left = l;
        right = r;
        up = u;
        down = d;
    }

    public void clearNode() {
        nodeColor = Color.LIGHT_GRAY;
    }

    public int getX() {
        return (Xpos - 15) / WIDTH;
    }


    public int getY() {
        return (Ypos - 15) / HEIGHT;
    }
    public int getXpos(){
        return Xpos;
    }
    public int getYpos(){
        return  Ypos;
    }

    public Node setX(int x) {
        Xpos = x;
        return this;
    }

    public Node setY(int y) {
        Ypos = y;
        return this;
    }

    public boolean isWall() {
        return (nodeColor == Color.BLACK);
    }

    public boolean isStart() {
        return (nodeColor == Color.GREEN);
    }

    public boolean isEnd() {
        return (nodeColor == Color.RED);
    }

    public boolean isPath() {
        return (nodeColor == Color.LIGHT_GRAY || nodeColor == Color.RED);
    }

    public boolean isSearched() {
        return (nodeColor == Color.BLUE || nodeColor == Color.ORANGE);
    }

}