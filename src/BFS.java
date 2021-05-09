import java.util.ArrayList;
import java.util.Random;

public class BFS extends Recherche{

    int profondeur;

    public BFS(String[][] data, int variableLength, int profondeur) {
        super(data, variableLength);
        this.profondeur = profondeur;
    }

    @Override
    public void printMatrix() {
        super.printMatrix();
    }

    public void rechercheEnLargeur(){
        Random rand = new Random();
        for(int i=0;i< variables.length;i++){
            variables[i]=rand.nextInt(2);
            System.out.println(i+"="+variables[i]);
        }
        System.out.println(super.testAveugle(variables));

    }


}
