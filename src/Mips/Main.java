package Mips;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file = "code5";
		File inputDBfile = new File(file);
		Scanner input = new Scanner(inputDBfile);
		parser p = new parser(input);
		
	}
	

}
