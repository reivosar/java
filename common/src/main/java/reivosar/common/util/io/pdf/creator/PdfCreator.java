package reivosar.common.util.io.pdf.creator;

import java.nio.file.Path;

public final class PdfCreator {
    
    private final PdfTemplate pdfTemplate;
    private final PdfCreateParameters pdfCreateParameters;
    
    public static PdfCreator createNew() {
        return new PdfCreator(null);
    }
    
    public static PdfCreator embed(final PdfTemplate pdfTemplate) {
        return new PdfCreator(pdfTemplate);
    }
    
    private PdfCreator(final PdfTemplate pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
        this.pdfCreateParameters = new PdfCreateParameters();
    }
    
    public PdfCreator append(final int page, final String propertyName, final Object text) {
        return this;
    }
    
    public PdfCreator append(final int page,
                             final float x,
                             final float y,
                             final float width,
                             final float height,
                             final int fontSize,
                             final String align,
                             final Object text) {
        return append(new NormalPdfCreateParameter.Builder()
                    .page(page)
                    .xCoordination(x)
                    .yCoordination(y)
                    .width(width)
                    .height(height)
                    .fontSize(fontSize)
                    .align(align)
                    .text(text)
                .build());
    }
    
    public PdfCreator append(final PdfCreateParameter pdfCreateParameter) {
        this.pdfCreateParameters.add(pdfCreateParameter);
        return this;
    }
    
    boolean createTo(final Path path) {
        final PdfCreateInvokerSelector selector = new PdfCreateInvokerSelector(pdfTemplate, pdfCreateParameters);
        final PdfCreateInvoker invoker = selector.select();
        return invoker.invoke(path);
    }
}
