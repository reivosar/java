package reivosar.common.util.io.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

abstract class FontLoader {
    abstract PDFont load();
}
