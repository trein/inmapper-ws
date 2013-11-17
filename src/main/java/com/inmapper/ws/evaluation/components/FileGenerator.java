package com.inmapper.ws.evaluation.components;

import java.io.File;

public class FileGenerator {
    
    private static final String DATA_DIR_PATTERN = "." + File.separatorChar + "audit" + File.separatorChar + "%s"
            + File.separatorChar + "%s";
    
    private static final String IMAGE_DIR_PATTERN = "." + File.separatorChar + "audit" + File.separatorChar + "%s"
            + File.separatorChar + "%s.png";
    
    public static File newFileForData(String operation, String token) {
        return new File(getDataFilename(operation, createNewWith(token)));
    }
    
    public static File existentFileForData(String operation, String filename) {
        return new File(getDataFilename(operation, filename));
    }
    
    public static File newFileForImage(String operation, String token) {
        return new File(getImageFilename(operation, createNewWith(token)));
    }
    
    public static File existentFileForImage(String operation, String filename) {
        return new File(getImageFilename(operation, filename));
    }
    
    private static String createNewWith(String token) {
        return String.format("%s-%s", String.valueOf(System.currentTimeMillis()), token);
    }
    
    private static String getDataFilename(String operation, String file) {
        return String.format(DATA_DIR_PATTERN, operation, file);
    }
    
    private static String getImageFilename(String operation, String file) {
        return String.format(IMAGE_DIR_PATTERN, operation, file);
    }
    
}
