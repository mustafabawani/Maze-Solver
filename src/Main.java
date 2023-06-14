import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Main extends Canvas implements Runnable{
    private static JFrame frame;

    private final static int WIDTH = 1024;
    private final static int HEIGHT = 768;

    private final static int NODES_WIDTH = 28;
    private final static int NODES_HEIGHT = 19;

    private static MazeBoard mazeBoard;

    private static Algorithm algorithm;
    private static Main runTimeMain;

    public static void main(String[] args){
        frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLayout(null);
        Main m = new Main();
        // check
        m.setBounds(0, 25, WIDTH, HEIGHT);
        SetupMenu(frame);
        runTimeMain = m;
        // check
        frame.add(m);
        frame.setVisible(true);
        m.start();

    }
    public void start(){
        new Thread(this).start();
    }
    public static void SetupMenu(JFrame frame){
        JMenuBar bar=new JMenuBar();
        bar.setBounds(0,0,WIDTH,25);
        frame.add(bar);
        JMenu algorithmsMenu=new JMenu("Algorithms");
        bar.add(algorithmsMenu);

        JMenuItem backtrackItem = new JMenuItem("backtrack search");
        JMenuItem astarItem = new JMenuItem("A-star Search");

        astarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm=new Astar(mazeBoard);
                algorithm.SolveMaze(mazeBoard.getStartPoint(),mazeBoard.getEndPoint());
            }
        });
        backtrackItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm=new BackTrack(mazeBoard);
                algorithm.SolveMaze(mazeBoard.getStartPoint(),mazeBoard.getEndPoint());
            }
        });

        algorithmsMenu.add(astarItem);
        algorithmsMenu.add(backtrackItem);
    }

    public void init() {
        // check
        requestFocus();
        mazeBoard=new MazeBoard();
        mazeBoard.createNodes(false);
        mazeBoard.setMazeDirections();
    }
    @Override
    public void run() {
        init();
        while (true) {
            // check
            BufferStrategy bs = getBufferStrategy(); // check
            if (bs == null) {
                // check
                createBufferStrategy(2);
                continue;
            }
            // check
            Graphics2D grap = (Graphics2D) bs.getDrawGraphics(); // check
            render(grap);
            bs.show();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < mazeBoard.getNodesWidth(); i++) {
            for (int j = 0; j <mazeBoard.getNodesHeight(); j++) {

                g.setColor(Color.BLACK);
                g.drawRect(mazeBoard.getXposOfANode(i,j), mazeBoard.getYposOfANode(i,j), 35, 35);
                g.setColor(mazeBoard.getColorOfANode(i,j));
                g.fillRect( mazeBoard.getXposOfANode(i,j)+ 1, mazeBoard.getYposOfANode(i,j) + 1, 35 - 1, 35 - 1);
            }
        }
    }

}
