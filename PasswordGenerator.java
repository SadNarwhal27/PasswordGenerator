import java.util.Random;

public class PasswordGenerator {
	
	// Final Strings of each group of characters accessed to make the password
	static String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String LOWERS = CAPITALS.toLowerCase();
	static String SPECIALS = "!@#$%&?";
	static String NUMBERS = "1234567890";

	// Minimum number of specific characters needed to create a successful password.
	static int minCapitals = 1;
	static int minLowers = 1;
	static int minSpecials = 1;
	static int minNumbers = 1;

	// Takes in a password length and randomly generates a password
	public static String generatePassword(int length) {
		Random rand = new Random();
		
		// An array of each character in the password
		char[] password;
		
		// The final output given to the user
		String output;

		// The actual generator
		do {
			// Primes the char array and output string to empty
			password = new char[length];
			output = "";

			// Creates the base password out of all lowercase letters
			for (int i = 0; i < password.length; i++)
				password[i] = LOWERS.charAt(rand.nextInt(LOWERS.length()));
			
			// Replaces random characters in the password with specified characters to add complexity
			for (int i = 0; i < minCapitals; i++)
				password[rand.nextInt(password.length)] = CAPITALS.charAt(rand.nextInt(CAPITALS.length()));
			for (int i = 0; i < minSpecials; i++)
				password[rand.nextInt(password.length)] = SPECIALS.charAt(rand.nextInt(SPECIALS.length()));
			for (int i = 0; i < minNumbers; i++)
				password[rand.nextInt(password.length)] = NUMBERS.charAt(rand.nextInt(NUMBERS.length()));

			// Converts the password char[] to a usable string
			for (int i = 0; i < password.length; i++)
				output += password[i];
			
			// Checks if the password has the minimum amount of complexity to be output to the user
		} while (!checkPassword(password));

		return output;
	}
	
	// Checks if the password has the required complexity to be output or regenerated if not complex enough
	public static boolean checkPassword(char[] password) {
		// A simple int[] used to count each character in the password and compare to minimum requirements
		int[] presentChars = new int[4];

		// Goes through each character in the password and increments counts across the 4 categories
		for (char c : password) {
			for (int i = 0; i < CAPITALS.length(); i++)
				if (c == CAPITALS.charAt(i)) {
					presentChars[0]++;
					continue;
				}
			for (int i = 0; i < LOWERS.length(); i++)
				if (c == LOWERS.charAt(i)) {
					presentChars[1]++;
					continue;
				}
			for (int i = 0; i < SPECIALS.length(); i++)
				if (c == SPECIALS.charAt(i)) {
					presentChars[2]++;
					continue;
				}
			for (int i = 0; i < NUMBERS.length(); i++)
				if (c == NUMBERS.charAt(i)) {
					presentChars[3]++;
					continue;
				}
		}

		// Checks if the password follows the complexity requirements and returns true or false depending
		if (presentChars[0] >= minCapitals && presentChars[1] >= minLowers && presentChars[2] >= minSpecials
				&& presentChars[3] >= minNumbers)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		System.out.println(generatePassword(12));

	}

}
