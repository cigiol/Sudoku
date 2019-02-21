/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;


import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 *
 * @author cigid
 */
public class Controller implements Initializable {

	 // The FXML loader is going to inject from the layout
        @FXML Button button_zero;
	@FXML Button button_one; // remember our fx:id's for our buttons? name should be the same in order for the FXMLLoader to find it.
	@FXML Button button_two;
	@FXML Button button_three;
	@FXML Button button_four;
	@FXML Button button_five;
	@FXML Button button_six;
	@FXML Button button_seven;
	@FXML Button button_eight;
	@FXML Button button_nine;
        @FXML Button button_a;
	@FXML Button button_b;
	@FXML Button button_c;
	@FXML Button button_d;
	@FXML Button button_e;
	@FXML Button button_f;
        @FXML Button btnStart;
	@FXML Button btnStop;
        @FXML Button btnExit;
	@FXML Canvas canvas;
        @FXML Label labelTimer;
        @FXML RadioButton radioEasy;
        @FXML RadioButton radioMedium;
        @FXML RadioButton radioHard;
	
       
        // Make a new GameBoard declaration
	GameBoard gameboard;
        int player_selected_row;
        int player_selected_col;
        private int            seconds;
        int oldr;
        int oldc;
        int level=0;
        private long lastTime = 0;
        AnimationTimer timer;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            //Create an instance of our gameboard
                player_selected_col=0;
                player_selected_row=0;
		btnStop.setDisable(true);
		//Get graphics context from canvas
		GraphicsContext context = canvas.getGraphicsContext2D();
		//Call drawOnCanvas method, with the context we have gotten from the canvas
		//drawOnCanvas(context);
                timer= new AnimationTimer() {
                    
                    @Override
                    public void handle(long now) {
                        if (lastTime != 0) {
                        if (now > lastTime + 1_000_000_000) {
                            seconds++;
                            labelTimer.setText(Integer.toString(seconds) + " .s");
                            lastTime = now;
                        }
                    } else {
                        lastTime = now;

                    }
                    }
                };
                ToggleGroup tg= new ToggleGroup();
                radioEasy.setToggleGroup(tg);
                radioMedium.setToggleGroup(tg);
                radioHard.setToggleGroup(tg);
                canvas.setDisable(true);
	}
        
        public void canvasMouseClicked() {
		// attach a new EventHandler of the MouseEvent type to the canvas
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// override the MouseEvent to do what we want it to do
			@Override
			public void handle(MouseEvent event) {
				// intercept the mouse position relative to the canvas and cast it to an integer
				int mouse_x = (int) event.getX();
				int mouse_y = (int) event.getY();

				// convert the mouseX and mouseY into rows and cols
				// We are going to take advantage of the way integers are treated and we are going to divide 
				//by a cell's width.
				// This way any value between 0 and 449 for x and y is going to give us an integer from 
				//0 to 8, which is exactly what we are after.
				player_selected_row = (int) (mouse_y / 30); // update player selected row
				player_selected_col = (int) (mouse_x / 30); // update player selected column

				//get the canvas graphics context and redraw
				drawOnCanvas(canvas.getGraphicsContext2D());
			}
		});
	}
        
        boolean clicked = false;
        public void buttonStartPressed() {
                
                if(radioEasy.isSelected()){  
                    btnStop.setDisable(false);
                    gameboard = new GameBoard();
                    canvas.setDisable(false);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    level=1;
                    drawOnCanvas(context);
                    //this.lastTime = 0;
                    this.seconds = -1;
                    timer.start();
                    this.clicked = true;  
                    disableStartComp(true);
                    disableGameMenu(false);  
                    btnStop.setDisable(false);
                }
                if(radioMedium.isSelected()){
                    btnStop.setDisable(false);
                    gameboard = new GameBoard();
                    canvas.setDisable(false);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    level=2;
                    drawOnCanvas(context);
                    this.seconds = -1;
                    timer.start();
                    this.clicked = true;  
                    disableStartComp(true);
                    disableGameMenu(false);
                    btnStop.setDisable(false);
                }
                if(radioHard.isSelected()){
                    btnStop.setDisable(false);
                    gameboard = new GameBoard();
                    canvas.setDisable(false);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    level=3;
                    drawOnCanvas(context);
                    this.seconds = -1;
                    timer.start();
                    this.clicked = true; 
                    disableStartComp(true);
                    disableGameMenu(false); 
                    btnStop.setDisable(false);
                }
               
	}
        public void buttonStopPressed() {
                timer.stop();//TODO BUNA BASTIĞINDA SUDOKUNU GÖREMESİN ELLEŞEMESİN
                if(btnStop.getText().equals("RESUME")){
                    btnStop.setText("STOP");
                    canvas.setVisible(true);
                    canvas.setDisable(false);
                    disableGameMenu(false);
                    timer.start();
                }
                else if(btnStop.getText().equals("STOP")){
                    btnStop.setText("RESUME");
                    canvas.setVisible(false);
                    canvas.setDisable(true);
                    disableGameMenu(true);
                    timer.stop();
                }
                
                
                
	}
        
        public void modify(char a){
             switch (level) {
                case 1:
                    gameboard.modifyPlayer(a, player_selected_row, player_selected_col);
                    break;
                case 2:
                    gameboard.modifyPlayerM(a, player_selected_row, player_selected_col);
                    break;
                case 3:
                    gameboard.modifyPlayerH(a, player_selected_row, player_selected_col);
                    break;
                default:
                    break;
            }
        }
        //TODO I WILL ADD SOME CLİCKED FUNCS.
        public void buttonZeroPressed() {
		// The only thing that changes between all nine methods is the value we are injecting
		// in the player array. In this case, it is 1, because it corresponds to the button.
                if(this.clicked){
                    modify('0');
		
                    // refresh our canvas
                    drawOnCanvas(canvas.getGraphicsContext2D());
                }
                
	}
        public void buttonExitPressed() {
		// The only thing that changes between all nine methods is the value we are injecting
		// in the player array. In this case, it is 1, because it corresponds to the button.
                if(this.clicked){
                    modify('0');
		
                    // refresh our canvas
                    drawOnCanvas(canvas.getGraphicsContext2D());
                }
                
	}
        
        
        public void buttonOnePressed() {
		// The only thing that changes between all nine methods is the value we are injecting
		// in the player array. In this case, it is 1, because it corresponds to the button.
                if(this.clicked){
                    modify('1');
                    // refresh our canvas
                    drawOnCanvas(canvas.getGraphicsContext2D());
                }
		
	}
	public void buttonTwoPressed() {
            if(this.clicked){
                modify('2');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
		
	}

	public void buttonThreePressed() {
            if(this.clicked){
                modify('3');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
		
	}

	public void buttonFourPressed() {
            if(this.clicked){
                modify('4');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}

	public void buttonFivePressed() {
            if(this.clicked){
                modify('5');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}

	public void buttonSixPressed() {
            if(this.clicked){
                modify('6');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}

	public void buttonSevenPressed() {
            if(this.clicked){
                modify('7');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}

	public void buttonEightPressed() {
            if(this.clicked){
                modify('8');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}

	public void buttonNinePressed() {
            if(this.clicked){
                modify('9');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void buttonAPressed() {
            if(this.clicked){
                modify('A');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void buttonBPressed() {
            if(this.clicked){
                modify('B');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void buttonCPressed() {
            if(this.clicked){
                modify('C');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void buttonDPressed() {
            if(this.clicked){
                modify('D');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void buttonEPressed() {
            if(this.clicked){
                modify('E');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void buttonFPressed() {
            if(this.clicked){
                modify('F');
		drawOnCanvas(canvas.getGraphicsContext2D());
            }
	}
        public void check(GraphicsContext context){
            switch (level) {
                case 1:
                    if(gameboard.checkForSuccess() == true) {
					
			// clear the canvas
			//context.clearRect(0, 0, 480, 480);
			// set the fill color to green
			context.setFill(javafx.scene.paint.Color.GREEN);
			// set the font to 36pt
			context.setFont(new Font(32));
			// display SUCCESS text on the screen
			context.fillText("SUCCESS! YOUR TIME IS "+seconds+"SEC", 0, 250);//TODO BU OLDUKTAN SONRA EKRANI ELLEŞEMESİN İT
                        disableStartComp(false);
                        disableGameMenu(true);
                        btnStop.setDisable(true);
                        canvas.setDisable(true);
                        timer.stop();
                        
                    }
                    break;
                case 2:
                    if(gameboard.checkForSuccessM()== true) {
					
			// clear the canvas
			//context.clearRect(0, 0, 480, 480);
			// set the fill color to green
			context.setFill(javafx.scene.paint.Color.GREEN);
			// set the font to 36pt
			context.setFont(new Font(32));
			// display SUCCESS text on the screen
			context.fillText("SUCCESS! YOUR TIME IS "+seconds+"SEC", 0, 250);//TODO BU OLDUKTAN SONRA EKRANI ELLEŞEMESİN İT
                        disableStartComp(false);
                        disableGameMenu(true);
                        btnStop.setDisable(true);
                        canvas.setDisable(true);
                        timer.stop();
                        
				}
                    break;
                case 3:
                    if(gameboard.checkForSuccessH() == true) {
					
			// clear the canvas
			//context.clearRect(0, 0, 480, 480);
			// set the fill color to green
			context.setFill(javafx.scene.paint.Color.GREEN);
			// set the font to 36pt
			context.setFont(new Font(32));
			// display SUCCESS text on the screen
			context.fillText("SUCCESS! YOUR TIME IS "+seconds+"SEC", 0, 250);//TODO BU OLDUKTAN SONRA EKRANI ELLEŞEMESİN İT
                        
                        disableStartComp(false);
                        disableGameMenu(true);
                        btnStop.setDisable(true);
                        canvas.setDisable(true);
                        timer.stop();
                        
				}
                    break;
                default:
                    break;
            }
        }
        
    private void disableGameMenu(boolean check){
        if(check){
            button_zero.setDisable(true);
            button_one.setDisable(true); 
            button_two.setDisable(true);
            button_three.setDisable(true);
            button_four.setDisable(true);
            button_five.setDisable(true);
            button_six.setDisable(true);
            button_seven.setDisable(true);
            button_eight.setDisable(true);
            button_nine.setDisable(true);
            button_a.setDisable(true);
            button_b.setDisable(true);
            button_c.setDisable(true);
            button_d.setDisable(true);
            button_e.setDisable(true);
            button_f.setDisable(true);  
        }
        else{
            button_zero.setDisable(false);
            button_one.setDisable(false); 
            button_two.setDisable(false);
            button_three.setDisable(false);
            button_four.setDisable(false);
            button_five.setDisable(false);
            button_six.setDisable(false);
            button_seven.setDisable(false);
            button_eight.setDisable(false);
            button_nine.setDisable(false);
            button_a.setDisable(false);
            button_b.setDisable(false);
            button_c.setDisable(false);
            button_d.setDisable(false);
            button_e.setDisable(false);
            button_f.setDisable(false);
        }
        
    }
    private void disableStartComp(boolean check){
        if(check){
            btnStart.setDisable(true);
            radioEasy.setDisable(true);
            radioMedium.setDisable(true);
            radioHard.setDisable(true);
        }
        else{
            btnStart.setDisable(false);
            radioEasy.setDisable(false);
            radioMedium.setDisable(false);
            radioHard.setDisable(false);
        }
    }
        
    private void drawOnCanvas(GraphicsContext context) {
        
        context.clearRect(0, 0, 480, 480);
        for(int row = 0; row<16; row++) {
			for(int col = 0; col<16; col++) {
				// finds the y position of the cell, by multiplying the row number by 50, which is the height of a row 					// in pixels
				// then adds 2, to add some offset
				int position_y = row * 30 + 2;
				
				// finds the x position of the cell, by multiplying the column number by 50, which is the width of a 					// column in pixels
				// then add 2, to add some offset
				int position_x = col * 30 + 2;
				context.setFill(javafx.scene.paint.Color.WHITE);
                                if(row <4 || row >7 && row<12){
                                    if(col<4 || col>7 && col<12){
                                        context.setFill(javafx.scene.paint.Color.LIGHTGREY);
                                    }
                                }
                                else if(row >3 || row >11 ){
                                    if(col>3 && col<8 || col>11 ){
                                        context.setFill(javafx.scene.paint.Color.LIGHTGREY);
                                    }
                                }
                               
				// defines the width of the square as 46 instead of 50, to account for the 4px total of blank space 					// caused by the offset
				// as we are drawing squares, the height is going to be the same
				int width = 26;
				
				// set the fill color to white (you could set it to whatever you want)
				
				
				// draw a rounded rectangle with the calculated position and width. The last two arguments specify the 					// rounded corner arcs width and height.
				// Play around with those if you want.
				context.fillRoundRect(position_x, position_y, width, width, 10, 10);
			}
		}
        
        // when the gameboard returns true with its checkForSuccess
		// method, that means it has found no mistakes
		// checkForSuccess CAN BE SUBSTITUTED WITH checkForSuccessGeneral
                
                check(context);
		/*if(gameboard.checkForSuccess() == true) {
					
			// clear the canvas
			context.clearRect(0, 0, 480, 480);
			// set the fill color to green
			context.setFill(javafx.scene.paint.Color.GREEN);
			// set the font to 36pt
			context.setFont(new Font(36));
			// display SUCCESS text on the screen
			context.fillText("SUCCESS!", 150, 250);//TODO BU OLDUKTAN SONRA EKRANI ELLEŞEMESİN İT
				}*/
                
                
        // draw highlight around selected cell
		// set stroke color to res
		context.setStroke(javafx.scene.paint.Color.BLUE);
		// set stroke width to 5px
		context.setLineWidth(2);
		// draw a strokeRoundRect using the same technique we used for drawing our board.
		context.strokeRoundRect(player_selected_col * 30 + 2, player_selected_row * 30 + 2, 26, 26, 10, 10);
                char[][] initial = gameboard.getInitialM();
        
            switch (level) {
                case 1:
                    initial = gameboard.getInitial();
                    break;
                case 2:
                    initial = gameboard.getInitialM();
                    break;
                case 3:
                    initial = gameboard.getInitialH();
                    break;
                default:
                    break;
            }
		
		// for loop is the same as before
		for(int row = 0; row<16; row++) {//TODO THEY WILL 16
			for(int col = 0; col<16; col++) {
			
				// finds the y position of the cell, by multiplying the row number by 50, which 
				// is the height of a row in pixels then adds 2, to add some offset
				int position_y = row * 30 + 20;
				
				// finds the x position of the cell, by multiplying the column number by 50, 
				// which is the width of a column in pixels then add 2, to add some offset
				int position_x = col * 30 +10;
				
				// set the fill color to white (you could set it to whatever you want)
				context.setFill(javafx.scene.paint.Color.BLACK);
                                
                                // set the font, from a new font, constructed from the system one, with size 20
				context.setFont(new Font(20));
				
				// check if value of coressponding initial array position is not 0, remember that
				// we treat zeroes as squares with no values.
				if(initial[row][col]!='-') {
				
					// draw the number using the fillText method
					context.fillText(initial[row][col] + "", position_x, position_y);
				}
			}
		}
                // draw the players numbers from our GameBoard instance
				char[][] player = gameboard.getPlayer();
                                //char[][] solution = gameboard.getSolution();
				for(int row = 0; row<16; row++) {//TODO MAKE IT 16 
					for(int col = 0; col<16; col++) {
						// finds the y position of the cell, by multiplying the row 
						// number by 50, which is the height of a row in pixels
						// then adds 2, to add some offset
						int position_y = row * 30 + 20;
						// finds the x position of the cell, by multiplying the column 
						// number by 50, which is the width of a column in pixels
						// then add 2, to add some offset
						int position_x = col * 30 +10;
						// set the fill color to purple (you could set it to whatever you want)
						context.setFill(javafx.scene.paint.Color.BLUE);
                                                
						// set the font, from a new font, constructed from the system one, with size 20
						context.setFont(new Font(20));
						// check if value of coressponding array position is not 0
						if(player[row][col]!='-') {//TODO ITS -1
							// draw the number
                                                        for(int r = 0; r<16; r++) {//TODO MAKE IT 16 
                                                            if(player[row][col]==initial[r][col]){
                                                                context.setFill(javafx.scene.paint.Color.RED);
                                                                context.setStroke(javafx.scene.paint.Color.RED);
                                                                // set stroke width to 5px
                                                                context.setLineWidth(1);
                                                                // draw a strokeRoundRect using the same technique we used for drawing our board.
                                                                context.strokeRoundRect(col * 30 + 2, r * 30 + 2, 26, 26, 10, 10);
                                                                break;
                                                            }
                                                        }
                                                        for(int c = 0; c<16; c++) {
                                                            if(player[row][col]==initial[row][c]){
                                                                context.setFill(javafx.scene.paint.Color.RED);
                                                                context.setStroke(javafx.scene.paint.Color.RED);
                                                                // set stroke width to 5px
                                                                context.setLineWidth(1);
                                                                // draw a strokeRoundRect using the same technique we used for drawing our board.
                                                                context.strokeRoundRect(c * 30 + 2, row * 30 + 2, 26, 26, 10, 10);
                                                                break;
                                                            }
                                                                
                                                        }
                                                        
                                                        /*if(player[row][col]==solution[row][col])
                                                            context.setFill(javafx.scene.paint.Color.GREEN);
                                                        else
                                                            context.setFill(javafx.scene.paint.Color.RED);*/
                                                            
                                                            
							context.fillText(player[row][col] + "", position_x, position_y);
						}
					}
				}
    }
}
