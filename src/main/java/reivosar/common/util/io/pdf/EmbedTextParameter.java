package reivosar.common.util.io.pdf;

/**
 * Represents a set of parameters for embedding text in a PDF document.
 * <p>
 * This class extends the {@link PdfCreateParameter} class.
 */
public class EmbedTextParameter implements PdfCreateParameter {
    
    private final int page;
    private final float xCoordination;
    private final float yCoordination;
    private final float width;
    private final float height;
    private final String fontName;
    private final int fontSize;
    private final String align;
    private final Object text;
    
    /**
     * Constructs an instance of the {@link EmbedTextParameter} class using a builder pattern.
     *
     * @param builder the builder object used to construct this instance.
     */
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
    
    /**
     * A nested class that provides a builder pattern for constructing instances of the {@link EmbedTextParameter} class.
     */
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
        
        /**
         * Sets the page number.
         *
         * @param page the page number.
         * @return the builder object.
         */
        public Builder at(final int page) {
            this.page = page;
            return this;
        }
        
        /**
         * Sets the x and y coordinates.
         *
         * @param xCoordination the x coordinate.
         * @param yCoordination the y coordinate.
         * @return the builder object.
         */
        public Builder coordination(final float xCoordination, final float yCoordination) {
            this.xCoordination = xCoordination;
            this.yCoordination = yCoordination;
            return this;
        }
        
        /**
         * Sets the width and height of the text area.
         *
         * @param width  the width of the text area.
         * @param height the height of the text area.
         * @return the builder object.
         */
        public Builder areaSize(final float width, final float height) {
            this.width = width;
            this.height = height;
            return this;
        }
        
        /**
         * Sets the font name and font size.
         *
         * @param fontName the font name.
         * @param fontSize the font size.
         * @return the builder object.
         */
        public Builder font(final String fontName, final int fontSize) {
            this.fontName = fontName;
            this.fontSize = fontSize;
            return this;
        }
        
        /**
         * Sets the text alignment.
         *
         * @param align the text alignment.
         * @return the builder object.
         */
        public Builder align(final String align) {
            this.align = align;
            return this;
        }
        
        /**
         * Sets the text to be embedded.
         *
         * @param text the text to be embedded.
         * @return the builder object.
         */
        public Builder text(final Object text) {
            this.text = text;
            return this;
        }
        
        /**
         * Constructs and returns an instance of the {@link EmbedTextParameter}
         * class using the parameters set in the builder object.
         *
         * @return an instance of the {@link EmbedTextParameter} class.
         */
        public PdfCreateParameter build() {
            return new EmbedTextParameter(this);
        }
    }
    
    @Override
    public PdfPage pdfPage() {
        return new PdfPage(page);
    }
    
    @Override
    public PdfItem pdfItem() {
        return new PdfItem(new PdfItemLocation(xCoordination, yCoordination), new PdfItemSize(width, height));
    }
    
    @Override
    public EmbedText embedText() {
        return new EmbedText(new TextContent(text), new TextFont(fontName, fontSize), TextAlign.of(align));
    }
}
