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
        final boolean result = PdfCreator.createNew()
                .append(0, 0f, 755f, 200, 100, 12, "center", "123213122312")
                .createTo(outputPath);
        // THEN
        assertTrue(result);
    }
}