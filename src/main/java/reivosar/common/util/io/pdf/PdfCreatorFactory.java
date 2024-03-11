package reivosar.common.util.io.pdf;

class PdfCreatorFactory {

    static PdfCreator gatDefault(PdfTemplate pdfTemplate) {
        return new DefaultPdfCreator(pdfTemplate);
    }

}
