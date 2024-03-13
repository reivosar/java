package reivosar.common.util.io.pdf;

import reivosar.common.util.model.Model;

public class PdfDocumentInfo extends Model {

    private final PdfMetadata pdfMetadata;
    private final PdfPages pdfPages;

    PdfDocumentInfo(final PdfMetadata pdfMetadata, final PdfPages pdfPages) {
        this.pdfMetadata = pdfMetadata;
        this.pdfPages = pdfPages;
    }

    public PdfMetadata metadata() {
        return pdfMetadata;
    }

    public PdfPages pages() {
        return pdfPages;
    }

}
