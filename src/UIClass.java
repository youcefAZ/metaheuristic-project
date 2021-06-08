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
    FileChooser fileChooser;

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

    Button buttonFilePfs= new Button("Choose");
    TextField textFPfs= new TextField();
    TextField nbPar= new TextField();
    TextField nbIter= new TextField();
    TextField vmaxField= new TextField();
    TextField wField= new TextField();
    TextField c1Field= new TextField();
    TextField c2Field= new TextField();
    Button launchP = new Button("Launch");

    Button buttonFileGfs= new Button("Choose");
    TextField textFGfs= new TextField();
    TextField nbGen= new TextField();
    TextField nbPop= new TextField();
    TextField crossRate= new TextField();
    TextField mutRate= new TextField();
    Button launchG = new Button("Launch");



    TableView<Variable> tableViewB;
    TableView<Variable> tableViewD;
    TableView<Variable> tableViewA;
    TableView<Variable> tableViewP;
    TableView<Variable> tableViewG;

    ListView listViewB = new ListView();
    ListView listViewD = new ListView();
    ListView listViewA = new ListView();
    ListView listViewP= new ListView();
    ListView listViewG= new ListView();

    Label resultB= new Label("");
    Label resultD= new Label("");
    Label resultA= new Label("");
    Label resultP= new Label("");
    Label resultG= new Label("");
    Label elapsedB= new Label("");
    Label elapsedD= new Label("");
    Label elapsedA= new Label("");
    Label elapsedP= new Label("");
    Label elapsedG= new Label("");
    Label maxTimeB= new Label("Max time : (s)");
    Label maxTimeD= new Label("Max time : (s)");
    Label maxTimeA= new Label("Max time : (s)");
    TextField testMaxTimeB= new TextField();
    TextField testMaxTimeD= new TextField();
    TextField testMaxTimeA= new TextField();


    ObservableList<String> heuristics =
            FXCollections.observableArrayList(
                    "TD Heuristic",
                    "TD-long heuristic",
                    "Partial-diff Heuristic",
                    "Full-diff Heuristic"
            );


    public UIClass(Stage primaryStage){

        fileChooser=setFileChooser();

        textFDfs.setEditable(false);textFBfs.setEditable(false);
        textFAs.setEditable(false);textFPfs.setEditable(false);textFGfs.setEditable(false);


        listViewB.setMaxHeight(265);listViewD.setMaxHeight(265);
        listViewA.setMaxHeight(265);listViewA.setMaxHeight(265);listViewG.setMaxHeight(265);
        listViewD.setId("D");listViewB.setId("B");
        listViewA.setId("A");listViewP.setId("P");listViewG.setId("G");


        //BFS 1st part
        GridPane gridBfs= new GridPane();gridBfs.setHgap(25);gridBfs.setVgap(25);

        HBox hBoxFBfs= new HBox();hBoxFBfs.getChildren().addAll(textFBfs,buttonFileBfs);

        gridBfs.add(new Label("Data : "),0,0);
        gridBfs.add(hBoxFBfs,1,0);
        gridBfs.add(new Label("Depth : "),0,1);
        gridBfs.add(profB,1,1);
        gridBfs.add(maxTimeB,0,2);
        gridBfs.add(testMaxTimeB,1,2);
        gridBfs.add(launchB,1,3);


        //A-star 1st part
        GridPane gridAs= new GridPane();gridAs.setHgap(25);gridAs.setVgap(25);
        HBox hBoxFAs= new HBox();hBoxFAs.getChildren().addAll(textFAs, buttonFileAs);

        gridAs.add(new Label("Data : "),0,0);
        gridAs.add(hBoxFAs,1,0);
        gridAs.add(new Label("heuristic : "),0,1);
        gridAs.add(comboAs,1,1);
        gridAs.add(maxTimeA,0,2);
        gridAs.add(testMaxTimeA,1,2);
        gridAs.add(launchA,1,3);

        //PSO 1st part
        GridPane gridPs= new GridPane();gridPs.setHgap(25);gridPs.setVgap(25);

        HBox hBoxFPs= new HBox();hBoxFPs.getChildren().addAll(textFPfs, buttonFilePfs);
        HBox hBoxDP= new HBox();hBoxDP.getChildren().addAll(new Label("Data : "),hBoxFPs);
        HBox hBoxIterPs= new HBox();hBoxIterPs.getChildren().addAll(new Label("nbIter:"), nbIter);
        HBox hBoxParPs= new HBox();hBoxParPs.getChildren().addAll(new Label("nbP :"), nbPar);
        HBox hBoxVPs= new HBox();hBoxVPs.getChildren().addAll(new Label("Vmax :"), vmaxField);
        HBox hBoxWPs= new HBox();hBoxWPs.getChildren().addAll(new Label("w :   "), wField);
        HBox hBoxC1Ps= new HBox();hBoxC1Ps.getChildren().addAll(new Label("c1 :     "), c1Field);
        HBox hBoxC2Ps= new HBox();hBoxC2Ps.getChildren().addAll(new Label("c2 :  "), c2Field);

        gridPs.add(hBoxDP,0,0);
        gridPs.add(hBoxIterPs,0,1);
        gridPs.add(hBoxParPs,1,1);
        gridPs.add(hBoxVPs,0,2);
        gridPs.add(hBoxWPs,1,2);
        gridPs.add(hBoxC1Ps,0,3);
        gridPs.add(hBoxC2Ps,1,3);
        gridPs.add(launchP,0,4);


        //GA 1st part
        GridPane gridGs= new GridPane();gridGs.setHgap(25);gridGs.setVgap(25);
        HBox hBoxFGs= new HBox();hBoxFGs.getChildren().addAll(textFGfs, buttonFileGfs);

        gridGs.add(new Label("Data : "),0,0);
        gridGs.add(hBoxFGs,1,0);
        gridGs.add(new Label("nb population : "),0,1);
        gridGs.add(nbPop,1,1);
        gridGs.add(new Label("nb Gen : "),0,2);
        gridGs.add(nbGen,1,2);
        gridGs.add(new Label("cross-rate:"),0,3);
        gridGs.add(crossRate,1,3);
        gridGs.add(new Label("mut-rate:"),0,4);
        gridGs.add(mutRate,1,4);
        gridGs.add(launchG,1,5);


        tableViewB=setTableView();tableViewD=setTableView();
        tableViewA=setTableView(); tableViewP=setTableView();tableViewG=setTableView();


        //BFS 2nd PART
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


        //Astar 2nd part
        VBox vAs= new VBox();vAs.setSpacing(20);
        vAs.getChildren().addAll(gridAs,tableViewA);

        VBox box2A= new VBox();box2A.setSpacing(20);

        box2A.getChildren().addAll(new Label("Complexité temporelle pire cas: O(b^d)"),
                new Label("Complexité spatiale pire cas: O(b^d)"),
                new Label("Current variables : "),listViewA);

        VBox box3A= new VBox();box3A.setSpacing(20);
        box3A.getChildren().addAll(new Label("Result : "),elapsedA, resultA);

        HBox hAs= new HBox();hAs.setSpacing(30);hAs.setPadding(new Insets(25,0,0,25));
        hAs.getChildren().addAll(vAs,box2A,box3A);


        //PSO 2nd part
        VBox vPs= new VBox();vPs.setSpacing(20);
        vPs.getChildren().addAll(gridPs,tableViewP);

        VBox box2P= new VBox();box2P.setSpacing(20);

        box2P.getChildren().addAll(new Label("Complexité temporelle :"),
                new Label("Complexité spatiale:"),
                new Label("Current variables : "),listViewP);

        VBox box3P= new VBox();box3P.setSpacing(20);
        box3P.getChildren().addAll(new Label("Result : "),elapsedP, resultP);

        HBox hPs= new HBox();hPs.setSpacing(30);hPs.setPadding(new Insets(25,0,0,25));
        hPs.getChildren().addAll(vPs,box2P,box3P);

        //GA 2nd part
        VBox vGs= new VBox();vGs.setSpacing(20);
        vGs.getChildren().addAll(gridGs,tableViewG);

        VBox box2G= new VBox();box2G.setSpacing(20);

        box2G.getChildren().addAll(new Label("Complexité temporelle :"),
                new Label("Complexité spatiale:"),
                new Label("Current variables : "),listViewG);

        VBox box3G= new VBox();box3G.setSpacing(20);
        box3G.getChildren().addAll(new Label("Result : "),elapsedG, resultG);

        HBox hGs= new HBox();hGs.setSpacing(30);hGs.setPadding(new Insets(25,0,0,25));
        hGs.getChildren().addAll(vGs,box2G,box3G);


        //Creating DFS tab
        GridPane gdfs= new GridPane();gdfs.setVgap(25);gdfs.setHgap(25);

        HBox hBoxFDfs= new HBox();hBoxFDfs.getChildren().addAll(textFDfs,buttonFileDfs);

        gdfs.add(new Label("Data : "),0,0);
        gdfs.add(hBoxFDfs,1,0);
        gdfs.add(new Label("Depth : "),0,1);
        gdfs.add(profD,1,1);
        gdfs.add(maxTimeD,0,2);
        gdfs.add(testMaxTimeD,1,2);
        gdfs.add(launchD,1,3);


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


        comboAs.setItems(heuristics);


        numberOnly(profB);
        numberOnly(profD);
        numberOnly(testMaxTimeB);
        numberOnly(testMaxTimeD);
        numberOnly(testMaxTimeA);
        numberOnly(nbIter);numberOnly(nbPar);
        numberOnly(vmaxField);
        numberOnly(nbGen);numberOnly(nbGen);



        //setting up the actions for all buttons
        launchB.setOnAction(e->{
                setupAction();
                BFS bfs= new BFS(data,Integer.parseInt(parameters[2]),Integer.parseInt(parameters[4]),
                        Integer.parseInt(profB.getText()),Integer.parseInt(testMaxTimeB.getText()));
                long start = System.nanoTime();
                ReturnClass result=bfs.rechercheEnLargeur();
                long end = System.nanoTime();
                float elapsedTime = (float) (end - start)/1000000000;
                updateList(listViewB,result,elapsedTime);

        });

        buttonFileBfs.setOnAction(e ->{
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
            }catch (Exception c){
                System.out.println(c);
            }
        });


        launchD.setOnAction(e->{
                System.out.println("Executing DFS");
                setupAction();
                DFS dfs= new DFS(data,Integer.parseInt(parameters[2]),Integer.parseInt(parameters[4]),
                        Integer.parseInt(profD.getText()),Integer.parseInt(testMaxTimeD.getText()));
                long start = System.nanoTime();
                ReturnClass DFSResult = dfs.rechercheEnProfondeur();
                long end = System.nanoTime();
                float elapsedTime = (float) (end - start)/1000000000;
                updateList(listViewD,DFSResult,elapsedTime);
        });

        buttonFileDfs.setOnAction(e ->{
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
            }catch (Exception c){
                System.out.println(c);
            }
        });

        launchA.setOnAction(e->{
            System.out.println("Executing A star");
                setupAction();
                Astar astar= new Astar(data,Integer.parseInt(parameters[2]),Integer.parseInt(parameters[4]),
                        comboAs.getValue().toString(),Integer.parseInt(testMaxTimeA.getText()));

                long start = System.nanoTime();
                ReturnClass AstarRes=astar.astar_algo();
                long end = System.nanoTime();
                float elapsedTime = (float) (end - start)/1000000000;
                updateList(listViewA,AstarRes,elapsedTime);

        });

        buttonFileAs.setOnAction(e ->{
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
            }catch (Exception c){
                System.out.println(c);
            }
        });

        launchP.setOnAction(e->{
            setupAction();
            PSO pso= new PSO(data,Integer.parseInt(parameters[2]),Integer.parseInt(parameters[4]),
                    Integer.parseInt(nbPar.getText()),Integer.parseInt(nbIter.getText()),
                    Integer.parseInt(vmaxField.getText()),Float.parseFloat(c1Field.getText()),
                    Float.parseFloat(c2Field.getText()),Float.parseFloat(wField.getText()));

            long start = System.nanoTime();
            ReturnClass AstarRes=pso.psoAlgorithm();
            long end = System.nanoTime();
            float elapsedTime = (float) (end - start)/1000000000;
            updateList(listViewP,AstarRes,elapsedTime);

        });

        buttonFilePfs.setOnAction(e ->{
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
            }catch (Exception c){
                System.out.println(c);
            }
        });


        launchG.setOnAction(e->{
            setupAction();
            //use the algorithm here, its gonna be something like this :
            //Ga ga= new Ga(data,Integer.parseInt(parameters[2]),Integer.parseInt(parameters[4]),
            // Integer.parseInt(nbGen.getText),Integer.parseInt(nbPop.getText),Integer.parseInt(crossRate.getText),
            // Integer.parseInt(mutRate.getText));
            //ga.algo();
        });


        buttonFileGfs.setOnAction(e ->{
            try {
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                updateTab(selectedFile.getPath());
            }catch (Exception c){
                System.out.println(c);
            }
        });


        //Linking  the panels with their tabs
        Tab tab1 = new Tab("BFS", hbfs);
        Tab tab2 = new Tab("DFS"  ,hdfs );
        Tab tab3 = new Tab("A-star" , hAs);
        Tab tab4= new Tab("PSO",hPs);
        Tab tab5= new Tab("Genetic Algorithm", hGs);
        tabPane.setTabMinWidth(100);
        tabPane.getTabs().addAll(tab1,tab2,tab3,tab4,tab5);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        VBox vBox = new VBox(tabPane);



        mainScene=new Scene(vBox,825,450);
        primaryStage.setTitle("Meta-heuristic project");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }



    public void setupAction(){
        try {
            dataList= fileToArraylist(textFAs.getText());
            parameters= dataList.get(7).split(" ");
            data=listToMatrix(dataList,Integer.parseInt(parameters[4]));
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public void updateTab(String path){
        textFBfs.setText(path);
        textFDfs.setText(path);
        textFAs.setText(path);
        textFPfs.setText(path);

        try{
            dataList= fileToArraylist(textFBfs.getText());
            String[] parameters= dataList.get(7).split(" ");
            data=listToMatrix(dataList,Integer.parseInt(parameters[4]));
            initLists(Integer.parseInt(parameters[2]));
            tableViewB.setItems(arrayToOBS(data));
            tableViewD.setItems(arrayToOBS(data));
            tableViewA.setItems(arrayToOBS(data));
            tableViewP.setItems(arrayToOBS(data));
            tableViewG.setItems(arrayToOBS(data));
        }catch (Exception e){
            System.out.println("You didnt select a file");
        }
    }


    public FileChooser setFileChooser(){
        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Choose CNF File");
        //fileChooser.setInitialDirectory(new File("cnfs"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNF Files", "*.cnf"));

        return  fileChooser;
    }

    public TableView setTableView(){

        TableView tableView= new TableView();
        tableView.setMaxSize(300,180);

        TableColumn column1= new TableColumn("Premier x");
        TableColumn column2= new TableColumn("Deuxiéme x");
        TableColumn column3= new TableColumn("Troisiéme x");
        column1.setMinWidth(100);
        column2.setMinWidth(100);
        column3.setMinWidth(100);

        column1.setCellValueFactory(new PropertyValueFactory<Variable,String>("x1"));
        column2.setCellValueFactory(new PropertyValueFactory<Variable,String>("x2"));
        column3.setCellValueFactory(new PropertyValueFactory<Variable,String>("x3"));

        tableView.getColumns().addAll(column1,column2,column3);

        return tableView;
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

    public static String [][] listToMatrix(ArrayList<String> dataList,int length){
        String[][] data=new String[length][3];
        String t=dataList.get(8);
        if(dataList.get(8).charAt(0)==' '){
            t=dataList.get(8).substring(1);
        }

        String[] temp1=t.split(" ");
        for(int j=0;j<temp1.length-1;j++){
            data[0][j]=temp1[j];
        }

        for( int i=9;i<dataList.size()-3;i++){
            String[] temp=dataList.get(i).split(" ");
            for(int j=0;j<temp.length-1;j++){
                data[i-8][j]=temp[j];
            }
        }
        return data;
    }


    public void updateList(ListView listView, ReturnClass returnC,float elapsedTime){

        if(listView.getId()==listViewB.getId()){
            elapsedB.setText("Elapsed time : "+elapsedTime+" s");
            if(returnC!=null){
                listView.getItems().remove(0,listView.getItems().size());
                for(int i=0;i<returnC.vars.length;i++){
                    listView.getItems().add(i,"X"+i+": "+returnC.vars[i]);
                }
                resultB.setText("Satisfiability : "+returnC.satisfied);
            }
            else {
                resultB.setText("Satisfiability : false");
            }
        }
        else if(listView.getId()==listViewD.getId()){
            elapsedD.setText("Elapsed time : "+elapsedTime+" s");
            if(returnC!=null){
                listView.getItems().remove(0,listView.getItems().size());
                for(int i=0;i<returnC.vars.length;i++){
                    listView.getItems().add(i,"X"+i+": "+returnC.vars[i]);
                }
                resultD.setText("Satisfiability : "+returnC.satisfied);
            }
            else {
                resultD.setText("Satisfiability : false");
            }
        }
        else {
            elapsedA.setText("Elapsed time : "+elapsedTime+" s");
            if(returnC.vars!=null){
                listView.getItems().remove(0,listView.getItems().size());
                for(int i=0;i<returnC.vars.length;i++){
                    listView.getItems().add(i,"X"+i+": "+returnC.vars[i]);
                }
                resultA.setText("Satisfiability : "+returnC.satisfied+"\nnb Clauses satisfaite : "+returnC.score);
            }
            else {
                resultA.setText("Satisfiability : false");
            }
        }

    }


    public void initLists(int length){
        listViewB.getItems().remove(0,listViewD.getItems().size());
        listViewD.getItems().remove(0,listViewD.getItems().size());
        listViewA.getItems().remove(0,listViewD.getItems().size());

        int[] variables= new int[length];
        for(int i=0;i<length;i++){
            variables[i]=-1;
            listViewB.getItems().add("X"+i+": "+variables[i]);
            listViewD.getItems().add("X"+i+": "+variables[i]);
            listViewA.getItems().add("X"+i+": "+variables[i]);
        }
    }

    public void numberOnly(TextField textf){
        textf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }


    public ObservableList<Variable> arrayToOBS(String[][] data){
        ObservableList<Variable> observTB = FXCollections.observableArrayList();
        for(int i=0;i<data.length;i++){
            observTB.add(new Variable(data[i][0],data[i][1],data[i][2]));
        }
        return observTB;
    }

}
