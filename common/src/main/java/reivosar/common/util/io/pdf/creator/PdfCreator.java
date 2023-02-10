package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class PdfCreator {
    
    private final Map<Integer, Collection<PdfCreateParameter>> pdfCreateParameters;
    
    public PdfCreator() {
        this.pdfCreateParameters = new LinkedHashMap<>();
    }
    
    public PdfCreator addParameter(final int page,
                                   final float x,
                                   final float y,
                                   final float width,
                                   final float height,
                                   final int fontSize,
                                   final String align,
                                   final Object text) {
        return addParameter(new PdfCreateParameter.Builder()
                .page(page)
                .x(x)
                .y(y)
                .width(width)
                .height(height)
                .fontSize(fontSize)
                .align(align)
                .text(text)
                .build());
    }
    
    public PdfCreator addParameter(final PdfCreateParameter pdfCreateParameter) {
        final int page = pdfCreateParameter.page();
        final Collection<PdfCreateParameter> parameters = pdfCreateParameters.containsKey(page) ?
                pdfCreateParameters.get(page) : new LinkedList<>();
        parameters.add(pdfCreateParameter);
        this.pdfCreateParameters.put(page, parameters);
        return this;
    }
    
    public boolean createTo(final Path path) {
        try (PDDocument doc = new PDDocument()) {
            addNewPage(doc);
            embedTextInDocument(doc);
            saveDocument(path, doc);
            return path.toFile().exists();
        } catch (Exception e) {
            return false;
        }
    }
    
    private Collection<Integer> pageNumbers() {
        return pdfCreateParameters.keySet();
    }
    
    private void addNewPage(final PDDocument pagesDocument) {
        IntStream.range(0, pageNumbers().stream().max(Comparator.naturalOrder()).get() + 1).forEach(
                value -> pagesDocument.addPage(new PDPage())
        );
    }
    
    private void embedTextInDocument(final PDDocument pagesDocument) throws IOException {
        for (final int pageNumber : pageNumbers()) {
            final PDPage page = pagesDocument.getPage(pageNumber);
            if (page == null) {
                pagesDocument.addPage(new PDPage());
            }
            try (PDPageContentStream pdPageContentStream
                         = new PDPageContentStream(pagesDocument, pagesDocument.getPage(pageNumber), PDPageContentStream.AppendMode.APPEND, false)) {
                for (final PdfCreateParameter parameter : pdfCreateParameters.get(pageNumber)) {
                    final PdfCreateInvoker pdfCreateInvoker = new PdfCreateInvoker(parameter.toPdfItem(), parameter.toEmbedText());
                    pdfCreateInvoker.invoke(pdPageContentStream);
                }
            }
        }
    }
    
    private void saveDocument(final Path path, final PDDocument document) throws IOException {
        document.save(path.toFile());
    }
}
