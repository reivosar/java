package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

public class PdfCreateParameter extends Model {
    
    private final int page;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final int fontSize;
    private final String align;
    private final Object text;
    
    private PdfCreateParameter(final Builder builder) {
        this.page = builder.page;
        this.x = builder.x;
        this.y = builder.y;
        this.width = builder.width;
        this.height = builder.height;
        this.fontSize = builder.fontSize;
        this.align = builder.align;
        this.text = builder.text;
    }
    
    public static class Builder {
        private int page;
        private float x;
        private float y;
        private float width;
        private float height;
        private int fontSize;
        private String align;
        private Object text;
        
        public Builder page(final int page) {
            this.page = page;
            return this;
        }
        
        public Builder x(final float x) {
            this.x = x;
            return this;
        }
        
        public Builder y(final float y) {
            this.y = y;
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
            return new PdfCreateParameter(this);
        }
    }
    
    int page() {
        return page;
    }
    
    PdfItem toPdfItem() {
        return new PdfItem(page, new PdfItemLocation(x, y), new PdfItemSize(width, height));
    }
    
    EmbedText toEmbedText() {
        return new EmbedText(new TextContent(text), new TextFont(fontSize), TextAlign.of(align));
    }
}
