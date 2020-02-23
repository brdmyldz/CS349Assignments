import java.io.File;

class Rename {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java rename [file1] [file2]");
			System.exit(0);
		}

		// get file references
		File file1 = new File(args[0]);
		File file2 = new File(args[1]);

		// check for errrors
		if (!file1.exists()) {
			System.out.println("Error: " + file1.toString() + " does not exist");
			System.exit(0);
		}

		if (file2.exists()) {
			System.out.println("Error: " + file2.toString() + " already exists");
			System.exit(0);
		}
		
		boolean success = file1.renameTo(file2);
		if (success) {
			System.out.println("Successfully renamed " + file1.toString() + " to " + file2.toString());
		} else {
			System.out.println("Error renaming " + file1.toString() + " to " + file2.toString());
		}
	}

}