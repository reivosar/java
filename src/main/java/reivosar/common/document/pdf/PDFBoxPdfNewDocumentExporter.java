package reivosar.common.document.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

class PDFBoxPdfNewDocumentExporter extends PDFBoxPdfDocumentExporter {

    @Override
    PDDocument createPDDocument() {
        return new PDDocument();
    }
    
    @Override
    boolean needToAddNewPages() {
        return true;
    }
}
