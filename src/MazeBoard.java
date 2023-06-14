import java.awt.*;
import java.util.Calendar;
import java.util.Random;

public class MazeBoard {
    private Node[][] mazeBoard;
    private Node startPoint;
    private Node endPoint;
    private final static int NODES_WIDTH = 28;
    private final static int NODES_HEIGHT = 19;

    public MazeBoard(){
        mazeBoard=new Node[NODES_WIDTH][NODES_HEIGHT];
    }
    public Node getStartPoint(){
        return startPoint;
    }
    public Node getEndPoint(){
        return endPoint;
    }

    public static int getNodesHeight() {
        return NODES_HEIGHT;
    }

    public static int getNodesWidth() {
        return NODES_WIDTH;
    }
    public int getXposOfANode(int row,int col){
            return mazeBoard[row][col].getXpos();
    }
    public int getYposOfANode(int row,int col){
            return mazeBoard[row][col].getYpos();
        }
    public void createNodes(boolean ref) {
        System.out.println(mazeBoard.length);
        System.out.println(mazeBoard[0].length);
        Random rand = new Random(Calendar.getInstance().getTimeInMillis());

        for (int i = 0; i < mazeBoard.length; i++) {
            for (int j = 0; j < mazeBoard[i].length; j++) {

                if(!ref) mazeBoard[i][j] = new Node(i, j).setX(15 + i * 35).setY(15 + j * 35);
                if(i==0 && j==0){
                    mazeBoard[i][j].setColor(Color.green);
                    startPoint=mazeBoard[i][j];
                }
                else if(i== mazeBoard.length-1 && j== mazeBoard[i].length-1){
                    mazeBoard[i][j].setColor(Color.red);
                    endPoint=mazeBoard[i][j];
                }
                else {
                    int num = rand.nextInt(4);
                    if (num == 0) {
                        mazeBoard[i][j].setColor(Color.black);
                    } else {
                        mazeBoard[i][j].clearNode();
                    }
                }
            }
        }
    }
    public int getNode_Width(){
        return NODES_WIDTH;
    }

    public int getNode_Height(){
        return NODES_HEIGHT;
    }
    public void setMazeDirections() {
        for (int i = 0; i < mazeBoard.length; i++) {
            for (int j = 0; j < mazeBoard[i].length; j++) {
                Node up = null,down = null,left = null,right = null;
                int u = j - 1;
                int d = j + 1;
                int l = i - 1;
                int r = i + 1;

                if(u >= 0) up = mazeBoard[i][u];
                if(d < NODES_HEIGHT) down =  mazeBoard[i][d];
                if(l >= 0) left = mazeBoard[l][j];
                if(r < NODES_WIDTH) right =  mazeBoard[r][j];

                mazeBoard[i][j].setDirections(left, right, up, down);
            }
        }
    }

    public Color getColorOfANode(int row, int col) {
        return  mazeBoard[row][col].getNodeColor();
    }
}
