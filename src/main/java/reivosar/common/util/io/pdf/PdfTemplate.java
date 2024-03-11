package reivosar.common.util.io.pdf;

import reivosar.common.util.resources.ClassPathResources;
import reivosar.common.util.resources.FileExtension;
import reivosar.common.util.resources.ResourceFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * Represents a PDF template, encapsulating the logic to locate the PDF template file and its associated item definition file.
 * This record is designed to work with resources located in the classpath, specifically within a predefined base directory path.
 *
 * @param templateName The name of the template, used to construct filenames for the PDF template and its item definition.
 */
public record PdfTemplate(String templateName) {
    
    private static final ClassPathResources PDF_TEMPLATE;
    private static final ClassPathResources PDF_DEFINITION;
    
    static {
        PDF_TEMPLATE = new ClassPathResources(new FileExtension("pdf"));
        PDF_DEFINITION = new ClassPathResources(new FileExtension("json"));
    }
    
    private static final String BASE_DIR_PATH = "/templates/pdf";
    
    Path pdfTemplateFilePath() throws FileNotFoundException {
        return getResourceFilePath(PDF_TEMPLATE, "/template/", pdfTemplateFileName()).toPath();
    }
    
    String pdfTemplateFileName() {
        return templateName() + ".pdf";
    }
    
    Path itemDefinitionFilePath() throws FileNotFoundException {
        return getResourceFilePath(PDF_DEFINITION, "/definition/", itemDefinitionFileName()).toPath();
    }
    
    String itemDefinitionFileName() {
        return templateName() + ".json";
    }
    
    private File getResourceFilePath(
            final ClassPathResources resources,
            final String basedir,
            final String fileName) throws FileNotFoundException {
        final ResourceFile resourceFile = resources
                .findFirstMatchingFilePath(BASE_DIR_PATH + basedir + fileName)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + BASE_DIR_PATH + basedir + pdfTemplateFileName()));
        return resourceFile.toFile();
    }
}
