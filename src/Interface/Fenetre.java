package Interface;

import MultiAgent.Etoile;
import MultiAgent.Humain;
import MultiAgent.Terrain;
import MultiAgent.Zombie;
import MultiAgent.SuperHumain;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
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
    private final int taille = 20;
    private final int nbCol = 50;
    private final int nbRow = 30;
    private final Terrain terrain = Terrain.getInstance(nbRow, nbCol);
    private final Button resetButton = new Button("Reset");
    private final Button playButton = new Button("Play/Pause");
    private final Button stepButton = new Button("Step");
    private final Label labelHumain = new Label("Humain :");
    private final Label labelZombie = new Label("Zombie :");
    private final Label labelVitesse = new Label("Vitesse :");
    private final Label labelVision = new Label("Champ de vision :");
    private final Label labelCountHumain = new Label();
    private final Label labelCountZombie = new Label();
    private final GridPane gridPane = new GridPane();
    private final BorderPane rootPane = new BorderPane();
    private final Slider slider = new Slider(1, 100, 50);
    private Timeline timeline;
    private final FlowPane flowPaneBottom = new FlowPane();
    private final Scene scene = new Scene(rootPane, nbCol * taille, nbRow * taille + 25);
    private final CheckBox checkVision = new CheckBox();
    private final Separator separator1 = new Separator();
    private final Separator separator2 = new Separator();
    private final Separator separator3 = new Separator();

//    private final Image imgHumain = new Image("interface/Images/humain.png", 140, 140, false, true);
    private final Image imgHumain = new Image("interface/Images/humain.png", 20, 20, false, true);
    private final Image imgZombie = new Image("interface/Images/zombie.png", 20, 20, false, false);
    private final Image imgEtoile = new Image("interface/Images/Etoile.png", 20, 20, false, false);
    private final Image imgSuperHumain = new Image("interface/Images/superhumain.png", 20, 20, false, true);

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
        KeyFrame key2 = new KeyFrame(Duration.millis(50), new EventHandler() {
            @Override
            public void handle(Event event) {
                terrain.deplacerLesAgents();
                // System.out.println(t);
                refresh(gridPane);

            }

        });

        timeline = new Timeline(key2);

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

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (timeline.getStatus() == Animation.Status.RUNNING) {
                    timeline.pause();
                }
                terrain.reset();
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

        slider.setShowTickLabels(true);
        slider.setMin(1);
        slider.setMax(99);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {

                double temp = 100 - (double) new_val;
                KeyFrame key2 = new KeyFrame(Duration.millis(temp), new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        terrain.deplacerLesAgents();
                        // System.out.println(t);
                        refresh(gridPane);

                    }

                });

                timeline.stop();
                timeline = null;
                timeline = new Timeline(key2);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        });

        scene.getStylesheets().addAll(this.getClass().getResource("Images/style.css").toExternalForm());

        rootPane.setCenter(gridPane);
        rootPane.setBottom(flowPaneBottom);

        separator1.setOrientation(Orientation.VERTICAL);
        separator2.setOrientation(Orientation.VERTICAL);
        separator3.setOrientation(Orientation.VERTICAL);
        flowPaneBottom.getChildren().addAll(playButton, stepButton, resetButton,separator1, labelHumain, labelCountHumain, labelZombie, labelCountZombie,separator2, labelVitesse, slider, separator3, labelVision, checkVision);

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
                    } else if (terrain.map[i][j] instanceof SuperHumain) {
                        imgView.setImage(imgSuperHumain);
//                        imgView.setTranslateX(-60);
//                        imgView.setTranslateY(-60);
                        nbHumain++;
                    } else if (terrain.map[i][j] instanceof Humain) {
                        imgView.setImage(imgHumain);
                        nbHumain++;
                    } else if (terrain.map[i][j] instanceof Etoile) {
                        imgView.setImage(imgEtoile);
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
