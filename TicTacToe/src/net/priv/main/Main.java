package net.priv.main;

import java.io.IOException;
import java.util.Scanner;

public class Main {	
	
	public Scanner anykey;
	
	public int maxRounds = 9;
	public int tableLenght = 3;
	
	public char[][] table = {{'-','-','-'}, {'-','-','-'}, {'-','-','-'}}; 
	public char[] symbols = {'X','O'};
	public char winnerSymbol;
	
	public Main() {
		anykey = new Scanner(System.in);
	}
	
	public void startGame() {
		for(int t = 0; t < maxRounds; t++) {
			System.out.println("Round: "+(t+1));
			drawTable();
			
			System.out.println("Write the line number:");
			
			int y = tryParse(anykey.nextLine());
			
			System.out.println("Write the column number:");
			
			int x = tryParse(anykey.nextLine());
			
			if(y < 2 && y >= 0 && x < 2 && x >= 0) {
				if(table[x][y] == '-') {
					if((t+1)%2 != 0) {
						table[x][y] = symbols[0];
					}else {
						table[x][y] = symbols[1];
					}
				}else {
					System.out.println("This space ["+x+"]["+y+"] is already being used");
					t--;
				}
			}else {
				System.out.println("Invalid number. Try again.");
				t--;
			}
			
			if(checkWinner(table)) {
				endGame();
				t = maxRounds;
			}
		}
		
		if(!checkWinner(table))tieGame();
	}
	
	public static Integer tryParse(String input) {
	  try {
	    return Integer.parseInt(input);
	  } catch (NumberFormatException e) {
		return 3;
	  }
	}
	
	public void endGame() {
		System.out.println("GAME OVER!!!!");
		if (winnerSymbol == symbols[0]) {
			System.out.println("Player 1 won the game!");
		}else {
			System.out.println("Player 2 won the game!");
		}
		System.out.println("Press Enter to play again or close the terminal to endgame!");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetGame();
	}
	
	public void tieGame() {
		System.out.println("GAME OVER!!!!");
		System.out.println("I GUESS THIS IS A TIE!");
		System.out.println("Try again!");
		System.out.println("Press Enter to continue or close the terminal to endgame!");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetGame();
	}
	
	public void resetGame() {
		for(int y = 0; y < tableLenght; y++) {
			for(int x = 0; x < tableLenght; x++) {
				table[x][y] = '-';
			}
		}
		
		startGame();
	}
	
	public void drawTable() {
		System.out.println("----0-1-2----");
		for(int y = 0; y < tableLenght; y++) {
			System.out.print(y+"->|");
			for(int x = 0; x < tableLenght; x++) {
				System.out.print(table[x][y]+"|");
			}
			System.out.println("<--");
			System.out.println("-------------");
		}
	}
	
	public boolean checkWinner(char[][] table) {
		if(checkRow(table) || checkColumn(table) || checkDiagonal(table)) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkRow(char[][] table) {
		for(int r = 0; r < tableLenght; r++) {
			//check for defaults on the row
			if(table[r][0] != '-' && table[r][1] != '-' && table[r][2] != '-') {
				//no defaults on this line
				if(table[r][0] == table[r][1] && table[r][1] == table[r][2]) {
					winnerSymbol = table[r][0];
					return true;
				}
			} 
		}
		
		return false;
	}
	
	public boolean checkColumn(char[][] table) {
		
		for(int c = 0; c < tableLenght; c++) {
			//check for defaults on the column
			if(table[0][c] != '-' && table[1][c] != '-' && table[2][c] != '-') {
				//no defaults on this line
				if(table[0][c] == table[1][c] && table[1][c] == table[2][c]) {
					winnerSymbol = table[0][c];
					return true;
				}
			} 
		} 
		
		return false;
	}
	
	public boolean checkDiagonal(char[][] table) {
		//check for defaults on the diagonal
		//diagonal
		if(table[0][0] != '-' && table[1][1] != '-' && table[2][2] != '-') {
				if(table[0][0] == table[1][1] && table[1][1] == table[2][2]) {
					winnerSymbol = table[1][1];
					return true;
				}
		}
		
		if(table[0][2] != '-' && table[1][1] != '-' && table[2][0] != '-') {
			if(table[0][2] == table[1][1] && table[1][1] == table[2][0]) {
				winnerSymbol = table[1][1];
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		Main main = new Main();
		
		System.out.println("Hello Players");
		System.out.println("Player 1 -> X");
		System.out.println("Player 2 -> O");
		System.out.println("");
		System.out.println("Press enter to start");
		
		if(main.anykey.nextLine() != null) {
			main.startGame();
		}
	}
	
}
