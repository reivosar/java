package reivosar.common.document.pdf;

class PdfCreatorFactory {

    static PdfCreator gatDefault(PdfTemplate pdfTemplate) {
        return new PDFBoxPdfCreator(pdfTemplate);
    }

}
