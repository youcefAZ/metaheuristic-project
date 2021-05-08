import java.util.ArrayList;

public class RechercheEnLargeur {
    String[][] data;
    int[] variables;
    int numberOfClauses;

    public RechercheEnLargeur(String[][]data, int variableLength, int numberOfClauses) {
        this.data = data;
        variables= new int[variableLength];
        for(int i=0;i<variables.length;i++){
            variables[i]=0;
        }
        this.numberOfClauses = numberOfClauses;
    }
}
