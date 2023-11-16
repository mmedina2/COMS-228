package edu.iastate.cs228.hw4;

import java.util.Stack;

public class MessageTree {

	public MessageTree R;
	public MessageTree L;
	public char Char1;
	private static String Bin;
	
	/**
	 * @author Marcos Medina
	 */

	/**
	 * Constructor makes tree from the encoded string 
	 * 
	 * @param EncodingSTR String from File 
	 */
	public MessageTree(String EncodingSTR) {
		if (EncodingSTR == null || EncodingSTR.length() < 2) {
			return;
		}
		Stack<MessageTree> stk = new Stack<>();
		int ID = 0;
		this.Char1 = EncodingSTR.charAt(ID++);
		stk.push(this);
		MessageTree Current = this;
		String LastOut = "in";
		while (ID < EncodingSTR.length()) {
			MessageTree node = new MessageTree(EncodingSTR.charAt(ID++));
				if (LastOut.equals("in")) {
					Current.L = node;
				if (node.Char1 == '^') {
					Current = stk.push(node);
					LastOut = "in";
				} else {
					if (!stk.empty())
						Current = stk.pop();
					LastOut = "out";
				}
			} else {
				Current.R = node;
				if (node.Char1 == '^') {
					Current = stk.push(node);
					LastOut = "in";
				} else {
					if (!stk.empty())
						Current = stk.pop();
					LastOut = "out";
				}
			}
		}
	}

	/**
	 * Constructor for one node with null R and L 
	 * 
	 * @param Char1
	 */
	public MessageTree(char Char1) {
		this.R = null;
		this.L = null;
		this.Char1 = Char1;
	}

	/**
	 * Decodes the message using the variables  
	 * 
	 * @param Codes
	 * @param Message 
	 */
	public void Solve(MessageTree Codes, String Message) {
		System.out.println("MESSAGE:");
		StringBuilder StringBuilder = new StringBuilder();
		MessageTree Current = Codes;
		for (int i = 0; i < Message.length(); i++) {
			char Character = Message.charAt(i);
			Current = (Character == '0' ? Current.L : Current.R);
			if (Current.Char1 != '^') {
				Get(Codes, Current.Char1, Bin = "");
				StringBuilder.append(Current.Char1);
				Current = Codes;
			}
		}
		System.out.println(StringBuilder.toString());
		statistc(Message, StringBuilder.toString());
	}
	
	/**
	 * Method print Characters and binary 
	 * 
	 * @param Root
	 * @param Code
	 */
	public static void print(MessageTree Root, String Code) {
		System.out.println("Character Code\n-------------------------");
		for (char Char : Code.toCharArray()) {
			Get(Root, Char, Bin = "");
			System.out.println("    " + (Char == '\n' ? "\\n" : Char + " ") + "    " + Bin);
		}
	}


	
	/**
	 * gets the code and calls itself to set the variables 
	 * 
	 * @param Root
	 * @param Character 
	 * @param Path
	 * @return
	 */
	private static boolean Get(MessageTree Root, char Character, String Path) {
		if (Root != null) {
			if (Root.Char1 == Character) {
				Bin = Path;
				return true;
			}
			return Get(Root.R, Character, Path + "1") || Get(Root.L, Character, Path + "0");
		}
		return false;
	}

	/**
	 * Prints out the statistics 
	 * 
	 * @param EString
	 * @param DString
	 */
	private void statistc(String EString, String DString) {
		System.out.println("Statistics:");
		System.out.println(String.format("Avg bits/char:\t%.1f", EString.length() / (double) DString.length()));
		System.out.println("Total Characters:\t" + DString.length());
		System.out.println(
				String.format("Space Saving:\t%.1f%%", (1d - DString.length() / (double) EString.length()) * 100));
	}
}
