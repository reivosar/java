package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

class PdfTemplateEmbedInvoker extends PdfCreateInvoker {
    
    private final PdfTemplate pdfTemplate;
    
    PdfTemplateEmbedInvoker(final PdfTemplate pdfTemplate, final PdfCreateParameters pdfCreateParameters) {
        super(pdfCreateParameters);
        this.pdfTemplate = pdfTemplate;
    }
    
    @Override
    PDDocument createPDDocument() throws Exception {
        final File pdfTemplateFile = pdfTemplate.pdfTemplateFilePath().toFile();
        return new PDDocument();
    }
    
    @Override
    boolean needToAddNewPages() {
        return false;
    }
}
