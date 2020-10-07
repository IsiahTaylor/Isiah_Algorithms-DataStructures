package edu.metrostate.ics340.mx.it8343;

public class PalindromeGenerator {
	static int generate(int n) {
		// if the preconditions are not met then throw IllegalArgumentException();
		if (!(n > 0)) {
			throw new IllegalArgumentException();
		}
		int output = -1;
		// check planendrome
		if (isPalindrome(n) == true) {
			output = n;
			// If not it will run through a loop adding to itself until it is one or the
			// number gets too big
		} else {
			while (!(isPalindrome(n)) && n < 1000000) {
				n = generatePalindrome(n);
			}
			if(n < 1000000) {output = n;}
		}
		return output;
	}

	public static boolean isPalindrome(int input) {
		boolean output = true;
		// reverse the input
		int y = input;
		int reverse = 0;
		while (y > 0) {
			// grab the right most digit
			int x = y % 10;
			// add it to the left most end
			reverse = (reverse * 10) + x;
			// Get rid of the right most digit
			y = y / 10;
		}
		// check to see if they are the same
		if (input != reverse) {
			output = false;
		}
		return output;
	}

	private static int generatePalindrome(int input) {
		// reverse the number
		int y = input;
		int reverse = 0;
		while (y > 0) {
			int x = y % 10;
			reverse = (reverse * 10) + x;
			y = y / 10;
		}
		return input + reverse;
	}

	public static void main(String[] args) {
		//System.out.println("0:"+PalindromeGenerator.generate(0));
		System.out.println("1:"+PalindromeGenerator.generate(1));
		System.out.println("10:"+PalindromeGenerator.generate(10));
		System.out.println("11:"+PalindromeGenerator.generate(11));
		System.out.println("12:"+PalindromeGenerator.generate(12));
		System.out.println("100:"+PalindromeGenerator.generate(100));
		System.out.println("196:"+PalindromeGenerator.generate(196));

	}

}
