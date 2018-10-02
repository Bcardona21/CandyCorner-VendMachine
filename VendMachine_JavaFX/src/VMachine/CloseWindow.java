package VMachine;



import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;




public class CloseWindow
{



    public static void closeAll()
    {



        Stage closeStage = new Stage();
        closeStage.setTitle("Close window");
        closeStage.setResizable(false);


        Label text = new Label("Are you sure you want to quit?");



        Button yesButton = new Button("Yes");
        yesButton.setOnAction(event -> System.exit(0));



        Button noButton = new Button("No");
        noButton.setOnAction(event -> closeStage.close());


        //layout
        GridPane layout = new GridPane();
        layout.setMinSize(250,100);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);


        layout.add(text, 1,1);
        layout.add(yesButton,1,3);
        layout.add(noButton, 1,3);

        layout.setHalignment(yesButton, HPos.LEFT);
        layout.setHalignment(noButton, HPos.RIGHT);



        Scene closeWindow = new Scene(layout);


        closeStage.initModality(Modality.APPLICATION_MODAL);

        closeStage.setScene(closeWindow);
        closeStage.showAndWait();


    } //END CLOSEALL METHOD




} //END CLOSEWINDOW CLASS
