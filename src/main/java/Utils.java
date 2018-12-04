import java.io.File;
import java.util.Date;

public class Utils {
    public static String getResourcesPath() {
        return new File("").getAbsolutePath() + "/src/main/resources/";
    }
}
