package Interface;

import MultiAgent.Agent;
import MultiAgent.Terrain;
import MultiAgent.Zombie;
import com.sun.javafx.collections.ElementObservableListDecorator;
import com.sun.javafx.collections.TrackableObservableList;
import com.sun.javafx.collections.VetoableListDecorator;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fenetre extends Application {

    private final int NUM_COLS = 8;
    Terrain t = new Terrain(30, 30);

    private final TimeCounter counter = new TimeCounter();

    @Override
    public void start(Stage primaryStage) throws Exception {

        t.initialiser();
        final GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setId("pane");
        
        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        initGrille(gridPane);

        // Add 250 rows to test performance:
        final Button addButton = new Button("Start/Stop");
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        t.deplacerLesAgents();
                        System.out.println(t);
                        step(gridPane);

                    }

                }),
                new KeyFrame(Duration.millis(100))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //gridPane.getChildren().clear();

                //t.deplacerLesAgents();
                //step(gridPane);
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    timeline.pause();
                } else {
                    timeline.play();
                }

            }
        });

        final BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        root.setBottom(addButton);

        Scene scene = new Scene(root, 602, 628);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.show();
        counter.reset();
        //timeline.play();
    }

    private void initGrille(GridPane root) {
        for (int i = 0; i < 30; i++) {
            ColumnConstraints column1 = new ColumnConstraints(20);
            RowConstraints row = new RowConstraints(20);
            root.getRowConstraints().add(row);
            root.getColumnConstraints().add(column1);

        }
        step(root);
    }

    private void step(GridPane root) {

        Vector<Node> childrens2 = new Stack<>();
        ObservableList<Node> childrens = root.getChildren();

//        for (Node node : childrens) {
//            if (node instanceof ImageView) {
//                childrens2.add(node);
//            }
//        }
//        for (Node node : childrens2) {
//            root.getChildren().remove(node);
//
//        }
        int c = 0;
        root.getChildren().clear();
        Image image1 = new Image("interface/waa.gif", 20, 20, false, false);
        Image image2 = new Image("interface/kef.png", 20, 20, false, false);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {

                if (t.map[i][j] != null) {
                    ImageView iv1 = new ImageView();
                    if(t.map[i][j]instanceof Zombie)
                    {
                    iv1.setImage(image2); 
                    }
                    else iv1.setImage(image1);
                    

                    root.add(iv1, j, i);

                }

            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    class TimeCounter {

        private long start = new Date().getTime();

        void reset() {
            start = new Date().getTime();
        }

        long elapsed() {
            return new Date().getTime() - start;
        }
    }
}
