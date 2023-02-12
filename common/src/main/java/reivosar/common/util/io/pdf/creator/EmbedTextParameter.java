package reivosar.common.util.io.pdf.creator;

public class EmbedTextParameter extends PdfCreateParameter {
    
    private final int page;
    private final float xCoordination;
    private final float yCoordination;
    private final float width;
    private final float height;
    private final String fontName;
    private final int fontSize;
    private final String align;
    private final Object text;
    
    private EmbedTextParameter(final Builder builder) {
        this.page = builder.page;
        this.xCoordination = builder.xCoordination;
        this.yCoordination = builder.yCoordination;
        this.width = builder.width;
        this.height = builder.height;
        this.fontName = builder.fontName;
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
        private String fontName;
        private int fontSize;
        private String align;
        private Object text;
        
        public Builder page(final int page) {
            this.page = page;
            return this;
        }
        
        public Builder coordination(final float xCoordination, final float yCoordination) {
            this.xCoordination = xCoordination;
            this.yCoordination = yCoordination;
            return this;
        }
        
        public Builder areaSize(final float width, final float height) {
            this.width = width;
            this.height = height;
            return this;
        }
    
        public Builder font(final String fontName, final int fontSize) {
            this.fontName = fontName;
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
            return new EmbedTextParameter(this);
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
        return new EmbedText(new TextContent(text), new TextFont(fontName, fontSize), TextAlign.of(align));
    }
}
