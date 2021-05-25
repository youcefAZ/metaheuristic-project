
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BFS extends Recherche{

    int profondeur;

    public BFS(String[][] data, int variableLength,int nbC, int profondeur) {
        super(data, variableLength,nbC);
        this.profondeur = profondeur;
    }

    @Override
    public void printMatrix(String[][] data) {
        super.printMatrix(data);
    }

    @Override
    public void printArray(int[] array) { super.printArray(array); }



    //Dans cet algorithme, nous créeons une file qui vas assurer l'ordre de la recherche en largeur,
    //aprés chaque test de nos variables sur le data, nous enfilons les fils du noeud actuel.
    //bool1 represente si on trouve une solution, et bool2 si on est arrivé a la profondeur max donnée.
    int[] rechercheEnLargeur() {
        Boolean bool=false,bool2=false;
        TreeNode node=new TreeNode(variables,0);  //Creating the first node of -1.
        Queue<TreeNode> queue = new LinkedList<TreeNode>();       //Queue will give us the order of breadth first
        queue.add(node);
        while (!queue.isEmpty() && bool==false && bool2==false)
        {

            // poll() removes the present head.
            TreeNode tempNode = queue.poll();
            if(tempNode.profondeur>profondeur){
                bool2=true;
            }
            else
            {
                bool=testAveugle(tempNode.variables);   //testing if current node is satisfiable
                if(bool==true){
                    System.out.println("Solution trouvée! :");
                    printArray(tempNode.variables);
                    return tempNode.variables;
                }
                //node isnt satisfiable, we create the childs
                TreeNode nodeLeft=nextVar(tempNode.variables,tempNode.profondeur+1,0);
                tempNode.left=nodeLeft;
                TreeNode nodeRight=nextVar(tempNode.variables,tempNode.profondeur+1,1);
                tempNode.right=nodeRight;
            }

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }

        if(bool2==true){
            System.out.println("Limite de profondeur atteinte, solution non trouvée.");
        }
        else {
            System.out.println("Solution non trouvée dans l'espace de recherche.");
        }
        return null;
    }


    //Cet algorithme sert a creer le prochain tableau de variable a utilisée a partir du tableau pére.
    public TreeNode nextVar(int[] variables,int profondeur,int lfri){
        if(profondeur<=this.profondeur){    //si profondeur>profondeurDonnée veut dire on a atteint le max prof.
            int i=0;
            int[] temporary=variables.clone();

            //we increment i until we arrive at the var to use in child nodes
            while( i< variables.length && variables[i]!=-1){
                i++;
            }

            if(i!= variables.length){
                temporary[i]=lfri;  //we change the var i to 0 or 1 to create the child node
                TreeNode treeNode= new TreeNode(temporary,profondeur);
                return treeNode;
            }
        }
        return null;
    }

}
