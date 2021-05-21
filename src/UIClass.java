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


public class UIClass{

    ArrayList<String> dataList;
    String[][] data;
    String[] parameters;
    //Creating all the variables for all the UI panels
    Scene mainScene;
    TabPane tabPane = new TabPane();
    FileChooser fileChooser = new FileChooser();
    FileChooser fileChooser2= new FileChooser();
    FileChooser fileChooser3= new FileChooser();
    Button buttonFileBfs= new Button("Choose");
    TextField textFBfs= new TextField();
    TextField profB= new TextField();
    Button launchB= new Button("Launch");
    Button buttonFileDfs= new Button("Choose");
    TextField textFDfs= new TextField();
    TextField profD= new TextField();
    Button launchD= new Button("Launch");
    Button buttonFileAs = new Button("Choose");
    TextField textFAs= new TextField();
    ComboBox comboAs= new ComboBox();
    Button launchA= new Button("Launch");
    TableView<Variable> tableViewB = new TableView();
    TableColumn column1B = new TableColumn<>("Premier x");
    TableColumn column2B = new TableColumn<>("Deuxiéme x");
    TableColumn column3B = new TableColumn<>("Troisiéme x");
    TableView<Variable> tableViewD = new TableView();
    TableColumn column1D = new TableColumn<>("Premier x");
    TableColumn column2D = new TableColumn<>("Deuxiéme x");
    TableColumn column3D = new TableColumn<>("Troisiéme x");
    TableView<Variable> tableViewA = new TableView();
    TableColumn column1A = new TableColumn<>("Premier x");
    TableColumn column2A = new TableColumn<>("Deuxiéme x");
    TableColumn column3A = new TableColumn<>("Troisiéme x");
    ListView listViewB = new ListView();
    ListView listViewD = new ListView();
    ListView listViewA = new ListView();
    Label resultB= new Label("");
    Label resultD= new Label("");
    Label resultA= new Label("");
    Label elapsedB= new Label("");
    Label elapsedD= new Label("");
    Label elapsedA= new Label("");

    ObservableList<String> heuristics =
            FXCollections.observableArrayList(
                    "Heuristic 1",
                    "Heuristic 2",
                    "Heuristic 3"
            );


    public UIClass(Stage primaryStage){

        fileChooser.setTitle("Choose CNF File");
        fileChooser.setInitialDirectory(new File("cnfs"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF Files", "*.cnf"));

        fileChooser2.setTitle("Choose CNF File");
        fileChooser2.setInitialDirectory(new File("cnfs"));
        fileChooser2.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF Files", "*.cnf"));

        fileChooser3.setTitle("Choose CNF File");
        fileChooser3.setInitialDirectory(new File("cnfs"));
        fileChooser3.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF Files", "*.cnf"));

        textFDfs.setEditable(false);textFBfs.setEditable(false);textFAs.setEditable(false);
        tableViewB.setMaxSize(300,180);tableViewD.setMaxSize(300,180);
        tableViewA.setMaxSize(300,180);

        column1B.setMinWidth(100);
        column2B.setMinWidth(100);
        column3B.setMinWidth(100);

        column1D.setMinWidth(100);
        column2D.setMinWidth(100);
        column3D.setMinWidth(100);

        column1A.setMinWidth(100);
        column2A.setMinWidth(100);
        column3A.setMinWidth(100);

        listViewB.setMaxHeight(215);listViewD.setMaxHeight(215);listViewA.setMaxHeight(215);


        //Creating BFS tab
        GridPane gridBfs= new GridPane();gridBfs.setHgap(25);gridBfs.setVgap(25);

        HBox hBoxFBfs= new HBox();hBoxFBfs.getChildren().addAll(textFBfs,buttonFileBfs);

        gridBfs.add(new Label("Data : "),0,0);
        gridBfs.add(hBoxFBfs,1,0);
        gridBfs.add(new Label("Depth : "),0,1);
        gridBfs.add(profB,1,1);
        gridBfs.add(launchB,1,2);

        GridPane gridAs= new GridPane();gridAs.setHgap(25);gridAs.setVgap(25);

        HBox hBoxFAs= new HBox();hBoxFAs.getChildren().addAll(textFAs, buttonFileAs);

        gridAs.add(new Label("Data : "),0,0);
        gridAs.add(hBoxFAs,1,0);
        gridAs.add(new Label("heuristic : "),0,1);
        gridAs.add(comboAs,1,1);
        gridAs.add(launchA,1,2);

        column1B.setCellValueFactory(new PropertyValueFactory<Variable,String>("x1"));
        column2B.setCellValueFactory(new PropertyValueFactory<Variable,String>("x2"));
        column3B.setCellValueFactory(new PropertyValueFactory<Variable,String>("x3"));

        column1D.setCellValueFactory(new PropertyValueFactory<Variable,String>("x1"));
        column2D.setCellValueFactory(new PropertyValueFactory<Variable,String>("x2"));
        column3D.setCellValueFactory(new PropertyValueFactory<Variable,String>("x3"));

        column1A.setCellValueFactory(new PropertyValueFactory<Variable,String>("x1"));
        column2A.setCellValueFactory(new PropertyValueFactory<Variable,String>("x2"));
        column3A.setCellValueFactory(new PropertyValueFactory<Variable,String>("x3"));


        tableViewB.getColumns().addAll(column1B,column2B,column3B);
        tableViewD.getColumns().addAll(column1D,column2D,column3D);
        tableViewA.getColumns().addAll(column1A,column2A,column3A);

        VBox vbfs= new VBox();vbfs.setSpacing(20);
        vbfs.getChildren().addAll(gridBfs,tableViewB);

        VBox box2= new VBox();box2.setSpacing(20);

        box2.getChildren().addAll(new Label("Complexité temporelle : O(b^d)"),
                new Label("Complexité spatiale : O(b^d)"),
                new Label("Current variables : "),listViewB);

        VBox box3= new VBox();box3.setSpacing(20);
        box3.getChildren().addAll(new Label("Result : "),elapsedB, resultB);

        HBox hbfs= new HBox();hbfs.setSpacing(30);hbfs.setPadding(new Insets(25,0,0,25));
        hbfs.getChildren().addAll(vbfs,box2,box3);


        VBox vAs= new VBox();vAs.setSpacing(20);
        vAs.getChildren().addAll(gridAs,tableViewA);

        VBox box2A= new VBox();box2A.setSpacing(20);

        box2A.getChildren().addAll(new Label("Complexité temporelle : O()"),
                new Label("Complexité spatiale : O()"),
                new Label("Current variables : "),listViewA);

        VBox box3A= new VBox();box3A.setSpacing(20);
        box3A.getChildren().addAll(new Label("Result : "),elapsedA, resultA);

        HBox hAs= new HBox();hAs.setSpacing(30);hAs.setPadding(new Insets(25,0,0,25));
        hAs.getChildren().addAll(vAs,box2A,box3A);



        //Creating DFS tab
        GridPane gdfs= new GridPane();gdfs.setVgap(25);gdfs.setHgap(25);

        HBox hBoxFDfs= new HBox();hBoxFDfs.getChildren().addAll(textFDfs,buttonFileDfs);

        gdfs.add(new Label("Data : "),0,0);
        gdfs.add(hBoxFDfs,1,0);
        gdfs.add(new Label("Depth : "),0,1);
        gdfs.add(profD,1,1);
        gdfs.add(launchD,1,2);


        VBox vdfs= new VBox();vdfs.setSpacing(20);
        vdfs.getChildren().addAll(gdfs,tableViewD);

        VBox box2D= new VBox();box2D.setSpacing(20);

        box2D.getChildren().addAll(new Label("Complexité temporelle : O(b^d)"),
                new Label("Complexité spatiale : O(b^d)"),
                new Label("Current variables : "),listViewD);

        VBox box3D= new VBox();box3D.setSpacing(20);
        box3D.getChildren().addAll(new Label("Result : "),elapsedD, resultD);

        HBox hdfs= new HBox();hdfs.setSpacing(30);hdfs.setPadding(new Insets(25,0,0,25));
        hdfs.getChildren().addAll(vdfs,box2D,box3D);

        listViewD.setId("D");listViewB.setId("B");listViewA.setId("A");


        //creating Astar tab
        comboAs.setItems(heuristics);


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

        int[] variables= new int[75];
        for(int i=0;i<variables.length;i++){
            variables[i]=-1;
            listViewB.getItems().add("X"+i+": "+variables[i]);
            listViewD.getItems().add("X"+i+": "+variables[i]);
            listViewA.getItems().add("X"+i+": "+variables[i]);
        }

        launchB.setOnAction(e->{
            try{
                dataList= fileToArraylist(textFBfs.getText());
                parameters= dataList.get(7).split(" ");
                data=listToMatrix(dataList);

                BFS bfs= new BFS(data,Integer.parseInt(parameters[2]),Integer.parseInt(profB.getText()));
                long start = System.nanoTime();
                int[]result=bfs.rechercheEnLargeur();
                long end = System.nanoTime();
                float elapsedTime = (float) (end - start)/1000000000;
                updateList(listViewB,result,elapsedTime);
            }catch (Exception f){
                System.out.println(f);
            }
        });

        buttonFileBfs.setOnAction(e ->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
        });


        launchD.setOnAction(e->{
            try{
                System.out.println("Executing DFS");
                dataList= fileToArraylist(textFBfs.getText());
                parameters= dataList.get(7).split(" ");
                data=listToMatrix(dataList);
                DFS dfs= new DFS(data,Integer.parseInt(parameters[2]),Integer.parseInt(profD.getText()));
                long start = System.nanoTime();
                int [] DFSResult = dfs.rechercheEnProfondeur();
                long end = System.nanoTime();
                float elapsedTime = (float) (end - start)/1000000000;
                updateList(listViewD,DFSResult,elapsedTime);

            }catch (Exception f){
                System.out.println(f);
            }
        });

        buttonFileDfs.setOnAction(e ->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
        });

        launchA.setOnAction(e->{
            System.out.println("Executing A star");
        });

        buttonFileAs.setOnAction(e ->{
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            updateTab(selectedFile.getPath());
        });


        //Linking  the panels with their tabs
        Tab tab1 = new Tab("BFS", hbfs);
        Tab tab2 = new Tab("DFS"  ,hdfs );
        Tab tab3 = new Tab("A-star" , hAs);
        tabPane.setTabMinWidth(100);
        tabPane.getTabs().addAll(tab1,tab2,tab3);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        VBox vBox = new VBox(tabPane);



        mainScene=new Scene(vBox,800,400);
        primaryStage.setTitle("Meta-heuristic project");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public void updateTab(String path){
        textFBfs.setText(path);
        textFDfs.setText(path);
        textFAs.setText(path);

        try{
            dataList= fileToArraylist(textFBfs.getText());
            data=listToMatrix(dataList);
            tableViewB.setItems(arrayToOBS(data));
            tableViewD.setItems(arrayToOBS(data));
            tableViewA.setItems(arrayToOBS(data));
        }catch (Exception e){
            System.out.println("You didnt select a file");
        }
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

    public void updateList(ListView listView, int[] array,float elapsedTime){

        if(listView.getId()==listViewB.getId()){
            elapsedB.setText("Elapsed time : "+elapsedTime+" s");
            if(array!=null){
                for(int i=0;i<array.length;i++){
                    listView.getItems().set(i,"X"+i+": "+array[i]);
                }
                resultB.setText("Our CNF is infered\n by the current variables.");
            }
            else {
                resultB.setText("BFS Couldnt find variables\n to infer CNF ");
            }
        }
        else if(listView.getId()==listViewD.getId()){
            elapsedD.setText("Elapsed time : "+elapsedTime+" s");
            if(array!=null){
                for(int i=0;i<array.length;i++){
                    listView.getItems().set(i,"X"+i+": "+array[i]);
                }
                resultD.setText("Our CNF is infered\n by the current variables.");
            }
            else {
                resultD.setText("DFS Couldnt find variables\n to infer CNF ");
            }
        }


    }

    public ObservableList<Variable> arrayToOBS(String[][] data){
        ObservableList<Variable> observTB = FXCollections.observableArrayList();
        for(int i=0;i<data.length;i++){
            observTB.add(new Variable(data[i][0],data[i][1],data[i][2]));
        }
        return observTB;
    }

}
