public class Astar extends Recherche{

    String heuristic;

    public Astar(String[][] data, int variableLength, String heuristic) {
        super(data, variableLength);
        this.heuristic = heuristic;
    }
}
