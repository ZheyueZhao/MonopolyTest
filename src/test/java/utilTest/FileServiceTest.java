package utilTest;

import org.bihe.util.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/*
* Important: delete newread text file before running tests for all to pass
* */

public class FileServiceTest {
    static String existingFilepath = "./src/test/java/utilTest/fileservice.txt";
    static String newFilePath = "./src/test/java/utilTest/newfileservice.txt";
    static String neverExistingFilePath = "./src/test/java/utilTest/newread.txt";

    @Test
    public void testBasicFileWriteToExistingFile() {
        assertTrue(FileService.writeObj("hello world", existingFilepath));
    }

    @Test
    public void testBasicFileWriteToNewFile () {
        assertTrue(FileService.writeObj("hello world", newFilePath));
    }

    @Test
    public void testBasicFileRead() {
        assertEquals("hello world", FileService.readObj(existingFilepath));
    }

    @Test
    public void testFileReadNullOnNewFile() {
        assertNull(FileService.readObj(neverExistingFilePath));
    }

    @AfterAll
    public static void cleanup() {
        File file = new File(newFilePath);

        if (!file.delete()) {
            System.out.println("Issue deleting");
        }
    }
}
