import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //BFS bfs= new BFS(data,Integer.parseInt(parameters[2]),20);
        //bfs.printMatrix();
        //int[]result=bfs.rechercheEnLargeur();

        UIClass ui= new UIClass(primaryStage);

    }



}
