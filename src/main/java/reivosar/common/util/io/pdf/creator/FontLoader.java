package reivosar.common.util.io.pdf.creator;

import org.apache.pdfbox.pdmodel.font.PDFont;

abstract class FontLoader {
    abstract PDFont load();
}
