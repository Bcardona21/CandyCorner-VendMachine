package VMachine;

import javafx.application.Application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Main extends Application
{

    Scene scene;
    Button submitButton, closeButton, addButton, deleteButton;
    Image vendImage;
    Double purchaseTotal;


    public Main()
    {
        purchaseTotal = 0.00;
    }


    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage)
    {
        Stage sendPrimaryStage = primaryStage;

        Text welcomeText = new Text("Welcome to the CandyCorner!");
        welcomeText.setFont(Font.font("Cambria",18));


        Text infoText = new Text("Add your selections to the cart, and\n when you're done, click submit\n to proceed to payment");

        TextArea itemCart = new TextArea();
        itemCart.setEditable(false);
        itemCart.setPrefRowCount(8);
        itemCart.setMaxWidth(150);

        //A purchaseTotal text area that shows the user the purchaseTotal cost of their purchase
        TextArea cartTotal = new TextArea();

        cartTotal.setEditable(false);
        cartTotal.setPrefRowCount(1);
        cartTotal.setMaxWidth(55);
        cartTotal.setMaxHeight(25);
        cartTotal.setText("$ 0.00");


        //Array for vending machine vendOptions
        String[] arry = new String[10];
        arry[0] = "Snickers $1.25";
        arry[1] = "Kit-Kat $1.25";
        arry[2] = "Reese's $1.25";
        arry[3] = "PayDay $1.00";
        arry[4] = "Almond Joy $1.00";
        arry[5] = "Hershey's Milk Chocolate $1.25";
        arry[5] = "Hershey's Cookies and Cream $1.50";
        arry[6] = "Cheetos $1.75";
        arry[7] = "Doritos $1.75";
        arry[8] = "Lay's Classic Chips $1.75";
        arry[9] = "Famous Amos Cookies $2.00";


        //hashmap with array elements as keys and costs of each item as their values
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put(arry[0],1.25);
        map.put(arry[1],1.25);
        map.put(arry[2],1.25);
        map.put(arry[3],1.00);
        map.put(arry[4],1.00);
        map.put(arry[5],1.50);
        map.put(arry[6],1.75);
        map.put(arry[7],1.75);
        map.put(arry[8],1.75);
        map.put(arry[9],2.00);


        //Drop down list that displays all the options in the vending machine
        ComboBox vendOptions = new ComboBox();
        vendOptions.setMaxWidth(150);
        vendOptions.setPromptText("Make a selection: ");
        vendOptions.getItems().addAll(arry[0],arry[1],arry[2],arry[3],arry[4],arry[5],arry[6],arry[7],arry[8],arry[9]);



        Label cartLabel = new Label("Your cart:");
        Label cartTotalLabel = new Label("Total:");



        addButton = new Button("add");
        addButton.setOnAction(event -> {


            if(vendOptions.getValue() == null)
            {
                ErrorWindows.itemSelectErrorWindow();
            }
            else {

                itemCart.insertText(0, vendOptions.getValue() + "\n");


                for (int i = 0; i < arry.length; i++)
                {
                    if (vendOptions.getValue() == arry[i]) {

                        purchaseTotal = purchaseTotal + map.get(arry[i]);

                        DecimalFormat decimalFormat = new DecimalFormat(" #.00");


                        String stringTotal = String.valueOf(decimalFormat.format(purchaseTotal));
                        cartTotal.setText("$" + stringTotal);

                    }
                }
            }

        });


        deleteButton = new Button("delete");
        deleteButton.setOnAction(event -> {
            cartTotal.clear();
            itemCart.clear();
            cartTotal.setText("$ 0.00");
            purchaseTotal=0.00;
        } );


        //button to continue to payment
        //    IF the customer HAS NOT picked an option, they CANNOT go on to payment window
        submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {


            if(purchaseTotal == 0)
            {
                ErrorWindows.cartErrorWindow();
            }
            else {
                PaymentWindow.paymentType(purchaseTotal, sendPrimaryStage );
            }
        });


        closeButton = new Button("Close");
        closeButton.setOnAction(event ->  CloseWindow.closeAll());

        //PATH NEEDED HERE
        vendImage = new Image("file:   vend.jpg");

        ImageView vendMachineIMG = new ImageView();
        vendMachineIMG.setImage(vendImage);
        vendMachineIMG.setFitHeight(200);
        vendMachineIMG.setFitWidth(300);
        vendMachineIMG.setPreserveRatio(true);

        //*********LAYOUT CODE************
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);


/*
        layout.setGridLinesVisible(true);
*/


        layout.setMinSize(100,100);

        layout.add(welcomeText,3,0);
        layout.add(infoText,0,1);
        layout.add(submitButton, 2,4);
        layout.add(closeButton,4,4);
        layout.add(vendMachineIMG, 6,0);
        layout.add(cartLabel,4,0);
        layout.add(itemCart, 4,1);
        layout.add(cartTotalLabel,5,1);
        layout.add(cartTotal,5,1);
        layout.add(addButton,2,2);
        layout.add(deleteButton,4,2);
        layout.add(vendOptions,2,1);


        layout.setHalignment(cartLabel,HPos.CENTER);
        layout.setValignment(cartLabel, VPos.BOTTOM);


        layout.setHalignment(cartTotalLabel,HPos.CENTER);
        layout.setValignment(cartTotalLabel, VPos.TOP);
        cartTotalLabel.setPadding(new Insets(30,0,0,0));


        layout.setHalignment(cartTotal,HPos.CENTER);

        //*******END LAYOUT CODE********






        primaryStage.setTitle("CandyCorner V-Machine");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {

            event.consume();
            CloseWindow.closeAll();

        });

        scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();


    } //END START METHOD

} //END MAIN CLASS