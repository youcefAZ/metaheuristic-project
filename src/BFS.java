
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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

    @Override
    public void printArray(int[] array) { super.printArray(array); }



    //Dans cet algorithme, nous créeons une file qui vas assurer l'ordre de la recherche en largeur,
    //aprés chaque test de nos variables sur le data, nous enfilons les fils du noeud actuel.
    //bool1 represente si on trouve une solution, et bool2 si on est arrivé a la profondeur max donnée.
    int[] rechercheEnLargeur() {
        Boolean bool=false,bool2=false;
        TreeNode node=new TreeNode(variables,0);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
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
                bool=testAveugle(tempNode.variables);
                System.out.println(bool);
                System.out.println("profondeur : "+tempNode.profondeur);
                System.out.println("----------------------");
                if(bool==true){
                    System.out.println("Solution trouvée! :");
                    printArray(tempNode.variables);
                    return tempNode.variables;
                }
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
        int i=0;
        int[] temporary=variables.clone();
        while( i< variables.length && variables[i]!=-1){
            i++;
        }

        if(i!= variables.length){
            temporary[i]=lfri;
            TreeNode treeNode= new TreeNode(temporary,profondeur);
            return treeNode;
        }
        return null;
    }

}
