package utils;

import java.io.File;

public class PathUtils {
    public static String getResourcesPath() {
        return new File("").getAbsolutePath() + "/src/main/resources/";
    }
}
