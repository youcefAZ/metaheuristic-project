import java.util.LinkedList;

public class Astar extends Recherche{

    String heuristic;       //useless for now
    int[] heuristicArray1;  //not yet done
    int[] heuristicArray2;  //heuristic of TD
    int[] usageArray;       //i value is 0 when we didnt use the variable yet, else 1,
                            //Used to get the best unused var to use in the childs of a node.

    LinkedList<TreeStar> openList = new LinkedList<TreeStar>();


    public Astar(String[][] data, int variableLength,int nbC, String heuristic) {
        super(data, variableLength,nbC);
        this.heuristic = heuristic;
        heuristicArray1 = new int[variableLength*2-1];
        //fillHeuristicArray1();
        heuristicArray2= new int[variableLength];
        fillHeuristicArray2();      //gives every variable a score based on the td heuristic
        usageArray= new int[variableLength];
        refreshUsage(variables);    //initialize the usageArray to all 0

    }

    /*overall logic is :
    while(!openList.Empty) :
        pop node from openList
        return node.vars if its satisfiable, else:
            choose var to use next with a heuristic
            create the childs and add them to openList, wich is sorted
    end*/
    public int[] astar_algo(){
        TreeStar node=new TreeStar(variables,0);
        openList.push(node);

        while (!openList.isEmpty())
        {
            TreeStar temp= openList.pollLast(); //pop the highest scoring node in openList
            refreshUsage(temp.variables);       //uses the current node variables to update usageArray

            if(temp.score==nbC){
                System.out.println("solution trouvée !");
                printArray(temp.variables);
                return temp.variables;
            }

            int bestVar=chooseV();  //get the highest scoring unused var to use it in children
            TreeStar node1= nextVar(temp.variables, 0,bestVar,temp.profondeur+1);
            TreeStar node2= nextVar(temp.variables, 1,bestVar,temp.profondeur+1);

            if(node1!=null){
                addToOpenList(node1);
                addToOpenList(node2);
            }
        }

        System.out.println("solution non trouvée");
        return null;
    }



    //add a node to openList, the list is ordered croissant, so we always pop the highest scoring node
    public void addToOpenList(TreeStar node){
        Boolean bool=false;
        int i=0;
        while(i<openList.size() && !bool){
            if(openList.get(i).score>node.score){
                bool=true;
            }
            else {
                i++;
            }
        }
        openList.add(i,node);
    }


    //for when we finish a branch and pop to another branch, need to update the usageArray
    public void refreshUsage(int[] vars){
        for(int i=0;i<variableLength;i++){
            if(vars[i]==-1){
                usageArray[i]=0;
            }
            else {
                usageArray[i]=1;
            }
        }
    }


    //creates next var, priotirizes the var with the most satisfied clauses
    public TreeStar nextVar(int[] variables,int lfri, int bestVar, int profondeur){
        if(profondeur<=variableLength){
            int[] temporary=variables.clone();

            temporary[bestVar]=lfri;//change the best available var from -1 to 0 or 1
            TreeStar treeStar= new TreeStar(temporary,profondeur);
            treeStar.setScore(testAstar(temporary));
            return treeStar;
        }
            return null;

    }

    //Chooses the best available var, c te3 TD
    public int chooseV(){
        int min=0;
        //this loop is used to avoid a bug
        for(int i=0;i<variableLength;i++){
            if(heuristicArray2[i]<heuristicArray2[min]){
                min=i;
            }
        }
        int max=min;
        //returns highest scoring and unused var
        for(int i=0;i<variableLength;i++){
            if(heuristicArray2[i]>=heuristicArray2[max] && usageArray[i]==0){
                max=i;
            }
        }
        usageArray[max]=1;
        return max;
    }


    //fill the array2 with heuristic 2 values (clauses satisfied by x+xBAR, h te3 td exemple)
    public void fillHeuristicArray2(){
        int[] temp;
        //every var gets a score based on how many clauses it satisfies
        for(int i = 0; i< heuristicArray2.length; i++){
            temp=variables.clone();
            temp[i]=0;
            heuristicArray2[i]=testAstar(temp);
            temp[i]=1;
            heuristicArray2[i]+=testAstar(temp);
        }
    }


    //fill the array with heuristic 1 values, not used yet
    public void fillHeuristicArray1(){
        int[] temp;
        for(int i = 0; i< heuristicArray1.length/2; i++){
            temp=variables.clone();
            temp[i]=0;
            heuristicArray1[i]=testAstar(temp);
            temp[i]=1;
            heuristicArray1[i+75]=testAstar(temp);
        }
    }


}
