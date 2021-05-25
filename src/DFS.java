import java.util.Stack;

public class DFS extends Recherche{

    int profondeur;

    public DFS(String[][] data, int variableLength,int nbC, int profondeur) {
        super(data, variableLength, nbC);
        this.profondeur = profondeur;
    }

    public int[] rechercheEnProfondeur() {

        boolean sat = false;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // Creer puis empiler la racine :
        TreeNode node = new TreeNode(variables, 0);
        stack.push(node);

        int prof = 0;
        int [] tempVariables = variables.clone();
        TreeNode tempNode;

        while (!stack.isEmpty() && !sat) {
            node = stack.pop();     // depiler la tete de pile
            tempVariables = node.variables.clone();
            sat = testAveugle(tempVariables);
            prof = node.profondeur ;

            if ((prof < profondeur) && (!sat)) {
                // Si la profondeur n'est pas atteinte, on empile le fils droit puis le fils gauche.

                // Creer le fils droit du noeud courant puis l'empiler
                tempVariables = node.variables.clone();
                tempVariables[prof] = 0;
                tempNode = new TreeNode(tempVariables, prof+1);
                stack.push(tempNode);

                // Creer le fils gauche du noeud courant puis l'empiler
                tempVariables = node.variables.clone();
                tempVariables[prof] = 1;
                tempNode = new TreeNode(tempVariables, prof+1);
                stack.push(tempNode);
            }
        }

        if (sat){
            System.out.println("Solution trouvée ! Cette base est satisfiable\n");
            System.out.println("Solution : ");
            printArray(tempVariables);
            return tempVariables;
        }
        else {
            System.out.println("Pas de solution avec cette limite de profondeur\n");
            return null;
        }
    }

    @Override
    public void printMatrix(String[][] data) {
        super.printMatrix(data);
    }
}