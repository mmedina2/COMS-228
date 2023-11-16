package edu.iastate.cs228.hw4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author Marcos Medina 
 *
 */
public class MainMethod {

	public static void main(String[] args) throws IOException {
		System.out.println("Please enter filename to decode:");
		Scanner Scanner = new Scanner(System.in);
		String fileName = Scanner.nextLine();
		Scanner.close();

		// Reads all Characters to the variables 
		String IT = new String(Files.readAllBytes(Paths.get(fileName))).trim();
		int Possition = IT.lastIndexOf('\n');
		String Pattern = IT.substring(0, Possition); 
		String Bin = IT.substring(Possition).trim();

		Set<Character> Characters = new HashSet<>();
		for (char Char2 : Pattern.toCharArray()) {
			if (Char2 != '^') {
				Characters.add(Char2);
			}
		}
		
		String DCharacter = Characters.stream().map(String::valueOf).collect(Collectors.joining());

		// Calls other class 
		MessageTree Root = new MessageTree(Pattern);
		MessageTree.print(Root, DCharacter);
		Root.Solve(Root, Bin);
	}
}
