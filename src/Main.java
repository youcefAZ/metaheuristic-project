import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static ArrayList fileToArraylist(String file) throws  IOException{
        BufferedReader bufReader = new BufferedReader(new FileReader(file));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line); line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }

    public static void printMatrix(String[][] matrix){
        for(int i=0;i<matrix.length;i++){
            System.out.println("");
            for(int j=0;j<3;j++){
                System.out.println(matrix[i][j]);
            }
        }
    }

    public static String [][] listToMatrix(ArrayList<String> dataList){
        String[][] data=new String[325][3];
        for( int i=8;i<dataList.size()-3;i++){
            String[] temp=dataList.get(i).split(" ");
            for(int j=0;j<temp.length-1;j++){
                data[i-8][j]=temp[j];
            }
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
        //for now n9raw file ml mena before ma we implement l UI
        ArrayList<String> dataList= fileToArraylist("cnfs\\uf75-01.cnf");

        String[] parameters= dataList.get(7).split(" ");
        String[][] data=listToMatrix(dataList);

        printMatrix(data);

        RechercheEnLargeur rechercheEnLargeur= new RechercheEnLargeur(data,Integer.parseInt(parameters[2]),Integer.parseInt(parameters[4]));
    }
}
