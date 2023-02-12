package reivosar.common.util.io.pdf.creator;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PdfCreatorTest {
    
    @Test
    void test() {
        // GIVEN
        final Path outputPath = Paths.get("C:\\Users\\Windows\\Downloads\\dest\\" + System.currentTimeMillis() + ".pdf");
        // WHEN
        final boolean result = PdfCreator.forCreatingNew()
                .addText(0, 0f, 755f, 200, 100, "japanese",12, "center", "1")
                .addText(1, 0f, 755f, 200, 100, "japanese",12, "center", "2")
                .createTo(outputPath);
        // THEN
        assertTrue(result);
    }
}