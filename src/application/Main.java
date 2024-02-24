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
import java.util.Stack;





public class Main extends Application {
	
	private TextField textField = new TextField();
	private long num1 = 0;
	private String op = "";
	private boolean start = true;
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		textField.setFont(Font.font(20));
		textField.setPrefHeight(50);
		textField.setAlignment(Pos.CENTER_RIGHT);
		textField.setEditable(false); //Input field me hum kch b type nhi kr paenge ye line ni likhege to kch b type kia ja skta hai
		
		StackPane stackpane = new StackPane();
		stackpane.setPadding(new Insets(10));
		stackpane.getChildren().add(textField);
		
		
//		Adding the buttons to the scene in tilepane ye ky krega k ik specific order me button set kr dega
		TilePane tile = new TilePane();
		tile.setHgap(10);
		tile.setVgap(10);
		tile.setAlignment(Pos.TOP_CENTER);
		tile.getChildren().addAll(
				
//				Row1
				createButtonForNumber("7"),
				createButtonForNumber("8"),
				createButtonForNumber("9"),
				createButtonForOperators("X"),
				
//				Row2
				createButtonForNumber("4"),
				createButtonForNumber("5"),
				createButtonForNumber("6"),
				createButtonForOperators("-"),
				
//				Row3
				createButtonForNumber("1"),
				createButtonForNumber("2"),
				createButtonForNumber("3"),
				createButtonForOperators("+"),
				
//				Row4
				createButtonForNumber("0"),
				createButtonForClear("AC"),
				createButtonForOperators("="),
				createButtonForOperators("/"),
				
				createButtonForOperators("%")
				
				
				
				
				);
		
		
		
		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		root.setCenter(tile);
		
		Scene scene = new Scene(root,250,415);
		primaryStage.setScene(scene);
		primaryStage.setTitle("RICR Calculator");
		primaryStage.setResizable(false);  //Isse koi calculator ko resize nhi kr payega
		primaryStage.show();
		
		
	}
	
	private Button createButtonForNumber(String ch) {
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		
		button.setOnAction(this::processNumber);
		return button;
	}
	
	
	
	private Button createButtonForOperators(String ch) {
		Button button = new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		
		button.setOnAction(this::processOperator);

		return button;
	}
	
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
	
	private void processNumber(ActionEvent e) {
		if(start) {
			textField.setText("");
			start = false;
		}
		String value = ((Button)e.getSource()).getText();
		textField.setText(textField.getText()+value);
	}
	
	private void processOperator(ActionEvent e) {
		String value = ((Button)e.getSource()).getText();
		
		if(!value.equals("=")) {
			if(!op.isEmpty())
				return;
			num1 = Long.parseLong(textField.getText());
			op=value;
			textField.setText("");
			
		}
		
		else {
			if(op.isEmpty())
				return;
			long num2 = Long.parseLong(textField.getText());
			float result = calculate(num1,num2,op);
			textField.setText(String.valueOf(result));
			start= true;
			op="";
		}
	}
	
	//	Calculation k lie ik method banare ab
	private float calculate(long num1 , long num2 , String operator) {
		switch(operator) {
			case "+": return num1+num2;
			case "-": return num1-num2;
			case "X": return num1*num2;
			case "/": 
				if(num2==0)
					return 0;
				return num1/num2;
			case "%": return num1%num2;
			default: return 0;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}