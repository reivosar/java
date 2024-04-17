package reivosar.common.document.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

abstract class FontLoader {
    abstract PDFont load();
}
