package com.bbianchi;

import org.apache.commons.cli.Options;

/**
 * Container for Command Line Args
 */
public class AppOptions {

    static String LOAD_FROM_FILE_OPTION = "f";
    static String LOAD_FROM_FILE_OPTION_LONG_NAME = "file";
    static Options getOptions() {
        return new Options()
            .addOption(LOAD_FROM_FILE_OPTION, LOAD_FROM_FILE_OPTION_LONG_NAME, true, "Json File to Serialize");
    }
}