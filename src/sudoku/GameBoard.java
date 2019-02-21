/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author cigid
 */

public class GameBoard {

	/* Array that will contain the complete solution to the board */
	private char[][] solution;
        private char[][] solutionM;
        private char[][] solutionH;//TODO IT WILL BE CHAR ARRAY I THINK WE WILL SEE
	/* Array that will contain ONLY the numbers initially drawn on the board and that the player can't change */
	private char[][] initial;
        private char[][] initialM;
        private char[][] initialH;
	/* Array that will contain player's numbers */
	private char[][] player;
        
        private int level;
        
	public GameBoard() {
            //TODO I will make it 16x16 
		solution = new char[][]
		{
			{'C','9','E','7','6','8','3','4','A','B','5','0','F','D','2','1'},
			{'6','8','A','3','F','7','D','B','9','2','1','C','5','E','0','4'},
			{'0','B','F','D','2','A','1','5','4','8','7','E','6','3','9','C'},
			{'2','1','5','4','E','9','C','0','F','3','6','D','8','7','A','B'},
			{'1','7','8','6','0','3','F','9','B','5','A','2','E','C','4','D'},
			{'3','A','0','B','D','5','2','C','7','E','4','6','1','9','8','F'},
			{'E','D','9','F','7','4','B','A','0','C','8','1','2','6','5','3'},
			{'5','C','4','2','1','E','8','6','D','F','9','3','7','0','B','A'},
			{'4','2','3','1','8','6','5','7','E','9','D','A','B','F','C','0'},
			{'A','6','7','5','9','D','0','2','C','1','F','B','4','8','3','E'},
			{'B','0','C','E','A','F','4','3','6','7','2','8','D','5','1','9'},
			{'9','F','D','8','B','C','E','1','3','4','0','5','A','2','7','6'},
			{'7','4','B','C','5','2','9','D','1','0','E','F','3','A','6','8'},
			{'D','5','1','9','3','B','A','E','8','6','C','7','0','4','F','2'},
			{'F','E','2','0','4','1','6','8','5','A','3','9','C','B','D','7'},
			{'8','3','6','A','C','0','7','F','2','D','B','4','9','1','E','5'}
		};
                
                
                solutionM = new char[][]
		{
			{'7','8','2','9','A','B','D','0','4','E','C','5','F','6','1','3'},
			{'D','A','5','6','9','E','1','8','7','F','3','0','2','C','4','B'},
			{'E','0','F','1','4','C','2','3','8','6','D','B','9','A','7','5'},
			{'C','4','B','3','F','6','7','5','A','9','1','2','0','8','D','E'},
			{'4','C','0','D','E','8','B','2','F','A','5','6','1','7','3','9'},
			{'2','9','6','8','1','7','5','A','D','C','4','3','B','E','F','0'},
			{'5','F','7','E','6','D','3','4','9','0','B','1','C','2','8','A'},
			{'A','3','1','B','0','9','C','F','2','8','E','7','4','D','5','6'},
			{'B','5','9','7','2','4','0','6','1','D','8','F','E','3','A','C'},
			{'F','2','E','4','B','3','A','9','5','7','6','C','D','1','0','8'},
			{'3','6','A','C','D','1','8','7','0','B','9','E','5','4','2','F'},
			{'1','D','8','0','C','5','F','E','3','4','2','A','6','B','9','7'},
			{'8','E','4','2','5','A','6','1','B','3','0','9','7','F','C','D'},
			{'9','1','D','A','7','2','E','C','6','5','F','8','3','0','B','4'},
			{'6','B','3','F','8','0','9','D','C','2','7','4','A','5','E','1'},
			{'0','7','C','5','3','F','4','B','E','1','A','D','8','9','6','2'}
		};
                
                 solutionH = new char[][]
		{
			{'0','8','E','3','A','2','D','9','6','5','1','C','F','B','7','4'},
			{'7','B','5','A','4','0','8','F','9','2','D','3','6','C','1','E'},
			{'4','D','1','C','7','5','3','6','E','8','B','F','2','A','9','0'},
			{'9','2','6','F','E','1','C','B','7','A','0','4','3','8','D','5'},
			{'5','6','2','4','C','D','B','E','0','F','9','7','A','3','8','1'},
			{'B','0','A','7','6','9','4','3','C','1','8','5','E','D','2','F'},
			{'E','1','3','8','F','A','5','2','4','B','6','D','0','9','C','7'},
			{'D','C','F','9','1','8','7','0','A','E','3','2','4','5','B','6'},
			{'3','9','D','B','2','7','6','A','1','0','C','E','5','4','F','8'},
			{'F','E','C','6','B','4','0','D','5','7','A','8','1','2','3','9'},
			{'2','4','8','5','9','3','E','1','D','6','F','B','7','0','A','C'},
			{'A','7','0','1','5','C','F','8','3','4','2','9','D','6','E','B'},
			{'6','A','B','2','D','E','1','4','8','C','7','0','9','F','5','3'},
			{'8','F','7','D','0','6','9','5','B','3','E','A','C','1','4','2'},
			{'1','5','9','E','3','B','2','C','F','D','4','6','8','7','0','A'},
			{'C','3','4','0','8','F','A','7','2','9','5','1','B','E','6','D'}
		};
                
                //TODO there will be 0 as value so i need to 0 to -1 for empty space 
		// 0's will be rendered as empty space and will be editable by player
		initial = new char[][]
		{
			{'-','-','-','-','6','8','-','-','-','-','-','-','-','D','-','1'},
			{'6','-','A','-','-','-','-','-','9','-','1','C','-','E','-','4'},
			{'0','-','F','D','2','A','1','-','4','-','-','-','-','-','-','C'},
			{'-','1','-','4','-','-','-','-','-','-','6','-','-','7','A','-'},
			{'1','-','-','-','-','-','F','-','-','5','-','-','-','-','-','-'},
			{'-','A','0','B','D','-','-','-','7','-','4','6','-','9','-','-'},
			{'E','-','-','F','-','-','-','-','0','-','8','1','-','-','5','3'},
			{'-','-','4','-','1','-','-','6','-','-','-','3','7','0','-','A'},
			{'-','-','3','-','-','-','-','7','-','9','-','A','B','-','-','0'},
			{'-','-','7','5','-','D','-','2','C','-','-','-','-','8','-','-'},
			{'-','-','C','E','A','F','4','3','-','-','2','-','-','-','-','9'},
			{'-','F','-','8','B','-','-','-','-','-','-','5','A','-','7','-'},
			{'-','-','-','C','5','-','-','-','-','0','E','F','-','-','6','-'},
			{'D','-','-','9','-','B','A','-','8','-','-','-','-','4','-','-'},
			{'-','-','2','-','4','-','-','-','-','A','-','-','C','-','D','-'},
			{'8','3','6','-','-','0','-','F','2','D','B','-','9','1','E','5'}
		};
                initialM = new char[][]
		{
			{'-','8','2','9','-','-','-','-','-','E','C','-','F','-','-','3'},
			{'-','-','-','-','-','-','-','1','-','-','3','-','2','C','4','-'},
			{'E','0','-','1','4','-','2','3','-','-','D','-','-','-','-','5'},
			{'-','-','B','-','F','-','7','5','A','9','-','-','-','-','D','E'},
			{'-','C','-','D','-','8','B','-','F','-','5','6','-','7','-','9'},
			{'-','9','6','8','-','-','5','A','-','-','-','-','-','-','-','-'},
			{'-','F','7','-','-','D','-','-','-','-','-','-','C','-','-','-'},
			{'A','-','-','B','-','-','-','F','-','-','E','-','-','-','-','-'},
			{'-','-','-','-','2','-','-','6','1','D','-','F','E','3','-','C'},
			{'-','-','-','4','-','-','A','-','-','7','-','-','D','-','-','-'},
			{'3','-','A','C','-','-','8','-','-','B','-','-','-','-','2','-'},
			{'-','D','8','0','-','-','-','-','3','-','-','-','-','B','-','7'},
			{'-','E','-','-','-','A','-','-','B','-','-','-','-','F','-','D'},
			{'9','-','D','-','7','-','-','-','-','-','-','-','-','-','B','-'},
			{'6','-','3','-','-','0','-','-','C','-','-','-','A','-','-','1'},
			{'-','-','-','5','3','F','4','B','-','-','-','-','-','-','6','-'}
		};
                
                initialH = new char[][]
		{
			{'-','8','-','-','-','-','-','-','-','-','-','-','-','-','-','4'},
			{'7','B','-','-','-','0','8','-','9','-','D','-','-','-','-','-'},
			{'4','-','1','-','-','-','3','6','-','8','-','F','-','A','-','-'},
			{'-','2','-','F','E','1','-','-','-','-','-','-','3','-','D','5'},
			{'-','-','-','4','-','D','-','-','0','-','9','-','-','-','-','1'},
			{'B','0','-','7','-','9','4','3','-','-','8','5','-','-','-','F'},
			{'-','-','-','-','-','-','5','-','-','-','6','D','0','-','C','-'},
			{'-','-','-','-','-','-','-','-','-','-','-','-','-','5','B','6'},
			{'3','-','-','B','2','-','-','-','1','-','-','E','-','4','-','-'},
			{'F','E','C','6','-','-','0','-','5','-','-','-','-','2','3','-'},
			{'-','-','8','5','-','-','E','1','-','6','-','-','-','-','A','C'},
			{'-','-','-','-','5','-','-','-','3','4','-','9','-','-','-','B'},
			{'-','A','-','2','-','-','1','4','-','C','-','-','9','-','5','-'},
			{'8','-','-','-','-','-','-','-','-','-','E','-','-','-','-','2'},
			{'-','5','9','E','-','-','-','-','-','D','-','6','-','-','0','-'},
			{'C','3','-','-','-','-','-','-','-','9','-','-','-','E','6','D'}
		};

		// player's array is initialized as a 9x9 full of zeroes
		player = new char[16][16];//TODO 16X16
	}

	// returns the solution array
	public char[][] getSolution() {
		return solution;
	}
        
        public char[][] getSolutionM() {
		return solutionM;
	}
        
        public char[][] getSolutionH() {
		return solutionH;
	}
	// returns the initial filled-in numbers array
	public char[][] getInitial() {
		return initial;
	}
        
        public char[][] getInitialM() {
		return initialM;
	}

        public char[][] getInitialH() {
		return initialH;
	}
	// returns the player array
	public char[][] getPlayer() {
		return player;
	}
        
        public int getLevel(){
            return level;
        }
        
        public void setLevel(int level){
            this.level=level;
        }

	// modifies a value in the player array
	public void modifyPlayer(char val, int row, int col) {
		// check if the initial array has a zero (treated as empty square)
		// in the position we want to put in a number in the player array
		// this way we avoid intersections between the two
		if (initial[row][col] == '-') {//TODO NOT  IT WILL BE -1 MAYBE
			
			//if(val >=0 && val <= 9) // only values from 0 to 9 inclusive are permitted //TODO NOT JUST 0-9 THERE WILL BE A B C D E F
				player[row][col] = val;
			//else // print out an error message
				//System.out.println("Value passed to player falls out of range");
		}
		
	}
        public void modifyPlayerM(char val, int row, int col) {
		// check if the initial array has a zero (treated as empty square)
		// in the position we want to put in a number in the player array
		// this way we avoid intersections between the two
		if (initialM[row][col] == '-') {//TODO NOT  IT WILL BE -1 MAYBE
			
			//if(val >=0 && val <= 9) // only values from 0 to 9 inclusive are permitted //TODO NOT JUST 0-9 THERE WILL BE A B C D E F
				player[row][col] = val;
			//else // print out an error message
				//System.out.println("Value passed to player falls out of range");
		}
		
	}
        public void modifyPlayerH(char val, int row, int col) {
		// check if the initial array has a zero (treated as empty square)
		// in the position we want to put in a number in the player array
		// this way we avoid intersections between the two
		if (initialH[row][col] == '-') {//TODO NOT  IT WILL BE -1 MAYBE
			
			//if(val >=0 && val <= 9) // only values from 0 to 9 inclusive are permitted //TODO NOT JUST 0-9 THERE WILL BE A B C D E F
				player[row][col] = val;
			//else // print out an error message
				//System.out.println("Value passed to player falls out of range");
		}
		
	}
        public boolean checkForSuccess() {
		for(int row = 0; row<16; row++) {
			for(int col = 0; col<16; col++) { //TODO NOT 9 IT WILL BE 16
				
				// if the value in the initial array is zero, which means
				// the player has to input a value in the square
				if(initial[row][col] == '-') {//TODO NOT 0 IT WILL BE -1
					
					// check if the player value corresponds to the solution value
					// and if it doesn't:
					if(player[row][col] != solution[row][col]) {
						
						// return false, which will tell us there has been a mistake
						// and that is enough for us to know the player hasn't solved
						// the puzzle
						return false;
					}
				}
			}
		}
                
		// otherwise, if everything is correct, return true
		return true;
	}
         public boolean checkForSuccessM() {
		/*for(int row = 0; row<16; row++) {
			for(int col = 0; col<16; col++) { //TODO NOT 9 IT WILL BE 16
				
				// if the value in the initial array is zero, which means
				// the player has to input a value in the square
				if(initialM[row][col] == '-') {//TODO NOT 0 IT WILL BE -1
					
					// check if the player value corresponds to the solution value
					// and if it doesn't:
					if(player[row][col] != solutionM[row][col]) {
						
						// return false, which will tell us there has been a mistake
						// and that is enough for us to know the player hasn't solved
						// the puzzle
						return false;
					}
				}
			}
		}*/
                if(player[0][0]==solutionM[0][0])
                    return true;
               
		// otherwise, if everything is correct, return true
		return false;
	}
          public boolean checkForSuccessH() {
		for(int row = 0; row<16; row++) {
			for(int col = 0; col<16; col++) { //TODO NOT 9 IT WILL BE 16
				
				// if the value in the initial array is zero, which means
				// the player has to input a value in the square
				if(initialH[row][col] == '-') {//TODO NOT 0 IT WILL BE -1
					
					// check if the player value corresponds to the solution value
					// and if it doesn't:
					if(player[row][col] != solutionH[row][col]) {
						
						// return false, which will tell us there has been a mistake
						// and that is enough for us to know the player hasn't solved
						// the puzzle
						return false;
					}
				}
			}
		}
                
		// otherwise, if everything is correct, return true
		return true;
	}

}