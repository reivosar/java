package reivosar.common.document.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.IntStream;

abstract class PDFBoxPdfDocumentExporter extends PdfDocumentExporter {

    final boolean export(final Path path, final PdfTemplate pdfTemplate, final PdfCreateParameters pdfCreateParameters) {
        try (final PDDocument doc = createPDDocument()) {
            init(doc, pdfCreateParameters);
            embedTextInDocument(doc, pdfCreateParameters);
            saveDocument(path, doc);
            return path.toFile().exists();
        } catch (Exception e) {
            return false;
        }
    }

    abstract PDDocument createPDDocument() throws Exception;

    abstract boolean needToAddNewPages();

    private void init(final PDDocument pagesDocument, final PdfCreateParameters pdfCreateParameters) {
        if (needToAddNewPages()) {
            IntStream.range(0, pdfCreateParameters.pageNumbers().stream()
                            .max(Comparator.naturalOrder()).orElse(0) + 1)
                    .forEach(value -> pagesDocument.addPage(new PDPage()));
        }
    }

    private void embedTextInDocument(final PDDocument pagesDocument, final PdfCreateParameters pdfCreateParameters) throws IOException {
        for (final int pageNumber : pdfCreateParameters.pageNumbers()) {
            try (final PDPageContentStream stream = PDPageContentStream(pagesDocument, pageNumber)) {
                embedTextForNumberOfParameters(pageNumber, stream, pdfCreateParameters);
            }
        }
    }

    private void embedTextForNumberOfParameters(
            final int pageNumber,
            final PDPageContentStream stream,
            final PdfCreateParameters pdfCreateParameters) throws IOException {
        // Loop for number of parameters and add text to PDF
        for (final PdfCreateParameter parameter : pdfCreateParameters.get(new PdfPage(pageNumber))) {
            final PdfItem pdfItem = parameter.pdfItem();
            // The specified font size may not fit in the area depending on the number of characters,
            // so it is recalculated here.
            final EmbedText embedText = parameter.embedText().rebuildWithFontSizeCalculation(pdfItem);
            try {
                stream.beginText();
                stream.setFont(embedText.pdFont(), embedText.fontSize());
                final EmbedTextLocationCalculator calculator = new EmbedTextLocationCalculator(pdfItem, embedText);
                stream.newLineAtOffset(calculator.calcXPosition(), calculator.calcYPosition());
                stream.showText(embedText.textContent().asString());
            } finally {
                stream.endText();
            }
        }
    }

    private PDPageContentStream PDPageContentStream(
            final PDDocument pagesDocument,
            final int pageNumber) throws IOException {
        return new PDPageContentStream(
                pagesDocument,
                pagesDocument.getPage(pageNumber),
                PDPageContentStream.AppendMode.APPEND,
                false);
    }

    private void saveDocument(final Path path, final PDDocument document) throws IOException {
        document.save(path.toFile());
    }
}
