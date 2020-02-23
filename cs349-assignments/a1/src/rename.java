import java.awt.desktop.SystemEventListener;
import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;


public class rename {

    public static void main(String[] args) {
        if (args.length == 0) {
            optionMissing();
        }

        /*The reason of this is because I want to remove "-file" and
        it's arguments once I read them all*/
        List<String> newArgs = new ArrayList<String>();
        for(String input:args) {
            newArgs.add(input);
        }

        //We should check this first because command might be valid just because we have help in it
        for(String input:newArgs){
            if (input.equals("-help")) {
                printHelp();
            }
        }


        //Now work on -file and it's arguments because we need to store them before modify them
        boolean fileNameFlag = false;
        List<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < newArgs.size(); i++) {
            if (newArgs.get(i).equals("-file")) {
                fileNameFlag = true;
                if (newArgs.size() > i + 1) {
                    if (dashChecker(newArgs.get(i+1))){
                        argumentMissing();
                    }

                    fileNames.add(newArgs.get(i+1));
                    //Check the next arguments to see if there are more file names
                    int fileCounter = 1;
                    for(int extraFiles = i + 2; extraFiles < newArgs.size(); extraFiles++) {
                        if(dashChecker(newArgs.get(extraFiles))){
                            break;
                        }
                        fileCounter++;
                        fileNames.add(newArgs.get(extraFiles));
                    }

                    //Now lets remove the "-file" and it's argument from newArgs
                    for(;fileCounter > 0; fileCounter--){
                        newArgs.remove(i+1);
                    }
                    newArgs.remove(i);
                    i--; //If we don't do this, we will skip an argument

                } else { // Means next argument doesn't exist
                    argumentMissing();
                }
            }
        }

        if(fileNameFlag == false){
            System.out.println("Error: -file option is missing!");
            printHelp();
        }

        //This is a tester to see if the big ass function above worked or not
        for(String input:newArgs){
            System.out.println(input);
        }
        for(String input:fileNames){
            System.out.println(input);
        }



        //We need to check if the files exist in the directory
        for(String input:fileNames){
            File file = new File(input);
            if(!file.exists()){
                System.out.println("Error: " + file.toString() + " does not exist");
                System.exit(0);
            }
            file = null;
        }


        if(newArgs.size() < 1){
            System.out.println("Error: Option(s) are missing!");
            printHelp();
        }

        //Now we need to copy the fileArgument ArrayList
        List<String> newFileNames = new ArrayList<String>();
        for(String name:fileNames){
            newFileNames.add(name);
        }

        for(int i = 0; i < newArgs.size(); i++) {
            if (newArgs.get(i).equals("-replace")) {
                if (newArgs.size() < i + 2) {
                    argumentMissing();
                }


                String firstArg = newArgs.get(i + 1);
                String secondArg = newArgs.get(i + 2);
                if (dashChecker(firstArg) || dashChecker(secondArg)) {
                    argumentMissing();
                }

                if (firstArg.equals("@date") || firstArg.equals("@time")) {
                    firstArg = generateDateTime(firstArg);
                }

                if (secondArg.equals("@date") || secondArg.equals("@time")) {
                    secondArg = generateDateTime(secondArg);
                }

                for (int fileCounter = 0; fileCounter < newFileNames.size(); fileCounter++) {
                    if (!newFileNames.get(fileCounter).contains(firstArg)) {
                        System.out.println("Error: " + firstArg +
                                " does not exist in file " + newFileNames.get(fileCounter));
                        System.exit(0);
                    }

                    newFileNames.set(fileCounter, newFileNames.get(fileCounter).replaceAll(firstArg, secondArg));
                }

                //We need to increment i because we used the arguments
                i = i + 2;
            } else if (newArgs.get(i).equals("-prefix") || newArgs.get(i).equals("-suffix")) {
                if (newArgs.size() > i + 1) {
                    if (dashChecker(newArgs.get(i + 1))) {
                        argumentMissing();
                    }
                    String fix = newArgs.get(i + 1);
                    if (fix.equals("@date") || fix.equals("@time")) {
                        fix = generateDateTime(fix);
                    }
                    int argCounter = 1;
                    for (int extraArgs = i + 2; extraArgs < newArgs.size(); extraArgs++) {
                        if (dashChecker(newArgs.get(extraArgs))) {
                            break;
                        }
                        if (newArgs.get(extraArgs).equals("@date") || newArgs.get(extraArgs).equals("@time")) {
                            newArgs.set(extraArgs, generateDateTime(newArgs.get(extraArgs)));
                        }
                        System.out.println(newArgs.get(extraArgs));
                        fix += newArgs.get(extraArgs);
                        argCounter++;
                    }

                    if (newArgs.get(i).equals("-prefix")) {
                        for (int file = 0; file < newFileNames.size(); file++) {
                            newFileNames.set(file, fix.concat(newFileNames.get(file)));
                        }
                    } else { //"-suffix" case
                        for (int file = 0; file < newFileNames.size(); file++) {
                            newFileNames.set(file, newFileNames.get(file).concat(fix));
                        }
                    }
                    //Now we need to increment i because we already used the arguments
                    i = i + argCounter;

                } else {
                    argumentMissing();
                }

            } else {
                optionMissing();
            }
        }



        for(int i = 0; i < fileNames.size(); i++){
            File origFile = new File(fileNames.get(i));
            File newFile = new File(newFileNames.get(i));

            if(newFile.exists()){
                System.out.println("Error: " + newFile.toString() + " already exist");
                System.exit(0);
            }

            boolean success = origFile.renameTo(newFile);
            if (success) {
                System.out.println("Successfully renamed " + origFile.toString() + " to " + newFile.toString());
            } else {
                System.out.println("Error renaming " + origFile.toString() + " to " + newFile.toString());
            }
        }


    }

    static boolean dashChecker(String s){
        return s.startsWith("-");
    }

    static String generateDateTime(String change){
        SimpleDateFormat datePattern = new SimpleDateFormat();
        if(change.equals("@date")) {
            datePattern = new SimpleDateFormat("MM-dd-yyyy");
        }
        if(change.equals("@time")) {
            datePattern = new SimpleDateFormat("hh-mm-ss");
        }
        return datePattern.format(new Date());
    }

    static void printHelp() {
        System.out.println("(c) 2020 Berdem Yildiz. Last revised: January 21, 2020.");
        System.out.println("Usage: java rename [-option argument1 argument2 ...]");
        System.out.println("");
        System.out.println("Options: ");
        System.out.println("-help                     :: print out a help page including student name, correct usage and options, and exit the program.");
        System.out.println("-prefix [string]          :: rename [filename] so that it starts with [string].");
        System.out.println("-suffix [string]          :: rename [filename] so that it ends with [string].");
        System.out.println("-replace [str1] [str2]    :: rename [filename] by replacing all instances of [str1] with [str2].");
        System.out.println("-replace [str1] [str2]    :: rename [filename] by replacing all instances of [str1] with [str2].");
        System.out.println("-file [filename]          :: denotes the [filename] do be modified.);");
        System.exit(0);
    }

    static void argumentMissing() {
        System.out.println("Error: Arguments are missing");
        System.exit(0);
    }

    static void optionMissing() {
        System.out.println("Error: Option(s) are missing or unknown!");
        System.exit(0);
    }

    /*static void bothMissing() {
        System.out.println("Error: Option and arguments are missing!");
        System.exit(0);
    }*/

}