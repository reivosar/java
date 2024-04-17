package reivosar.common.document.pdf;

import reivosar.common.lang.ObjectUtil;

import java.nio.file.Path;

abstract class PdfCreatorTemplate implements PdfCreator {

    private final PdfTemplate pdfTemplate;
    private final PdfCreateParameters pdfCreateParameters;

    PdfCreatorTemplate(final PdfTemplate pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
        this.pdfCreateParameters = new PdfCreateParameters();
    }

    public final PdfCreatorTemplate addContent(final PdfCreateParameter pdfCreateParameter) {
        ObjectUtil.requireNonNull("pdfCreateParameter", pdfCreateParameter);
        this.pdfCreateParameters.add(pdfCreateParameter);
        return this;
    }

    public final boolean saveAs(final Path path) {
        ObjectUtil.requireNonNull("path", path);
        final PdfDocumentExporter exporter = getExporter(pdfTemplate, pdfCreateParameters);
        return exporter.export(path, pdfTemplate, pdfCreateParameters);
    }

    protected abstract PdfDocumentExporter getExporter(final PdfTemplate pdfTemplate,
                                                       final PdfCreateParameters pdfCreateParameters);
}
