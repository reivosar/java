package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

public abstract class PdfCreateParameter extends Model {
    
    abstract PdfPage pdfPage();
    
    abstract PdfItem pdfItem();
    
    abstract EmbedText embedText();
}
