package com.bbianchi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.bbianchi.view.View;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



public class App 
{

    /**
     * Code used to signal terminals that this app failed.
     */
    static int APPLICATION_FAILURE_CODE = -1;

    /**
     * This Application's Name
     */
    static String APPLICATION_NAME = "Config Parser";

    static View deserializedView = null;

    public static void main( String[] args )
    {
        Options appOptions = AppOptions.getOptions();

        CommandLine cmd = null;
        CommandLineParser parser = new DefaultParser();
        
        
        try {
            cmd = parser.parse( appOptions, args);
        } catch (ParseException parseException) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Config Parser", appOptions );
            System.exit(APPLICATION_FAILURE_CODE);
        }

        if (cmd.hasOption(AppOptions.LOAD_FROM_FILE_OPTION)) {
            deserializedView = readViewFromFile(cmd.getOptionValue(AppOptions.LOAD_FROM_FILE_OPTION));
        } else {
            // I created a new object mapper here because I do not want to keep the string in memory.
            try {
                deserializedView = new ObjectMapper().readValue(new Scanner(System.in).next(), View.class);
            } catch (Exception e) {
                System.err.println("Unable to Parse Stdin");
                System.exit(APPLICATION_FAILURE_CODE);
            }

        }
    }

    static View readViewFromFile(String filePath) throws IOException {

        View viewFromFile = null;

        if (filePath == null || filePath.isEmpty()) {
            System.err.println("File Not Found");
            throw new FileNotFoundException(filePath);
        }

        try {
            viewFromFile = new ObjectMapper().readValue(new File(filePath), View.class);
        } catch (IOException exception) {
            System.err.println("Unable to Read File. " + filePath);
            throw exception;
        }

        return viewFromFile;

    }
}
