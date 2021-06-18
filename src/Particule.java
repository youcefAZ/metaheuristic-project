import java.math.BigDecimal;
import java.math.BigInteger;

public class Particule {

    int[] position;
    BigInteger velocity;
    int score;

    public Particule(int[] position, BigInteger velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Particule() {
        score=0;
    }
}
