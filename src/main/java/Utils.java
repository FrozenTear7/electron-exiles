import java.io.File;

public class Utils {
    public static String getResourcesPath() {
        return new File("").getAbsolutePath() + "/src/main/resources/";
    }
}
