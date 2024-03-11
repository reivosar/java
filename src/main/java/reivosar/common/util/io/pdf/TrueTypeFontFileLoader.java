package reivosar.common.util.io.pdf;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import reivosar.common.util.resources.ClassPathResources;
import reivosar.common.util.resources.FileExtension;

import java.io.*;

class TrueTypeFontFileLoader extends FontLoader {
    
    private static final ClassPathResources CLASS_PATH_RESOURCES;
    static {
        CLASS_PATH_RESOURCES = new ClassPathResources(new FileExtension("ttf"));
    }
    
    private final PDFont pdFont;
    
    protected TrueTypeFontFileLoader(final String fontFilePath) {
        try (final PDDocument dummyDocument = new PDDocument();
             final InputStream inputStream = new FileInputStream(getFontFile(fontFilePath))) {
            this.pdFont = PDTrueTypeFont.load(dummyDocument, inputStream, Encoding.getInstance(COSName.STANDARD_ENCODING));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private File getFontFile(final String fontFilePath) throws FileNotFoundException {
        return CLASS_PATH_RESOURCES.findFirstMatchingFilePath(fontFilePath)
                .orElseThrow(() -> new FileNotFoundException("File not found: " + fontFilePath))
                .toFile();
    }
    
    @Override
    PDFont load() {
        return pdFont;
    }
}
