public class TreeNode {
    int[] variables;
    int profondeur;
    TreeNode left,right;

    public TreeNode(int[] variables, int profondeur) {
        this.variables = variables;
        this.profondeur = profondeur;
        left=null;
        right=null;
    }
}
