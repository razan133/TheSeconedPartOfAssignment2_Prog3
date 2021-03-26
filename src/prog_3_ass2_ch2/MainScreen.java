/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog_3_ass2_ch2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author aashgar
 */
public class MainScreen extends Stage {

    private MenuBar menuBar;
    private Menu menuFile, menuColor, menuabout;
    private MenuItem menuItemOpen, menuItemClose, menuItemSave, menuItemExit, cyan, gold, red, about;
    private TextArea textArea;
    private Slider sliderFontSize;
    public ComboBox<String> comboBoxLinks;
    private GridPane gridPane;
    Label myName, id, course;

    public MainScreen() {
        myName = new Label();
        myName.setText("Razan Ramy Abu Hamda .");
        id = new Label();
        id.setText("220182500 .");
        course = new Label();
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-font-size:30px");

        course.setText("Programming 3 .");
        gridPane.add(myName, 0, 0);
        gridPane.add(id, 0, 1);
        gridPane.add(course, 0, 2);
        menuBar = new MenuBar();

        menuFile = new Menu("File");

        menuabout = new Menu("About");
        about = new MenuItem("About Me");

        Scene s = new Scene(gridPane, 500, 500);

//        Scene new_Sce = new Scene(gridPane);
        Stage new_Sta = new Stage();
        new_Sta.setScene(s);
        new_Sta.setTitle("About ME");

        menuabout.getItems().add(about);
        about.setOnAction(e -> {
            new_Sta.show();

        });

        menuItemOpen = new MenuItem("Open");

        menuItemClose = new MenuItem("Close");
        menuItemClose.setOnAction(e -> {
            textArea.clear();
        });

        menuItemSave = new MenuItem("Save");

        menuItemSave.setOnAction(e -> {
            String area = textArea.getText();
            double slid = sliderFontSize.getValue();
            String combo = comboBoxLinks.getSelectionModel().getSelectedItem();
            ArrayList<String> arrSave = new ArrayList();
            if (validate(area) && validate(String.valueOf(slid)) && validate(combo)) {
                arrSave.add(area);
                arrSave.add(String.valueOf(slid));
                arrSave.add(combo);
            }
            Stage sa = new Stage();
            FileChooser sav = new FileChooser();
            FileWriter writer = null ;
            try {
                writer = new FileWriter("output.txt");
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (String str : arrSave) {
                try {
                    writer.write(str);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (sav != null) {
                File SelectedFile = sav.showSaveDialog(sa);
            }

        });

        menuItemExit = new MenuItem("Exit");
        menuItemExit.setOnAction(e -> {
            this.close();
        });

        menuColor = new Menu("Color");
        cyan = new MenuItem("Cyn");
        cyan.setOnAction(e -> {
            textArea.setStyle("-fx-text-fill:cyan");

        });
        gold = new MenuItem("Gold");
        gold.setOnAction(e -> {
            textArea.setStyle("-fx-text-fill:gold");

        });
        red = new MenuItem("Red");
        red.setOnAction(e -> {
            textArea.setStyle("-fx-text-fill:red");

        });
        menuColor.getItems().addAll(cyan, new SeparatorMenuItem(), gold, new SeparatorMenuItem(), red);

        menuFile.getItems().addAll(menuItemOpen,
                new SeparatorMenuItem(), menuItemSave, new SeparatorMenuItem(), menuItemClose, new SeparatorMenuItem(), menuItemExit);
        menuBar.getMenus().addAll(menuFile, menuColor, menuabout);
        menuItemOpen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("src/prog_3_ass2_ch2"));
            File selectedFile = fileChooser.showOpenDialog(this);
            textArea.appendText("\n");
            try {
                Scanner scanner = new Scanner(selectedFile);
                while (scanner.hasNext()) {
                    textArea.appendText(scanner.nextLine() + "\n");
                }
                scanner.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
//        menuItemOpen.setOnAction(e -> {
//            this.close();
//        });

        textArea = new TextArea("Playing with JavaFX Advanced Controls");
        textArea.setMinHeight(160);
        sliderFontSize = new Slider(5, 35, 12);
        sliderFontSize.setMajorTickUnit(5);
        sliderFontSize.setMinorTickCount(4);
        sliderFontSize.setShowTickLabels(true);
        sliderFontSize.setShowTickMarks(true);
        sliderFontSize.setSnapToTicks(true);
        sliderFontSize.valueProperty().addListener(e -> {
            textArea.setStyle("-fx-font-size:" + sliderFontSize.getValue() + "pt");
        });
//        ComboBox<String> comboBoxLinks = new ComboBox<>();
//        
//        comboBoxLinks.getItems().addAll("Instructor GitHub", "JavaFX Guide", "Razan's GitHub");
//        
        String arr[] = new String[]{"Instructor GitHub", "JavaFX Guide", "Razan's GitHub"};
//        comboBoxLinks.getItems().addAll("Instructor GitHub", "JavaFX Guide", "Razan's GitHub");
        ObservableList<String> ob = FXCollections.observableArrayList(arr);
        comboBoxLinks = new ComboBox<>(ob);
        WebView webView = new WebView();
        comboBoxLinks.setOnAction(e -> {;
            String ss = comboBoxLinks.getSelectionModel().getSelectedItem();
            if (ss.equalsIgnoreCase("Instructor GitHub")) {
                webView.getEngine().load("https://github.com/aashgar");
            } else if (ss.equalsIgnoreCase("JavaFX Guide")) {
                webView.getEngine().load("https://github.com/aashgar");
            } else if (ss.equalsIgnoreCase("Razan's GitHub")) {
                webView.getEngine().load("https://github.com/razan133");
            }
        });
        comboBoxLinks.getSelectionModel().selectFirst();

        HBox hBox1 = new HBox(15, comboBoxLinks, webView);

        VBox vBox1 = new VBox(15, textArea, sliderFontSize, hBox1);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(vBox1);

        Scene scene = new Scene(borderPane, 1000, 600);
        setScene(scene);
        setTitle("Advanced JavaFX Screen");
    }

    public boolean validate(String input) {
        return !input.equals("");
    }

}
