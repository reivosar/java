package reivosar.common.document.pdf;

import java.nio.file.Path;

abstract class PdfDocumentExporter {

    abstract boolean export(final Path path, final PdfTemplate pdfTemplate, final PdfCreateParameters pdfCreateParameters);
}
