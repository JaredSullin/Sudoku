import java.io.FileNotFoundException;

public class Game {
	
	/**
	 * @author Jared sullin
	 * St.thomasuniverity
	 * 
	 * Description: program that will read a sodoku puzzle path from command prompt 
	 * and returns a state of the puzzle 
	 * 
	 * file has to be in scr folder 
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * 
	 * 
	 */

	public static void main(String[] args) throws FileNotFoundException {
		 
		// takes in arguments in command prompt
		String path = args[0];
		
		Sodoku sd = new Sodoku(path);
		
		
		//sd.getCandidates(0, 0);
		
		sd.solve();
		
		
		
	}

}
