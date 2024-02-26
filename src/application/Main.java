package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;


public class Main extends Application {
	//global variables  private instance variables
	 private TextField  textField= new TextField();
	 private double num1 =0;
	 private  String op= "";
	 private boolean start = true;
	
	@Override
	//The throws keyword is used to declare which exceptions can be thrown from a method
	public void start(Stage primaryStage) throws Exception{
		
		textField.setFont(Font.font(20));
		textField.setPrefHeight(50);
		textField.setAlignment(Pos.CENTER_RIGHT);
		//for disabling editing mode in textfield
		textField.setEditable(false);
		
		//adding text field to top
		StackPane stackpane=new StackPane();
		
		//selecting the ref variable to add padding
		stackpane.setPadding(new Insets(10));
		//Adding txtField to stackpane
		stackpane.getChildren().add(textField);
		
		//styles
		TilePane tile = new TilePane();
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setAlignment(Pos.TOP_CENTER);
        //adding h and v in TilePane
        tile.getChildren().addAll(
        		createButtonForClear("AC"),
        		createButtonForOperators("%"),
        		createButtonForDecimal("."),	
        		createButtonForOperators("/"),
        		
        		createButtonForNumber("7"),
        		createButtonForNumber("8"),
        		createButtonForNumber("9"),
        		createButtonForOperators("X"),
        		
        		createButtonForNumber("4"),
        		createButtonForNumber("5"),
        		createButtonForNumber("6"),
        		createButtonForOperators("-"),
        	
        		
        		createButtonForNumber("1"),
        		createButtonForNumber("2"),
        		createButtonForNumber("3"),
        		createButtonForOperators("+"),
        		
        		
        		createButtonForNumber("00"),
        		createButtonForNumber("0"),
        		createButtonForBackspace("B"),  
        	    createButtonForOperators("=") 
        		);
        
		//adding StackPane in BorderPane root
		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		
		//adding the buttons/tilepane in borderpane
		root.setCenter(tile);
		
		Scene scene=new Scene(root,250,373);
		
		primaryStage.setScene(scene);
        primaryStage.setTitle("RICR Calculator");	
          
        //cant resize the calculator
        primaryStage.setResizable(false);
        primaryStage.show();
		
	}
	
		//4 methods for 4 different works to return buttons
		//method1 - for operands
		//method2 - for operators
		//method3 - for clear screen
		//method4 - for backspace
	
		//this method will return buttons for numbers
		private Button createButtonForNumber(String ch) {	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		
		button.setOnAction(this::processNumbers);	
		return button;
	}
		
		//this method will return operators button
			private Button createButtonForOperators(String ch) {
            Button button = new Button(ch);       
            button.setFont(Font.font(18));
			button.setPrefSize(50,50);
		    // Check if the button text is "=" and set its color to blue
		    if (ch.equals("=")) {
		        button.setStyle("-fx-background-color: skyblue;");
		    }

			
			//method reference operator is used
			button.setOnAction(this::processOperators);			
			return button;
		}
		
			//this method will return clear button
			private Button createButtonForClear(String ch) {
			Button button = new Button(ch);
			button.setFont(Font.font(18));
			button.setPrefSize(50,50);
			
			button.setOnAction(e->{
				textField.setText("");
				op="";
				start=true;
			});
			return button;
		}
		
			//methods return a javafx button object
			private Button createButtonForBackspace(String ch) {
				
			        Button button = new Button(ch);
			        button.setFont(Font.font(18));
			        button.setPrefSize(50, 50);
		        
			        //The text of the backspace button is set to the Unicode character for backspace, which is "\u232B".
			        // Set the text of the backspace button to a symbol indicating backspace
		        	button.setText("\u232B");

		        	//lambda expression to define an EventHandler for the ActionEvent
		        	//When the button is clicked, it retrieves the text
			        //currently displayed in the textField
		        	button.setOnAction(e -> {
		       	
		        	//If the text length is greater than 0 (i.e., there is text to delete),
		 		    //it removes the last character from the text by using the substring method and updates the textField with the modified text.     
		            String text = textField.getText();
		       
		            if (text.length() > 0) {
		                	textField.setText(text.substring(0, text.length() - 1));
		            	}
		        	});

		        	return button;
		    }

		    private Button createButtonForDecimal(String ch) {
		    	
		        Button button = new Button(ch);
		        button.setFont(Font.font(18));
		        button.setPrefSize(50, 50);

		        button.setOnAction(e -> {
		            String text = textField.getText();
		            if (!text.contains(".")) {
		                textField.setText(text + ".");
		            }
		        });

		        return button;
		    }

			
			private void processNumbers(ActionEvent e) {
				
				if(start) {
					textField.setText("");
					start=false;
				}
				String value=((Button)e.getSource()).getText();
				textField.setText(textField.getText()+value);
			}
		
			
		
			
			private void processOperators(ActionEvent e) {
				String value=((Button)e.getSource()).getText();
				
				//if user enter other operators than '='
				if(!value.equals("=")) {
					if(!op.isEmpty())
						return;
					num1=Double.parseDouble(textField.getText());
					op=value;
					textField.setText("");
					
				}
				
				//if user enter operator '='
				else {
					if(op.isEmpty())
						return;
					double num2=Double.parseDouble(textField.getText());
					double result = calculate(num1,num2,op);
					textField.setText(String.valueOf(result));
					start=true;
					op="";
				}
				
			}
			
			
			private double calculate(double num1,double num2, String operator) {
				switch(operator) {
				case "+":
					return num1+num2;
				case "-":
					return num1-num2;
				case "X":
					return num1*num2;
					
				case "%":
					return num1%num2;
				case "/":
					if(num2==0)
						return 0;
			         return num1/num2;
			         
				default: 
					return 0;
					
				}
			}
	
	public static void main(String[] args) {
		launch(args);
	}
}
