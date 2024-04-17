package reivosar.common.document.pdf;

final class PDFBoxPdfCreator extends PdfCreatorTemplate {

    PDFBoxPdfCreator(final PdfTemplate pdfTemplate) {
        super(pdfTemplate);
    }

    @Override
    protected PdfDocumentExporter getExporter(
            final PdfTemplate pdfTemplate,
            final PdfCreateParameters pdfCreateParameters) {
        if (pdfTemplate != null) {
            return new PDFBoxPdfTemplateEmbedExporter(pdfTemplate);
        }
        return new PDFBoxPdfNewDocumentExporter();
    }
}
