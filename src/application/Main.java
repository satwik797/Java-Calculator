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
	//global variables
	 private TextField  textField= new TextField();
	 private long num1 =0;
	 private  String op= "";
	 private boolean start = true;
	 
	@Override
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
        		createButtonForNumber("7"),
        		createButtonForNumber("8"),
        		createButtonForNumber("9"),
        		createButtonForOperators("/"),
        		
        		createButtonForNumber("4"),
        		createButtonForNumber("5"),
        		createButtonForNumber("6"),
        		createButtonForOperators("X"),
        		
        		createButtonForNumber("1"),
        		createButtonForNumber("2"),
        		
        		createButtonForNumber("3"),
        		createButtonForOperators("-"),
        		
        		createButtonForNumber("0"),
        		createButtonForClear("C"),
        		createButtonForOperators("="),
        		createButtonForOperators("+")
        		
        		);
        
		
		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		//adding the buttons/tilepane in borderpane
		root.setCenter(tile);
		
		
//creating a scene
		Scene scene=new Scene(root,250,300);
		primaryStage.setScene(scene);
        primaryStage.setTitle("My Calculator");	
        
        
        
        //cant be resizable
        primaryStage.setResizable(false);
        primaryStage.show();
		
	}
	
	//4 methods for 4 different works
	//method1 - for operands
	//method2 - for operators
	//method3 - for clear screen
	//method4 - for backspace
	
	
	//this method will return buttons
		private Button createButtonForNumber(String ch) {	
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		
		button.setOnAction(this::processNumbers);
		return button;
	}
		
	//this method will return operators
			private Button createButtonForOperators(String ch) {
            Button button = new Button(ch);       
            button.setFont(Font.font(18));
			button.setPrefSize(50,50);
			
			button.setOnAction(this::processOperators);
			return button;
		}
		
    //this method will return clear
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
		
	//this method will return backspace
			private Button createButtonForBackspace(String ch) {
			Button button = new Button(ch);
			button.setFont(Font.font(18));
			button.setPrefSize(50,50);
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
					num1=Long.parseLong(textField.getText());
					op=value;
					textField.setText("");
					
				}
				//if user enter operator '='
				else {
					if(op.isEmpty())
						return;
					long num2=Long.parseLong(textField.getText());
					float result =calculate(num1,num2,op);
					textField.setText(String.valueOf(result));
					start=true;
					op="";
				}
				
			}
			
			private float calculate(long num1,long num2, String operator) {
				switch(operator) {
				case "+":
					return num1+num2;
				case "-":
					return num1-num2;
				case "X":
					return num1*num2;
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
