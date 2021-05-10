public class Recherche {
    String[][] data;
    int[] variables;

    public Recherche(String[][] data, int variableLength) {
        this.data = data;
        variables= new int[variableLength];
        for(int i=0;i<variables.length;i++){
            variables[i]=-1;
        }
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
       return nb_clausesSat;
    }

    public boolean testAveugle(int[] variables){
        int i=0;Boolean bool=true;
        System.out.println("IN AVEUGLE");
        while(i<data.length && bool){
            System.out.println(i);
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

    public  void printMatrix(){
        for(int i=0;i<data.length;i++){
            System.out.println("");
            for(int j=0;j<3;j++){
                System.out.println(data[i][j]);
            }
        }
    }
}
