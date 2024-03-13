package reivosar.common.util.io.pdf;

class PdfDocumentExporterFactory {
    
    private final PdfTemplate pdfTemplate;
    private final PdfCreateParameters pdfCreateParameters;
    
    PdfDocumentExporterFactory(final PdfTemplate pdfTemplate, final PdfCreateParameters pdfCreateParameters) {
        this.pdfTemplate = pdfTemplate;
        this.pdfCreateParameters = pdfCreateParameters;
    }
    
    PdfDocumentExporter getExporter() {
        if (pdfTemplate != null) {
            return new PDFBoxPdfTemplateEmbedExporter(pdfTemplate, pdfCreateParameters);
        }
        return new PDFBoxPdfNewDocumentExporter(pdfCreateParameters);
    }
}
