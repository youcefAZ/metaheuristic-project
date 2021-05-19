import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class UIClass{
    //Creating all the variables for all the UI panels
    Scene mainScene;
    TabPane tabPane = new TabPane();
    FileChooser fileChooser = new FileChooser();
    FileChooser fileChooser2= new FileChooser();
    Button buttonFileBfs= new Button("Choose");
    TextField textFBfs= new TextField();
    TextField profB= new TextField();
    Button launchB= new Button("Launch");
    Button buttonFileDfs= new Button("Choose");
    TextField textFDfs= new TextField();
    TextField profD= new TextField();
    Button launchD= new Button("Launch");
    TableView<String[]> tableViewB = new TableView();
    TableColumn<String[], String> column1B = new TableColumn<>("Premier x");
    TableColumn<String[], String> column2B = new TableColumn<>("Deuxiéme x");
    TableColumn<String[], String> column3B = new TableColumn<>("Troisiéme x");
    TableView<String[]> tableViewD = new TableView();
    TableColumn<String[], String> column1D = new TableColumn<>("Premier x");
    TableColumn<String[], String> column2D = new TableColumn<>("Deuxiéme x");
    TableColumn<String[], String> column3D = new TableColumn<>("Troisiéme x");
    ListView listViewB = new ListView();
    ListView listViewD = new ListView();



    public UIClass(Stage primaryStage){


        fileChooser.setTitle("Choose CNF File");
        fileChooser.setInitialDirectory(new File("cnfs"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF Files", "*.cnf"));

        fileChooser2.setTitle("Choose CNF File");
        fileChooser2.setInitialDirectory(new File("cnfs"));
        fileChooser2.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF Files", "*.cnf"));

        textFDfs.setEditable(false);textFBfs.setEditable(false);
        tableViewB.setMaxSize(300,180);tableViewD.setMaxSize(300,180);

        column1B.setMinWidth(100);
        column2B.setMinWidth(100);
        column3B.setMinWidth(100);

        column1D.setMinWidth(100);
        column2D.setMinWidth(100);
        column3D.setMinWidth(100);

        listViewB.setMaxHeight(250);listViewD.setMaxHeight(250);


        //Creating BFS tab
        GridPane gridBfs= new GridPane();gridBfs.setHgap(25);gridBfs.setVgap(25);

        HBox hBoxFBfs= new HBox();hBoxFBfs.getChildren().addAll(textFBfs,buttonFileBfs);

        gridBfs.add(new Label("Data : "),0,0);
        gridBfs.add(hBoxFBfs,1,0);
        gridBfs.add(new Label("Depth : "),0,1);
        gridBfs.add(profB,1,1);
        gridBfs.add(launchB,1,2);


        tableViewB.getColumns().addAll(column1B,column2B,column3B);

        VBox vbfs= new VBox();vbfs.setSpacing(20);
        vbfs.getChildren().addAll(gridBfs,tableViewB);

        VBox box2= new VBox();box2.setSpacing(20);

        listViewB.getItems().add("X0 : 0");listViewB.getItems().add("X1 : 1");

        box2.getChildren().addAll(new Label("Current depth : 0"),new Label("Current variables : "),listViewB);

        VBox box3= new VBox();box3.setSpacing(20);
        box3.getChildren().addAll(new Label("Result : "), new Label("No solution available."));

        HBox hbfs= new HBox();hbfs.setSpacing(30);hbfs.setPadding(new Insets(25,0,0,25));
        hbfs.getChildren().addAll(vbfs,box2,box3);



        //Creating DFS tab
        GridPane gdfs= new GridPane();gdfs.setVgap(25);gdfs.setHgap(25);

        HBox hBoxFDfs= new HBox();hBoxFDfs.getChildren().addAll(textFDfs,buttonFileDfs);

        gdfs.add(new Label("Data : "),0,0);
        gdfs.add(hBoxFDfs,1,0);
        gdfs.add(new Label("Depth : "),0,1);
        gdfs.add(profD,1,1);
        gdfs.add(launchD,1,2);


        tableViewD.getColumns().addAll(column1D,column2D,column3D);

        VBox vdfs= new VBox();vdfs.setSpacing(20);
        vdfs.getChildren().addAll(gdfs,tableViewD);

        VBox box2D= new VBox();box2D.setSpacing(20);

        listViewD.getItems().add("X0 : 0");listViewD.getItems().add("X1 : 1");

        box2D.getChildren().addAll(new Label("Current depth : 0"),new Label("Current variables : "),listViewD);

        VBox box3D= new VBox();box3D.setSpacing(20);
        box3D.getChildren().addAll(new Label("Result : "), new Label("No solution available."));

        HBox hdfs= new HBox();hdfs.setSpacing(30);hdfs.setPadding(new Insets(25,0,0,25));
        hdfs.getChildren().addAll(vdfs,box2D,box3D);

        buttonFileDfs.setOnAction(e ->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            try {
                textFBfs.setText(selectedFile.getPath());
                textFDfs.setText(selectedFile.getPath());
            }catch (Exception f){
                System.out.println("You didnt select a file");
            }
        });


        profB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    profB.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        profD.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    profD.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        ObservableList<String[]> observTB = FXCollections.observableArrayList();

        launchB.setOnAction(e->{
            try{
                ArrayList<String> dataList= fileToArraylist(textFBfs.getText());
                String[] parameters= dataList.get(7).split(" ");
                String[][] data=listToMatrix(dataList);
                BFS bfs= new BFS(data,Integer.parseInt(parameters[2]),Integer.parseInt(profB.getText()));
                bfs.printMatrix();
                int[]result=bfs.rechercheEnLargeur();
            }catch (Exception f){
                System.out.println(f);
            }
        });

        buttonFileBfs.setOnAction(e ->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            try {
                textFBfs.setText(selectedFile.getPath());
                textFDfs.setText(selectedFile.getPath());
                ArrayList<String> dataList= fileToArraylist(textFBfs.getText());
                String[] parameters= dataList.get(7).split(" ");
                String[][] data=listToMatrix(dataList);
                observTB.addAll(Arrays.asList(data));
                tableViewB.setItems(observTB);
            }catch (Exception f){
                System.out.println("You didnt select a file");
            }
        });


        launchD.setOnAction(e->{
            System.out.println("Executing DFS");
        });



        //Linking  the panels with their tabs
        Tab tab1 = new Tab("BFS", hbfs);
        Tab tab2 = new Tab("DFS"  ,hdfs );
        Tab tab3 = new Tab("A-star" , new Label("A star tab"));
        tabPane.setTabMinWidth(100);
        tabPane.getTabs().addAll(tab1,tab2,tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        VBox vBox = new VBox(tabPane);



        mainScene=new Scene(vBox,800,400);
        primaryStage.setTitle("Meta-heuristic project");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }


    public static ArrayList fileToArraylist(String file) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(file));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line); line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
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

}
