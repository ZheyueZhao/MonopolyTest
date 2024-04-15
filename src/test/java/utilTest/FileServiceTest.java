package utilTest;

import org.bihe.util.FileService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {
    String existingFilepath = "./src/test/java/utilTest/fileservice.txt";
    String newFilePath = "./src/test/java/utilTest/newfileservice.txt";
    String neverExistingFilePath = "./src/test/java/utilTest/newread.txt";

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
}
