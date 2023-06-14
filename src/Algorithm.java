public abstract class Algorithm {
    private MazeBoard mazeboard;
    public Algorithm(MazeBoard mazeboard){
        this.mazeboard=mazeboard;
    }
    public int getNode_width(){
        return  mazeboard.getNode_Width();
    }
    public  int getNode_height(){
        return mazeboard.getNode_Height();
    }

//    public Node getStartPoint() {
//        return startPoint;
//    }

//    public Node getEndPoint() {
//        return endPoint;
//    }

    public abstract void SolveMaze(Node startPoint,Node endPoint);
}
