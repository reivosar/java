package reivosar.common.util.io.pdf.creator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PdfCreatorTest {
    
    private static final Path OUTPUT_PDF_PATH = Paths.get( System.getProperty("user.dir"),
                                                    "src\\test\\resources\\templates\\pdf\\dest",
                                                           "test.pdf");
    @Test
    void shouldBeCreatedNewPDF() {
        // GIVEN
        // WHEN
        final boolean result = PdfCreator.forCreatingNew()
                .append(new EmbedTextParameter.Builder()
                        .page(0)
                        .coordination(0f, 755f)
                        .areaSize(200, 100)
                        .font("japanese",12)
                        .align("center")
                        .text(1234567890)
                        .build())
                .createTo(OUTPUT_PDF_PATH);
        // THEN
        assertTrue(result);
    }
    
    @AfterEach
    void removeFile() throws IOException {
        Files.deleteIfExists(OUTPUT_PDF_PATH);
    }
    
}