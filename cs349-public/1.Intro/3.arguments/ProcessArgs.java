import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Parse command line arguments
 * Assume that they always appear as a key:value pair, separated by spaces
 * Store each key:value pair in a HashMap for easy lookup during runtime
 * e.g. CommandLine -v 1 would produce entry {v=1]
 */

class ProcessArgs {
    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
        } else {
            // extract options from command line
            // IntelliJ passes sample arguments if you run from the IDE (Run-Edit Configuration)
            java.util.HashMap<String, String> options = parse(args);

            // print them to verify that it worked
            for (Map.Entry entry : options.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }
        System.out.println("");
    }

    // Print command-line syntax
    static void printHelp() {
        System.out.println("(c) 2019 Jeff Avery. Last revised: Aug 29, 2019.");
        System.out.println("Usage: java CommandLine [-arg val]");
    }

    // Build a dictionary of key:value pairs (without the leading "-")
    static HashMap<String, String> parse(String[] args) {
        HashMap<String, String> arguments = new HashMap<>();
        String key = null;
        String value = null;

        // process each argument as either a key or value in the pair
        for(String entry : args) {
            // assume that keys start with a dash
            if (entry.startsWith("-")) {
                // if we already have a key, and then find a second key 
                // before we've found the corresponding value, it's an error.
                if (key != null) {
                    System.out.println("Invalid key:pair, skipping key " + key);
                }
                key = entry.substring(1);   // skip leading "-"

                // values start with anything else
            } else {
                // if we already have a key, and then find a second key 
                // before we've found the corresponding value, it's an error.
                if (value != null) {
                    System.out.println("Duplicate value found. Skipping value " + value);
                }
                value = entry;
            }
            if (key != null && value != null) {
                arguments.put(key, value);
                key = null;
                value = null;
            }
        }

        //  check final values
        if (key != null) System.out.println("Invalid key:pair, skipping key " + key);
        if (value != null) System.out.println("Duplicate value found. Skipping value " + value);

        // return dictionary
        return arguments;
    }
}