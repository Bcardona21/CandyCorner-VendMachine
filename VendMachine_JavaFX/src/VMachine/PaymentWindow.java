package VMachine;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;


public class PaymentWindow
{

    Stage payStage;
    Scene payWindow;


    static Label dollarInputLabel;
    private static TextField dollarInput;


    static Label cardNumberLabel;
    static private TextField cardNumber;

    private static Button submitButton;
    private static Button cancelButton;



    PaymentWindow()
    {
/*         int machineTotalChange = 100;*/
    }



    public static void paymentType(Double balance, Stage sentPrimaryStage)
    {

        Stage payStage = new Stage();
        payStage.setTitle("Payment");
        payStage.setResizable(false);



        DecimalFormat decimalFormat = new DecimalFormat(" #.00");
        String balanceTotal = String.valueOf(decimalFormat.format(balance));

        Text amountDue = new Text("Your total comes out to: $" + balanceTotal +" \n\n\nPlease select a method of payment below");



        ImageView choiceImageView = new ImageView();
        choiceImageView.setFitWidth(100);
        choiceImageView.setFitHeight(100);

        ToggleGroup payOptionsGroup = new ToggleGroup();

        RadioButton cashRadioButton = new RadioButton("cash");
        cashRadioButton.setUserData("vend");
        cashRadioButton.setToggleGroup(payOptionsGroup);


        RadioButton cardRadioButton = new RadioButton("card");
        cardRadioButton.setUserData("card");
        cardRadioButton.setToggleGroup(payOptionsGroup);


        dollarInputLabel = new Label("Enter whole dollar values below: ");
        dollarInputLabel.setVisible(false);





        dollarInput = new TextField();
        dollarInput.setMaxWidth(55);
        dollarInput.setVisible(false);



        cardNumberLabel = new Label("Enter your card number (10 digits): ");
        cardNumberLabel.setVisible(false);

        cardNumber = new TextField();
        cardNumber.setVisible(false);








        //Radio button mechanics to register user input and to display the corresponding payment icon
        payOptionsGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
            {

                if(payOptionsGroup.getSelectedToggle() == cashRadioButton )
                {

                    cardNumberLabel.setVisible(false);
                    cardNumber.setVisible(false);

                    dollarInputLabel.setVisible(true);
                    dollarInput.setVisible(true);

                    //PATH NEEDED HERE
                    final Image image = new Image("file:    cash.png");
                    choiceImageView.setImage(image);


                    cashPay();

                }
                else if(payOptionsGroup.getSelectedToggle() == cardRadioButton )
                {

                    dollarInputLabel.setVisible(false);
                    dollarInput.setVisible(false);



                    cardNumberLabel.setVisible(true);
                    cardNumber.setVisible(true);

                    //PATH NEEDED HERE
                    final Image image = new Image("file:   credit.png");
                    choiceImageView.setImage(image);

                    cardPay();

                }
            }
        });


        submitButton = new Button("Submit Payment");
        submitButton.setOnAction(event -> {

            //Logic for cash payment
            if(cashRadioButton.isSelected())
            {

                String cashEntered = dollarInput.getText();
                int dollarInputTextFieldLength = dollarInput.getText().length();

                //if user doesn't enter any value in the text field to pay
                if (dollarInputTextFieldLength == 0)
                {
                    ErrorWindows.cashAmountError(dollarInputTextFieldLength);
                }
                //if user entered a value into the text field
                if(dollarInputTextFieldLength > 0)
                {
                    //convert cashEntered to an int to be able to work with it
                    int cashEnteredAmount = Integer.parseInt(cashEntered);

                    //if user entered an amount greater than what they owe
                    if(cashEnteredAmount >= balance)
                    {
                        CompleteTransaction.dispenseItemsAndChange(cashEnteredAmount, balance, sentPrimaryStage);
                        payStage.close();
                    }
                    else {
                        System.out.println("you did not enter enough funds");
                        ErrorWindows.cashAmountError(dollarInputTextFieldLength);
                    }

                }

            }

            //Logic for card payment
            if(cardRadioButton.isSelected())
            {
                int cardNumberLength = 10;

                if(cardNumber.getText().length() > cardNumberLength)
                {
                    ErrorWindows.cardNumberLengthErrorWindow(cardNumber.getText().length());
                    cardNumber.clear();
                }
                else if (cardNumber.getText().length() < cardNumberLength)
                {
                    ErrorWindows.cardNumberLengthErrorWindow(cardNumber.getText().length());
                    cardNumber.clear();
                }
                else{
                    CompleteTransaction.dispenseitems(sentPrimaryStage);
                    payStage.close();
                }
            }


        });





        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> payStage.close());

        GridPane layout = new GridPane();
        layout.setMinSize(500,500);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);



        layout.add(amountDue, 1,0);
        layout.add(cashRadioButton,1,1);
        layout.add(cardRadioButton, 1,2);
        layout.add(choiceImageView, 2,2);

        layout.add(dollarInputLabel,2,4);
        layout.add(dollarInput,2,5);

        layout.add(cardNumberLabel,2,4);
        layout.add(cardNumber,2,5);


        layout.add(submitButton, 1,6);
        layout.add(cancelButton,2,6);

        payStage.initModality(Modality.APPLICATION_MODAL);

        Scene payWindow = new Scene(layout);
        payStage.setScene(payWindow);
        payStage.show();

    } //END PAYMENT TYPE METHOD





    private static void cashPay()
    {

        //Dollar Input:   IF USER INPUTS A LETTER INSTEAD OF A NUMBER
        dollarInput.lengthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue())
                {
                    char input = dollarInput.getText().charAt(oldValue.intValue());

                    if(!(input >= '0' && input <= '9'))
                    {
                        dollarInput.setText(dollarInput.getText().substring(0,dollarInput.getText().length()-1));
                    }

                }
            }
        });


    } //END CASHPAY METHOD


    private static void cardPay()
    {





        //Card Number Input:   IF USER INPUTS A LETTER INSTEAD OF A NUMBER
        cardNumber.lengthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue())
                {
                    char input = cardNumber.getText().charAt(oldValue.intValue());

                    if(!(input >= '0' && input <= '9'))
                    {
                        cardNumber.setText(cardNumber.getText().substring(0,cardNumber.getText().length()-1));
                    }

                }
            }
        });



    } //END CARDPAY METHOD



} //END PAYMENTWINDOW CLASS
