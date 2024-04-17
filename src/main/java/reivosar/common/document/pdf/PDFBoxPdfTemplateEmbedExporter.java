package reivosar.common.document.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

class PDFBoxPdfTemplateEmbedExporter extends PDFBoxPdfDocumentExporter {

    private final PdfTemplate pdfTemplate;

    PDFBoxPdfTemplateEmbedExporter(final PdfTemplate pdfTemplate) {
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
