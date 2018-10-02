package VMachine;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindows
{


    public static void cartErrorWindow()
    {

        Stage errorStage = new Stage();
        errorStage.setTitle("Error - Cart Empty");
        errorStage.setResizable(false);


        Label errorMsg = new Label("You did not add items to your cart");


        Button okButton = new Button("Ok");
        okButton.setOnAction(event -> errorStage.close());

        //layout
        GridPane layout = new GridPane();
        layout.setMinSize(250,100);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(errorMsg, 1,0);
        layout.add(okButton,1,2);


        layout.setHalignment(okButton,HPos.CENTER);

        Scene errorScene = new Scene(layout);

        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setScene(errorScene);
        errorStage.showAndWait();

    }




    public static void itemSelectErrorWindow()
    {


         Button okButton = new Button("Ok");
         Stage errorStage = new Stage();
         errorStage.setTitle("Error - Item Selection");

         Label errorMsg = new Label("\t\tYou did not select an item.\nPlease select an item from the drop down menu");


        okButton.setOnAction(event -> errorStage.close());

        //layout
        GridPane layout = new GridPane();
        layout.setMinSize(300,100);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(errorMsg, 1,0);
        layout.add(okButton,1,1);

        layout.setHalignment(okButton,HPos.CENTER);


        Scene errorScene = new Scene(layout);


        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setScene(errorScene);
        errorStage.showAndWait();



    }// END ITEM SELECTED ERROR WINDOW METHOD



    public static void multiplePaymentTypeErrorWindow()
    {

        Button okButton = new Button("Ok");
        Stage errorStage = new Stage();
        errorStage.setTitle("Error - Multiple Payment Types");

        Label errorMsg = new Label("\t\tSelect only one method of payment to continue");


        okButton.setOnAction(event -> errorStage.close());

        //layout
        GridPane layout = new GridPane();
        layout.setMinSize(300,100);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(errorMsg, 1,0);
        layout.add(okButton,1,1);

        layout.setHalignment(okButton,HPos.CENTER);


        Scene errorScene = new Scene(layout);


        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setScene(errorScene);
        errorStage.showAndWait();

    }




    public static void cardNumberLengthErrorWindow(int lengthCheck)
    {

        Button okButton = new Button("Ok");

        Label errorMsg;

        Stage errorStage = new Stage();
        errorStage.setTitle("Error - Card Number Length");
        errorStage.setResizable(false);



        okButton.setOnAction(event -> errorStage.close());

        GridPane layout = new GridPane();
        layout.setMinSize(300,100);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        if(lengthCheck > 10)
        {
            errorMsg = new Label("The card number you entered was invalid\n -Length may not exceed 10 digits");
            layout.add(errorMsg,0,0);
        }
        if(lengthCheck < 10)
        {
            errorMsg = new Label("The card number you entered was invalid\n -Length must equal 10 digits");
            layout.add(errorMsg,0,0);
        }

        layout.add(okButton,0,1);
        layout.setHalignment(okButton,HPos.CENTER);


        Scene errorScene = new Scene(layout);


        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setScene(errorScene);
        errorStage.showAndWait();

    } //END CARD NUMBER LENGTH ERROR WINDOW


    public static void cashAmountError(int cashAmountEntered)
    {
        Button okButton = new Button("Ok");

        Label errorMsg;

        Stage errorStage = new Stage();
        errorStage.setTitle("Error - Cash Amount");
        errorStage.setResizable(false);



        okButton.setOnAction(event -> errorStage.close());

        GridPane layout = new GridPane();
        layout.setMinSize(300,100);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);



        if(cashAmountEntered == 0)
        {
            errorMsg = new Label("You did not enter an amount\nPress ok to return to the payment window");
            layout.add(errorMsg,0,0);

        }

        if(cashAmountEntered >0 )
        {
            errorMsg = new Label("Insufficient funds\nPress ok to return to the payment window");
            layout.add(errorMsg,0,0);
        }

        layout.add(okButton,0,1);


        layout.setHalignment(okButton,HPos.CENTER);

        Scene errorScene = new Scene(layout);


        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setScene(errorScene);
        errorStage.showAndWait();

    } //END CASH AMOUNT ERROR METHOD

} // END ERROR WINDOWS CLASS
