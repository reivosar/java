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

    public DefaultPdfCreator append(final PdfCreateParameter pdfCreateParameter) {
        ObjectUtil.requireNonNull("pdfCreateParameter", pdfCreateParameter);
        this.pdfCreateParameters.add(pdfCreateParameter);
        return this;
    }

    public boolean createTo(final Path path) {
        ObjectUtil.requireNonNull("path", path);
        final PdfCreateInvokerSelector selector = new PdfCreateInvokerSelector(pdfTemplate, pdfCreateParameters);
        final PdfCreateInvoker invoker = selector.selectInvoker();
        return invoker.invoke(path);
    }
}
