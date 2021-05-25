public class Recherche {
    String[][] data;
    int[] variables;
    int nbC;
    int variableLength;

    public Recherche(String[][] data, int variableLength, int nbC) {
        this.data = data;
        variables= new int[variableLength];
        this.variableLength=variableLength;
        for(int i=0;i<variables.length;i++){
            variables[i]=-1;
        }
        this.nbC=nbC;
    }
    
    public int testAstar(int[] variables){
        int nb_clausesSat = 0;  //Nombre de clauses satisfiables
        boolean clauseSat;

       for (int i=0; i<data.length; i++){
           clauseSat = false;
           int j = 0;
           while (j<3 && clauseSat == false){
               int litteral = Integer.parseInt(data[i][j]);
               if (litteral>0){
                   if (variables[litteral-1]==1){
                       clauseSat = true;
                       nb_clausesSat ++;
                   }
               }
               else {
                   litteral = - litteral;
                   if(variables[litteral-1]==0){
                       clauseSat = true;
                       nb_clausesSat ++;
                   }
               }
               j++;
           }
       }
        //System.out.println(nb_clausesSat);
       return nb_clausesSat;
    }


    //Utilisée dans la rechercheAveugle(BFS et DFS) optimal car si une clause est fausse on sort directement.
    public boolean testAveugle(int[] variables){
        int i=0;Boolean bool=true;
        while(i<data.length && bool){
            bool=false;
            for(int j=0;j<3;j++){
                int num=Integer.parseInt(data[i][j]);
                if(num>0){
                    if(variables[num-1]==1){
                        bool=true;
                    }
                }
                else {
                    num=-num;
                    if(variables[num-1]==0){
                        bool=true;
                    }
                }
            }
            i++;
        }
        return bool;
    }

    public  void printMatrix(String[][] data){
        for(int i=0;i<data.length;i++){
            System.out.println("");
            for(int j=0;j<3;j++){
                System.out.println(data[i][j]);
            }
        }
    }

    public void printArray(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+", ");
        }
        System.out.println("");
    }
}
