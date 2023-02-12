package reivosar.common.util.io.pdf.creator;

class PdfCreateInvokerSelector {
    
    private final PdfTemplate pdfTemplate;
    private final PdfCreateParameters pdfCreateParameters;
    
    PdfCreateInvokerSelector(final PdfTemplate pdfTemplate, final PdfCreateParameters pdfCreateParameters) {
        this.pdfTemplate = pdfTemplate;
        this.pdfCreateParameters = pdfCreateParameters;
    }
    
    PdfCreateInvoker selectInvoker() {
        if (pdfTemplate != null) {
            return new PdfTemplateEmbedInvoker(pdfTemplate, pdfCreateParameters);
        }
        return new PdfNewCreateInvoker(pdfCreateParameters);
    }
}
