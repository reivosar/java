package reivosar.common.util.io.pdf.creator;

abstract class PdfCreateParameter {
    
    abstract PdfPage pdfPage();
    
    abstract PdfItem pdfItem();
    
    abstract EmbedText embedText();
}
