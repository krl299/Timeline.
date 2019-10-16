/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 *
 * @author Carlos
 */
public class TTimeline extends Application {
    static double ball_speedX = 1;
    static double ball_speedY = 1;
    @Override
    public void start(Stage primaryStage) {
        Group pane=new Group();
        Circle pelota=new Circle(20);
        pelota.setTranslateX(300*0.5);
        pelota.setTranslateY(250*0.5);
        Label texto=new Label();
        
        Pane root = new Pane();
        Scene scene = new Scene(root, 300, 250);
        root.getChildren().add(pelota);
        root.getChildren().add(texto);
        
        EventHandler<ActionEvent> eH = e->{
            PerformanceTracker perfTracker=PerformanceTracker.getSceneTracker(scene);
            texto.setText("FPS (timeline) = " + perfTracker.getInstantFPS());
            
            if(pelota.getTranslateX()<0 ||pelota.getTranslateX()>scene.getWidth()){
               ball_speedX *= -1;
            }
            if(pelota.getTranslateY()<0 || pelota.getTranslateY()>scene.getHeight()){
                ball_speedY *= -1;
            }
            pelota.setTranslateX(pelota.getTranslateX() + ball_speedX);
            pelota.setTranslateY(pelota.getTranslateY() + ball_speedY);
        };
        
        Timeline animacion=new Timeline(new KeyFrame(Duration.millis(5), eH));
        animacion.setCycleCount(Timeline.INDEFINITE);
        animacion.play();
        
        primaryStage.setTitle("Time Line Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
