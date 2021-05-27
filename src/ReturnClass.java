public class ReturnClass {
    int[] vars;
    int score;
    Boolean satisfied;

    public ReturnClass(int[] vars, int score, Boolean satisfied) {
        this.vars = vars;
        this.score = score;
        this.satisfied = satisfied;
    }

    public ReturnClass(int[] vars, Boolean satisfied) {
        this.vars = vars;
        this.satisfied = satisfied;
    }
}
