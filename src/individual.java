import java.util.Random;

public class individual {
    int [] variable;
    int variableLength, fitness, generation;

    public individual(int variableLength) {
        this.variableLength = variableLength;
        this.variable = new int[variableLength];
    }

    public individual InitialiseIndividual(int generation){
        Random random= new Random();
        for (int j=0; j<variableLength; j++ ){
            this.variable[j] = random.nextInt(2);
        }
        this.generation = generation;
        return this;
    }

}
