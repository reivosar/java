package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

class PdfNewCreateInvoker extends PdfCreateInvoker {
    
    PdfNewCreateInvoker(final PdfCreateParameters pdfCreateParameters) {
        super(pdfCreateParameters);
    }
    
    @Override
    PDDocument createPDDocument() {
        return new PDDocument();
    }
    
    @Override
    boolean needToAddNewPages() {
        return true;
    }
}
