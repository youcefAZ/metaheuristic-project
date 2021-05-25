public class TreeStar {

    int[] variables;
    int score;
    int profondeur;

    public TreeStar(int[] variables, int profondeur) {
        this.variables = variables;
        this.profondeur=profondeur;
        score=0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" + score +
                '}';
    }
}
