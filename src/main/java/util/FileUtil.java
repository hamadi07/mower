package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.io.FileUtils.readLines;


public class FileUtil {
    public File readFile(String path) {
        return new File("resources/" + path);
    }

    public List<String> redLines(File file) throws IOException {
        return readLines(file, "UTF-8");
    }
}
