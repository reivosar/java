package reivosar.common.document.pdf;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PdfReaderTest {

    @Nested
    class OpenNonProtectedPdfTests {

        @Test
        void shouldOpenNonProtectedPdfSuccessfully() {
            // given
            Path pdfPath = Paths.get("src/test/resources/test.pdf");

            // when
            PdfReader reader = PdfReader.open(pdfPath);
            PdfDocumentInfo documentInfo = reader.read();

            // then
            assertNotNull(documentInfo);
            assertTrue(documentInfo.pages().totalPages() > 0);

            PdfMetadata pdfMetadata = documentInfo.metadata();
            assertEquals("PdfReaderTests", pdfMetadata.title().get());
            assertEquals("reivosar", pdfMetadata.author().get());
            assertEquals("shouldOpenNonProtectedPdfSuccessfully", pdfMetadata.subject().get());
            assertEquals(List.of("key1", "key2", "key3", "key4", "key5"), pdfMetadata.keywords());
            assertEquals(LocalDateTime.of(2024, 3, 14, 21, 0), pdfMetadata.creationDate().get());
            assertEquals(LocalDateTime.of(2024, 3, 15, 21, 0), pdfMetadata.modDate().get());

            assertEquals(3, documentInfo.totalPages());

            documentInfo.at(1).ifPresent(pdfPageInfo -> {
                assertEquals(1, pdfPageInfo.pageNumber());
                assertEquals(1384, pdfPageInfo.trimedText().length());
                assertTrue(pdfPageInfo.images().isEmpty());
                assertEquals(595.3200073242188, pdfPageInfo.width());
                assertEquals(841.9199829101562, pdfPageInfo.height());
            });
            documentInfo.at(2).ifPresent(pdfPageInfo -> {
                assertEquals(2, pdfPageInfo.pageNumber());
                assertEquals(0, pdfPageInfo.trimedText().length());
                assertEquals(172800, pdfPageInfo.images().stream().findFirst().get().length);
                assertEquals(595.3200073242188, pdfPageInfo.width());
                assertEquals(841.9199829101562, pdfPageInfo.height());
            });
            documentInfo.at(3).ifPresent(pdfPageInfo -> {
                assertEquals(3, pdfPageInfo.pageNumber());
                assertEquals(0, pdfPageInfo.trimedText().length());
                assertEquals(750000, pdfPageInfo.images().stream().findFirst().get().length);
                assertEquals(595.3200073242188, pdfPageInfo.width());
                assertEquals(841.9199829101562, pdfPageInfo.height());
            });
        }

        @Test
        void shouldThrowNullPointerExceptionForNullPath() {
            // given
            Path nullPath = null;

            // when & then
            assertThrows(NullPointerException.class, () -> PdfReader.open(nullPath));
        }
    }

    @Nested
    class OpenPasswordProtectedPdfTests {

        @Test
        void shouldOpenPasswordProtectedPdfSuccessfully() {
            // given
            Path pdfPath = Paths.get("src/test/resources/protected.pdf");
            PdfPassword password = new PdfPassword("OpenPasswordProtectedPdfTests");

            // when
            PdfReader reader = PdfReader.open(pdfPath, password);
            PdfDocumentInfo documentInfo = reader.read();

            // then
            assertNotNull(documentInfo);
            assertTrue(documentInfo.pages().totalPages() > 0);

            PdfMetadata pdfMetadata = documentInfo.metadata();
            assertEquals(Optional.empty(), pdfMetadata.title());
            assertTrue(pdfMetadata.author().isPresent());
            assertFalse(pdfMetadata.subject().isPresent());
            assertTrue(pdfMetadata.keywords().isEmpty());
            assertEquals(LocalDateTime.of(2024, 3, 15, 8, 22, 38), pdfMetadata.creationDate().get());
            assertEquals(LocalDateTime.of(2024, 3, 15, 8, 22, 38), pdfMetadata.modDate().get());

            assertEquals(1, documentInfo.totalPages());

            documentInfo.at(1).ifPresent(pdfPageInfo -> {
                assertEquals(1, pdfPageInfo.pageNumber());
                assertEquals(42, pdfPageInfo.trimedText().length());
                assertTrue(pdfPageInfo.images().isEmpty());
                assertEquals(595.3200073242188, pdfPageInfo.width());
                assertEquals(841.9199829101562, pdfPageInfo.height());
            });
        }

        @Test
        void shouldThrownPdfReadExceptionForUncorrectedPassword() {
            // given
            Path pdfPath = Paths.get("src/test/resources/protected.pdf");
            PdfPassword password = new PdfPassword("uncorrected_password");

            // when & then
            assertThrows(PdfReadException.class, () -> PdfReader.open(pdfPath, password).read());
        }

        @Test
        void shouldThrowNullPointerExceptionForNullPassword() {
            // given
            Path pdfPath = Paths.get("src/test/resources/protected.pdf");

            // when & then
            assertThrows(NullPointerException.class, () -> PdfReader.open(pdfPath, null));
        }
    }
}
