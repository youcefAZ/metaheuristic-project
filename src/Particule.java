public class Particule {

    int[] position;
    float velocity;
    int score;

    public Particule(int[] position, float velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Particule() {
        score=0;
    }
}
