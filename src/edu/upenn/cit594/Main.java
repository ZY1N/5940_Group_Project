package edu.upenn.cit594;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        if(args.length > 4 || args.length < 1){
            System.out.println("Enter Format:--population=pop.csv --covid=cov.csv --properties=props.csv --log=log.txt");
            return;
        }

        //check format regex
        Pattern pattern = Pattern.compile("^--(?<name>.+?)=(?<value>.+)$");
        //store in here
        Map<String, String> arguments = new HashMap<>();
        Set<String> seen = new HashSet<>();
        Set<String> validKeys = Set.of("population", "covid", "properties", "log");

        for( int i = 0; i < args.length; i++ ){
            String arg = args[i];
            //check regex
            Matcher matcher = pattern.matcher(arg);
            if(!matcher.matches()){
                System.out.println("Format doesn't match:--population=pop.csv --covid=cov.csv --properties=props.csv --log=log.txt");
                return;
            }
            arg = arg.substring(2);
            String[] splitArg = arg.split("=");
            String key = splitArg[0];
            String value = splitArg[1];
            String[] fileParts = value.split("\\.");
            if(fileParts.length < 2){
                System.out.println("Invalid or missing extension");
            }
            String extension = fileParts[1].toLowerCase();


            if (!validKeys.contains(key)) {
                System.out.println("Invalid argument key: " + key);
                return;
            }

            if( !extension.equals("csv") &&
                    (key.equals("population")
                            || key.equals("log")
                            || key.equals("properties"
                    ))){
                System.out.println(key + " : only takes csv");
                return;
            }

            if((!extension.equals("csv") &&
                    !extension.equals("json")) &&
                    key.equals("covid")){
                    System.out.println(key + " : take only csv or json");
                    return;
            }

            //check to see if key are proper
//            if(i == 0){
//                if(!key.equals("population")){
//                    System.out.println("Format doesn't match:--population=pop.csv --covid=cov.csv --properties=props.csv --log=log.txt");
//                    return;
//                }
//                if(!extension.equals("csv")){
//                    System.out.println("");
//                    return;
//                }
//            } else if (i == 1 ) {
//                if(!key.equals("covid")){
//                    System.out.println("Format doesn't match:--population=pop.csv --covid=cov.csv --properties=props.csv --log=log.txt");
//                    return;
//                }
//                if(!extension.equals("csv") && !extension.equals("json")){
//                    System.out.println("");
//                    return;
//                }
//
//            }  else if (i == 2 ) {
//                if(!key.equals("properties")){
//                    System.out.println("Format doesn't match:--population=pop.csv --covid=cov.csv --properties=props.csv --log=log.txt");
//                    return;
//                }
//                if(!extension.equals("csv")){
//                    System.out.println("CSV expected in properties");
//                    return;
//                }
//            } else if (i == 3) {
//                if(!key.equals("log")){
//                    System.out.println("Format doesn't match:--population=pop.csv --covid=cov.csv --properties=props.csv --log=log.txt");
//                    return;
//                }
//                if(!extension.equals("csv")){
//                    System.out.println("CSV expected in log");
//                    return;
//                }
//            }

            //keep track of used values
            if(seen.contains(key)){
                System.out.println("Duplicate keys");
            }
            else{
                seen.add(key);
            }

            //now check to see if it can be read
            //The logger cannot be correctly initialized
            //The specified input files do not exist or cannot be opened for reading (e.g., because of file
            //permissions)
            File file = new File(value);

            //keep this to use later
            arguments.put(key, value);
        }




        //The logger cannot be correctly initialized


        //The specified input files do not exist or cannot be opened for reading (e.g., because of file
        //permissions)


    }
}
