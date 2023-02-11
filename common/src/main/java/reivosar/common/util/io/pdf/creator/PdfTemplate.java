package reivosar.common.util.io.pdf.creator;

import java.nio.file.Path;
import java.nio.file.Paths;

public record PdfTemplate(String templateName) {
    
    private static final String BASE_DIR_PATH = "/templates/pdf/";
    
    public Path pdfTemplateFilePath() {
        return Paths.get(this.getClass()
                .getResource(BASE_DIR_PATH + "template" + "/" + pdfTemplateFileName())
                .getPath());
    }
    
    public String pdfTemplateFileName() {
        return templateName() + ".pdf";
    }
    
    public Path itemDefinitionFilePath() {
        return Paths.get(this.getClass()
                .getResource(BASE_DIR_PATH + "template" + "/" + itemDefinitionFileName())
                .getPath());
    }
    
    public String itemDefinitionFileName() {
        return templateName() + ".json";
    }
}
