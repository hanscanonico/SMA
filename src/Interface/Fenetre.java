package Interface;

import MultiAgent.Humain;
import MultiAgent.Terrain;
import MultiAgent.Zombie;
import java.util.Date;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fenetre extends Application {

    //taille des cases
    private int taille = 20;
    private int nbCol = 50;
    private int nbRow = 30;
    private Terrain terrain = new Terrain(nbRow, nbCol);

    private final Button playButton = new Button("Play/Pause");
    private final Button stepButton = new Button("Step");
    private final Label labelHumain = new Label("Humain :");
    private final Label labelZombie = new Label("Zombie :");
    private Label labelCountHumain = new Label();
    private Label labelCountZombie = new Label();
    private final GridPane gridPane = new GridPane();
    private final BorderPane rootPane = new BorderPane();
    private Timeline timeline;
    private final FlowPane flowPaneBottom = new FlowPane();
    private final Scene scene = new Scene(rootPane, nbCol * taille, nbRow * taille + 20);

    private final Image imgHumain = new Image("interface/Images/humain.png", 20, 20, false, false);
    private final Image imgZombie = new Image("interface/Images/zombie.png", 20, 20, false, false);

    public Fenetre() {

    }

//    public Fenetre(int col, int row, int nbAgent) {
//
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        terrain.initialiser();

        gridPane.setGridLinesVisible(true);
        gridPane.setId("pane");
        gridPane.setMaxSize(nbCol * taille, nbRow * taille);
        initGrille(gridPane);

        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        terrain.deplacerLesAgents();
                        // System.out.println(t);
                        refresh(gridPane);

                    }

                }),
                new KeyFrame(Duration.millis(50))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    timeline.pause();
                }
                terrain.deplacerLesAgents();
                refresh(gridPane);
            }
        });

        playButton.setOnAction(new EventHandler<ActionEvent>() {
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

        scene.getStylesheets().addAll(this.getClass().getResource("Images/style.css").toExternalForm());

        rootPane.setCenter(gridPane);
        rootPane.setBottom(flowPaneBottom);

        flowPaneBottom.getChildren().addAll(playButton, stepButton, labelHumain
        ,labelCountHumain,labelZombie, labelCountZombie);
        
        flowPaneBottom.setAlignment(Pos.CENTER);
        flowPaneBottom.setHgap(10);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation Multi Agent");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Initialise la grille en mettant en place les lignes et les colones du
     * gridpane à la bonne taille
     *
     * @param root
     */
    private void initGrille(GridPane root) {
        for (int i = 0; i < nbCol; i++) {
            ColumnConstraints column = new ColumnConstraints(taille);
            root.getColumnConstraints().add(column);
        }
        for (int j = 0; j < nbRow; j++) {
            RowConstraints row = new RowConstraints(taille);
            root.getRowConstraints().add(row);
        }
        refresh(root);
    }

    /**
     * Mise a jour de la position des agents
     *
     * @param root
     */
    private void refresh(GridPane root) {

        root.getChildren().clear();
        int nbHumain = 0;
        int nbZombie = 0;

        for (int i = 0; i < nbRow; i++) {
            for (int j = 0; j < nbCol; j++) {

                if (terrain.map[i][j] != null) {
                    ImageView imgView = new ImageView();
                    if (terrain.map[i][j] instanceof Zombie) {
                        imgView.setImage(imgZombie);
                        nbZombie++;
                    } else if (terrain.map[i][j] instanceof Humain) {
                        imgView.setImage(imgHumain);
                        nbHumain++;
                    }

                    root.add(imgView, j, i);

                }
                

            }
        }
        labelCountHumain.setText(Integer.toString(nbHumain));
        labelCountZombie.setText(Integer.toString(nbZombie));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
