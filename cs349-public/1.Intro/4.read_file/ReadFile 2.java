import java.io.BufferedReader;
import java.io.FileReader;

/* 
 * Display the contents of a test file
 * Demonstrates exception handling
 */

class ReadFile {
    public static void main(String[] args) {
        if (args.length == 1) {

            try {
                // wrap FileReader in a BufferedReader for IO
                FileReader reader = new FileReader(args[0]);
                BufferedReader bufferedReader = new BufferedReader(reader);
                
                // loop until EOF and print each line
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
                
                // close when complete
                bufferedReader.close();
            } catch (Exception ex) {
                System.out.println("Error reading file: " + ex.toString());
            } 
        } else {
            displayHelp();
        }
    }
    static void displayHelp() {
        System.out.println("(c) 2019 Jeff Avery. Sept 13, 2019");
        System.out.println("Usage: java ReadFile [filename]");
    }
 }