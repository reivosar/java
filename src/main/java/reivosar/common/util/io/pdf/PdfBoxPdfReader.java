package reivosar.common.util.io.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

final class PdfBoxPdfReader implements PdfReader {

    private final File file;
    private final String password;

    PdfBoxPdfReader(final Path path, final PdfPassword pdfPassword) {
        this.file = path.toFile();
        this.password = pdfPassword.value;
    }

    @Override
    public PdfDocumentInfo read() {
        try (final PDDocument document = Loader.loadPDF(file, password)) {
            return new PdfDocumentInfo(getPdfMetadata(document), getPdfPages(document));
        } catch (Exception e) {
            throw new PdfReadException(e);
        }
    }

    private PdfMetadata getPdfMetadata(final PDDocument document) {
        final PdfMetadata pdfMetadata = new PdfMetadata();

        final PDDocumentInformation info = document.getDocumentInformation();

        pdfMetadata.addMetadata(PdfMetadata.TITLE, info.getTitle());
        pdfMetadata.addMetadata(PdfMetadata.AUTHOR, info.getAuthor());
        pdfMetadata.addMetadata(PdfMetadata.SUBJECT, info.getSubject());
        pdfMetadata.addMetadata(PdfMetadata.KEYWORDS, info.getKeywords());
        pdfMetadata.addMetadata(PdfMetadata.CREATION_DATE,
                info.getCreationDate() != null ? info.getCreationDate().getTime().toString() : null);
        pdfMetadata.addMetadata(PdfMetadata.MOD_DATE,
                info.getModificationDate() != null ? info.getModificationDate().getTime().toString() : null);

        return pdfMetadata;
    }

    private PdfPages getPdfPages(final PDDocument document) throws IOException {
        final PdfPages pdfPages = new PdfPages();

        final PDFTextStripper textStripper = new PDFTextStripper();
        for (int page = 1; page <= document.getNumberOfPages(); ++page) {

            final PDPage pdPage = document.getPage(page - 1);

            textStripper.setStartPage(page);
            textStripper.setEndPage(page);
            final String pageText = textStripper.getText(document);

            final PDResources resources = pdPage.getResources();

            final Collection<byte[]> images = new ArrayList<>();
            for (final COSName xObjectName : resources.getXObjectNames()) {
                if (resources.isImageXObject(xObjectName)) {
                    final PDImageXObject image = (PDImageXObject) resources.getXObject(xObjectName);
                    images.add(image.getStream().toByteArray());
                }
            }

            final Collection<String> fontNames = new HashSet<>();
            for (final COSName fontName : resources.getFontNames()) {
                final PDFont font = resources.getFont(fontName);
                fontNames.add(font.getName());
            }

            final PDRectangle pageSize = pdPage.getMediaBox();
            final float width = pageSize.getWidth();
            final float height = pageSize.getHeight();

            pdfPages.addPageInfo(new PdfPageInfo(page, pageText, images, fontNames, width, height));
        }
        return pdfPages;
    }
}
