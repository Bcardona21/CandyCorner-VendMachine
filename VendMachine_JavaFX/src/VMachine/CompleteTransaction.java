package VMachine;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompleteTransaction
{

    //payment with cash
    public static void dispenseItemsAndChange(int cashAmountEntered, double totalCost, Stage sentStage)
    {

        double changeDue = cashAmountEntered-totalCost;


        Stage cashPaidStage = new Stage();
        cashPaidStage.setTitle("Thank You!");
        cashPaidStage.setResizable(false);
        cashPaidStage.setOnCloseRequest(event -> {

            event.consume();
            CloseWindow.closeAll();

        });



        Label thankyouMsg = new Label("Thank you for your purchase!\nYour items have been dispensed");

        Label exitMsg = new Label("If you'd like to make another purchase, click Return to Store-Front\n" +
                                "otherwise, click Close");

        Label giveChange = new Label("Your change is: " +"$"+ changeDue);

        if(changeDue == 0)
        {
            giveChange.setVisible(false);
        }

        if (changeDue > 0)
        {
            giveChange.setVisible(true);
        }

        Button returnButton = new Button("Return to Store-Front");
        returnButton.setOnAction(event -> {

            cashPaidStage.close();

            Main mainObj = new Main();
            mainObj.start(sentStage);


        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> CloseWindow.closeAll());



        //layout
        GridPane layout = new GridPane();
        layout.setMinSize(300,300);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(thankyouMsg, 1,0);
        layout.add(giveChange, 1,1);
        layout.add(exitMsg, 1,2);
        layout.add(returnButton,0,4);
        layout.add(closeButton,2,4);

        layout.setHalignment(returnButton,HPos.CENTER);

        Scene lastScene = new Scene(layout);

        cashPaidStage.initModality(Modality.APPLICATION_MODAL);
        cashPaidStage.setScene(lastScene);
        cashPaidStage.showAndWait();


    } // END DISPENSE ITEMS AND CHANGE METHOD


    //payment with card
    public static void dispenseitems(Stage sentPrimarystage)
    {


        Stage cardPaidStage = new Stage();
        cardPaidStage.setTitle("Card Payment Approved - Thank you!");
        cardPaidStage.setResizable(false);
        cardPaidStage.setOnCloseRequest(event -> {

            event.consume();
            CloseWindow.closeAll();

        });


        Label thankyouMsg = new Label("Thank you for your purchase!\nYour items have been dispensed");

        Label exitMsg = new Label("If you'd like to make another purchase, click Return to Store-Front\n" +
                "otherwise, click Close");

        Button returnButton = new Button("Return to Store-Front");
        returnButton.setOnAction(event -> {

            cardPaidStage.close();

            Main caller = new Main();
            caller.start(sentPrimarystage);

        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> CloseWindow.closeAll());



        //layout
        GridPane layout = new GridPane();
        layout.setMinSize(300,300);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);

        layout.add(thankyouMsg, 1,0);
        layout.add(exitMsg,1,1);
        layout.add(returnButton,0,4);
        layout.add(closeButton,2,4);

        layout.setHalignment(returnButton,HPos.CENTER);

        Scene lastScene = new Scene(layout);

        cardPaidStage.initModality(Modality.APPLICATION_MODAL);
        cardPaidStage.setScene(lastScene);
        cardPaidStage.showAndWait();

    } //END DISPENSE ITEMS METHOD

}// END COMPLETE TRANSACTION CLASS
