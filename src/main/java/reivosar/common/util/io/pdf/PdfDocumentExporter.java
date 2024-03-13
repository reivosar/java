package reivosar.common.util.io.pdf;

import java.nio.file.Path;

abstract class PdfDocumentExporter {

    abstract boolean export(final Path path);
}
