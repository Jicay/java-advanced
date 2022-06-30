import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileSearch {
    public static String searchFile(String fileName) {

        File file = new File("documents");
        return findIn(file, fileName);
    }

    static String findIn(File file, String fileName) {
        List<String> files = Arrays.asList(file.listFiles())
                .stream()
                .map(aFile -> {
                    if (aFile.isDirectory())
                        return findIn(aFile, fileName);
                    else if (aFile.getName().contains(fileName))
                        return aFile.getPath();
                    else
                        return null;
                })
                .filter(Objects::nonNull).toList();
        if( files .isEmpty()) return null;
        else return files .get(0);
    }
}