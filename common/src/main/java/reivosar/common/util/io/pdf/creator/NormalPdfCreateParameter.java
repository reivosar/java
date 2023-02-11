package reivosar.common.util.io.pdf.creator;

public class NormalPdfCreateParameter extends PdfCreateParameter {
    
    private final int page;
    private final float xCoordination;
    private final float yCoordination;
    private final float width;
    private final float height;
    private final int fontSize;
    private final String align;
    private final Object text;
    
    private NormalPdfCreateParameter(final Builder builder) {
        this.page = builder.page;
        this.xCoordination = builder.xCoordination;
        this.yCoordination = builder.yCoordination;
        this.width = builder.width;
        this.height = builder.height;
        this.fontSize = builder.fontSize;
        this.align = builder.align;
        this.text = builder.text;
    }
    
    public static class Builder {
        private int page;
        private float xCoordination;
        private float yCoordination;
        private float width;
        private float height;
        private int fontSize;
        private String align;
        private Object text;
        
        public Builder page(final int page) {
            this.page = page;
            return this;
        }
        
        public Builder xCoordination(final float xCoordination) {
            this.xCoordination = xCoordination;
            return this;
        }
        
        public Builder yCoordination(final float yCoordination) {
            this.yCoordination = yCoordination;
            return this;
        }
        
        public Builder width(final float width) {
            this.width = width;
            return this;
        }
        
        public Builder height(final float height) {
            this.height = height;
            return this;
        }
        
        public Builder fontSize(final int fontSize) {
            this.fontSize = fontSize;
            return this;
        }
        
        public Builder align(final String align) {
            this.align = align;
            return this;
        }
        
        public Builder text(final Object text) {
            this.text = text;
            return this;
        }
        
        public PdfCreateParameter build() {
            return new NormalPdfCreateParameter(this);
        }
    }
    
    @Override
    PdfPage pdfPage() {
        return new PdfPage(page);
    }
    
    @Override
    PdfItem pdfItem() {
        return new PdfItem(new PdfItemLocation(xCoordination, yCoordination), new PdfItemSize(width, height));
    }
    
    @Override
    EmbedText embedText() {
        return new EmbedText(new TextContent(text), new TextFont(fontSize), TextAlign.of(align));
    }
}
