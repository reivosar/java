package reivosar.common.util.io.pdf.creator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PdfCreatorTest {
    
    private static final String THIS_TESTCASE_WORK_DIR = "\\src\\test\\resources\\templates\\pdf\\dest";
    private static final Path OUTPUT_PDF_DIR = Paths.get(System.getProperty("user.dir"), THIS_TESTCASE_WORK_DIR);
    
    @BeforeEach
    void setup() throws IOException {
        deleteDirectory();
        Files.createDirectories(OUTPUT_PDF_DIR);
    }
    
    @Test
    void shouldBeCreatedNewPDF() {
        // GIVEN
        final Path outputPdfFilePath = OUTPUT_PDF_DIR.resolve("test.pdf");
        // WHEN
        final boolean result = PdfCreator.instance()
                .append(new EmbedTextParameter.Builder()
                        .at(0)
                        .coordination(0f, 755f)
                        .areaSize(200, 100)
                        .font("japanese", 12)
                        .align("center")
                        .text(1234567890)
                        .build())
                .createTo(outputPdfFilePath);
        // THEN
        assertTrue(result);
        assertTrue(Files.exists(outputPdfFilePath));
    }
    
    @AfterEach
    void deleteDirectory() {
        try (final Stream<Path> list = Files.list(OUTPUT_PDF_DIR)) {
            list.forEach(path -> path.toFile().deleteOnExit());
            Files.deleteIfExists(OUTPUT_PDF_DIR);
        } catch (IOException ignored) {
            // Do nothing
        }
    }
}