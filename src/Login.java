import java.io.Console;
import java.io.IOException;

public class Login {

    //array of usernames and passwords
    static String[][] users = {
        {"alice", "1234"},
        {"kami", "4210"},
        {"charlie", "9012"}
    };

    //  check if the username and password are correct
    static boolean isValid(String username, String password) {
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Console console = System.console(); // Get the console object
        int tries = 3; // 3 tries allowed
        boolean success = false; // A flag to indicate if the login is successful

        while (tries > 0 && !success) {
            System.out.print("Enter username: ");
            String username = console.readLine(); // Read the username
            System.out.print("Enter password: ");
            String password = hidePasswordInput(); // Read and mask the password

            // Check if the username and password are valid
            if (isValid(username, password)) {
                System.out.println("Login successful!");
                success = true; // Set the flag to true
            } else {
                System.out.println("Invalid username or password.");
                tries--; // reduce the number of tries after the first and second try
            }
        }

        if (!success) {
            System.out.println("You have exceeded the number of tries.");
        }
    }

    //  hide the password input with asterisks
    public static String hidePasswordInput() {
        String password = ""; //  store the password to a variable
        int ch; // A variable to store each byte of data

        // Loop until the user presses enter
        do {
            try {
                ch = System.in.read(); 
            } catch (IOException e) {
                ch = -1; 
            }
            if (ch >= 32 && ch <= 126) { 
                password += (char) ch; 
                System.out.print("*"); 
            }
        } while (ch != '\n'); // Repeat until a newline is encountered
        System.out.println();

        return password.replace("\r", ""); 
    }
}
