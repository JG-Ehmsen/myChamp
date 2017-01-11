package mychamp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mychamp.BLL.MatchManager;

public class MyChamp extends Application
{
    MatchManager matchManager = MatchManager.getInstance();

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/FrontView.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("MyChamp");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    
    }

}
