import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Sodoku {
	/**
	 * 
	 * in this class i have global vaiable grid that will be filled by read puzzle 
	 * to be able to check the other methods in side the class
	 * 
	 * */
	 

	static int [][] grid = new int [9][9];
	int gridsGenerated = 0;

	
	// sets the path from the game method 
	public Sodoku(String path) throws FileNotFoundException {
		readPuzzle(path);
	}

	
	/**
	 * 
	 * @param filePath
	 * @return grid
	 * @throws FileNotFoundException
	 * 
	 * description: reads in the puzzle and sets all the cells in the grid 
	 * 
	 */
	
	public int[][] readPuzzle(String filePath) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(filePath)); 
		
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (sc.hasNextInt()) {
		
					grid[row][column] = sc.nextInt();
				
					}
				}
			}
		
		sc.close();
		
		return grid;
		
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return candidate
	 * 
	 * shortens the canidids in the row and collum and mini grid 
	 * and populates the array list 
	 * 
	 */
	
	public ArrayList<Integer> getCandidates(int row, int col){
		
		int [] candidate = {1,2,3,4,5,6,7,8,9};
		
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		for(int v : candidate) {
			 candidates.add(v);
		}
		
		for(int i = 0; i < grid.length; i++) {
			if(candidates.contains(grid[i][row])) {
				candidates.remove(new Integer(grid[i][row]));	
			}
		}
		
		for(int i = 0; i < grid.length; i++) {
			if(candidates.contains(grid[col][i])) {
				candidates.remove(new Integer(grid[col][i]));	
			}
		}
		
		// sets bounds of row
		
		int rowi = row - row % 3;

		// sets bounds for column
		
		int coli = col - col % 3;
		
		for (int r = 0; r < (row + rowi); row++) {
			for (int c = 0; c < (col + coli); c++) {
				
				int cv = grid[r][c];
				
				if(candidates.contains(cv)) {
					candidates.remove(new Integer(cv));	
				}

			}
		}
		

		return candidates;
		
	}
	
	/**
	 * 
	 * @param row
	 * @return isCorrect
	 * 
	 * checks if row has 1-9 and checks if the candidates list is empty
	 * 
	 */
	
	public Boolean isRowValid(int row) {
		
		boolean isCorrect =  true;
		
		
		
		ArrayList<Integer> correctCandidates = new ArrayList<Integer>();
		
		for(int i = 0; i < 9 ; i++) {
			
			correctCandidates = getCandidates(row, i);
			
		}
		
		if (correctCandidates.isEmpty()) {
			isCorrect = false;
		}
		
		
		// check if row has 1-9
		
		int [] check = {1,2,3,4,5,6,7,8,9};
		
		ArrayList<Integer> onethruninecheck = new ArrayList<Integer>();
		
		for(int v : check) {
			onethruninecheck.add(v);
		}
		
		for(int i = 0; i < grid.length; i++) {
			if(onethruninecheck.contains(grid[row][i])) {
				onethruninecheck.remove(new Integer(grid[row][i]));	
				
				if (onethruninecheck.isEmpty()) {
					isCorrect = true;
				}
			}
		}
		
		
		
		return isCorrect;
		
	}
	
	/**
	 * 
	 * @param col
	 * @return isCorrect
	 * 
	 * checks if col has 1-9 and checks if the candidates list is empty
	 * 
	 * 
	 */
	
	public Boolean isColumnValid(int col) {
		
		boolean isCorrect =  true;
		
		ArrayList<Integer> correctCandidates = new ArrayList<Integer>();
		
		for(int i = 0; i < 9 ; i++) {
			
			correctCandidates = getCandidates(i, col);
			
		}
		
		if (correctCandidates.isEmpty()) {
			isCorrect = false;
		}
		
		int [] check = {1,2,3,4,5,6,7,8,9};
		
		ArrayList<Integer> onethruninecheck = new ArrayList<Integer>();
		
		for(int v : check) {
			onethruninecheck.add(v);
		}
		
		for(int i = 0; i < grid.length; i++) {
			if(onethruninecheck.contains(grid[i][col])) {
				onethruninecheck.remove(new Integer(grid[i][col]));	
				
				if (onethruninecheck.isEmpty()) {
					isCorrect = true;
				}
			}
		}
		
		return isCorrect;
		
	}
	
	/**
	 * 
	 * @param col , row
	 * @return isCorrect
	 * 
	 * checks if sub grid has 1-9 and checks if the candidates list is empty
	 * 
	 * 
	 */
	
	public Boolean isSubGridValid(int row, int col) {
		
		boolean isCorrect =  true;
				
		
		//  creates checking list of one thru nine to mark off 
		
		int [] check = {1,2,3,4,5,6,7,8,9};
		
		ArrayList<Integer> onethruninecheck = new ArrayList<Integer>();
		
		// adds all the candidates into the array list 
		
		for(int v : check) {
			onethruninecheck.add(v);
		}
		
		// sets up the size of the sub grid
		
		int rowi = row - row % 3;
		int coli = col - col % 3;
		
		for(int i = 0; i < (row + rowi); i++) {
			for (int c = 0; c < (col + coli); c++) {
				if(onethruninecheck.contains(grid[i][c])) {
					onethruninecheck.remove(new Integer(grid[i][c]));	
					
					if (onethruninecheck.isEmpty()) {
						isCorrect = true;
					}
					
					else {
						isCorrect = false;
					}
				}
			
			}
		}
		
		return isCorrect;
		
	}
	
	/**
	 * 
	 * @return grid
	 * 
	 * fills and trys to solve the 2d array 
	 * by checking if it is zero and puts in a random value of the canidit list 
	 * 
	 * 
	 */
	
	public int[][] solve(){
		
		// creates random variable
		
		Random rand = new Random();
		
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		boolean filled = false;
			
		
		for (int r = 0; r < grid.length; r++)
		{
			for (int c = 0; c < grid.length; c++)
			{					
				int value = grid[r][c];
				
				candidates = getCandidates(r, c);	
				
				if (candidates.size() == 0) {
					break;
				}
				
				System.out.println(candidates);
				
									
				if (value == 0) {
									
					// creates a number based on random and the cinidite array list size
					value = candidates.get(rand.nextInt(candidates.size()));
					
					
					grid[r][c] = value;	
					
				}
				
				toString();
				
				gridsGenerated++;
			}
			System.out.println();
		}
	
		
		return grid;
		
		
	}
	
	
	/**
	 * 
	 * @return null
	 * 
	 * returns grid of the sodoku table
	 * 
	 * 
	 */
	public String toString() {
		
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				
				System.out.print(grid[row][column] + " ");

			}
			System.out.println();
		}
		return null;
		
	}
	
	/**
	 * 
	 * @return gridsGenerated
	 * 
	 * returns the number of grids 
	 */
	
	public int getgridsGenerated() {
		
		
		
		return gridsGenerated;
		
	}
	
	
}
