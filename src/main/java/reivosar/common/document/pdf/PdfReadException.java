package reivosar.common.document.pdf;

public class PdfReadException extends RuntimeException {
    public PdfReadException(Exception e) {
        super(e);
    }
}
