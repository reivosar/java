package reivosar.common.util.io.pdf.creator;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PdfTemplateTest {
    
    @Test
    void pdfTemplateFilePath_returnsValidFilePath() {
        PdfTemplate pdfTemplate = new PdfTemplate("test");
        assertDoesNotThrow(() -> {
            Path path = pdfTemplate.pdfTemplateFilePath();
            assertNotNull(path);
        });
    }
    
    @Test
    void pdfTemplateFilePath_throwsFileNotFoundException() {
        PdfTemplate pdfTemplate = new PdfTemplate("invalid-template-name");
        assertThrows(FileNotFoundException.class, pdfTemplate::pdfTemplateFilePath);
    }
    
    @Test
    void itemDefinitionFilePath_returnsValidFilePath() {
        PdfTemplate pdfTemplate = new PdfTemplate("test");
        assertDoesNotThrow(() -> {
            Path path = pdfTemplate.itemDefinitionFilePath();
            assertNotNull(path);
        });
    }
    
    @Test
    void itemDefinitionFilePath_throwsFileNotFoundException() {
        PdfTemplate pdfTemplate = new PdfTemplate("invalid-template-name");
        assertThrows(FileNotFoundException.class, pdfTemplate::itemDefinitionFilePath);
    }
}