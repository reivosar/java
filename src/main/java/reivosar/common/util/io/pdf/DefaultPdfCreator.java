package reivosar.common.util.io.pdf;

import reivosar.common.util.lang.ObjectUtil;

import java.nio.file.Path;

final class DefaultPdfCreator implements PdfCreator {

    private final PdfTemplate pdfTemplate;
    private final PdfCreateParameters pdfCreateParameters;

    DefaultPdfCreator(final PdfTemplate pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
        this.pdfCreateParameters = new PdfCreateParameters();
    }

    public DefaultPdfCreator addContent(final PdfCreateParameter pdfCreateParameter) {
        ObjectUtil.requireNonNull("pdfCreateParameter", pdfCreateParameter);
        this.pdfCreateParameters.add(pdfCreateParameter);
        return this;
    }

    public boolean saveAs(final Path path) {
        ObjectUtil.requireNonNull("path", path);
        final PdfDocumentExporterFactory factory = new PdfDocumentExporterFactory(pdfTemplate, pdfCreateParameters);
        final PdfDocumentExporter exporter = factory.getExporter();
        return exporter.export(path);
    }
}
