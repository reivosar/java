package reivosar.common.document.pdf;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PdfPagesTest {

    @Nested
    class AddAndRetrievePageInfoTests {

        @Test
        void shouldCorrectlyAddAndRetrievePageInfo() {
            // given
            PdfPages pages = new PdfPages();
            PdfPageInfo pageInfo = new PdfPageInfo(1, "Sample Text", null, null, 100, 100);
            pages.addPageInfo(pageInfo);

            // when
            Optional<PdfPageInfo> retrievedPageInfo = pages.at(1);

            // then
            assertTrue(retrievedPageInfo.isPresent());
            assertEquals("Sample Text", retrievedPageInfo.get().text());
        }
    }

    @Nested
    class TotalPagesTests {

        @Test
        void shouldCorrectlyReportTotalPages() {
            // given
            PdfPages pages = new PdfPages();
            pages.addPageInfo(new PdfPageInfo(1, "Page 1", null, null, 100, 100));
            pages.addPageInfo(new PdfPageInfo(2, "Page 2", null, null, 100, 100));

            // when
            int total = pages.totalPages();

            // then
            assertEquals(2, total);
        }
    }


    @Nested
    class TextSearchTests {

        @Test
        void shouldReturnTrueWhenTextIsContained() {
            // given
            PdfPages pages = new PdfPages();
            PdfPageInfo pageInfo1 = new PdfPageInfo(1, "This is a test page with some text.", null, null, 100, 100);
            PdfPageInfo pageInfo2 = new PdfPageInfo(2, "Another page that also contains the specific text.", null, null, 100, 100);
            pages.addPageInfo(pageInfo1);
            pages.addPageInfo(pageInfo2);

            // when
            boolean contains = pages.containsText("specific text");

            // then
            assertTrue(contains);
        }

        @Test
        void shouldReturnFalseWhenTextIsNotContained() {
            // given
            PdfPages pages = new PdfPages();
            PdfPageInfo pageInfo1 = new PdfPageInfo(1, "Text that does not match.", null, null, 100, 100);
            pages.addPageInfo(pageInfo1);

            // when
            boolean contains = pages.containsText("missing text");

            // then
            assertFalse(contains);
        }

        @Test
        void shouldFindPagesContainingText() {
            // given
            PdfPages pages = new PdfPages();
            PdfPageInfo pageInfo1 = new PdfPageInfo(1, "Page with some random text.", null, null, 100, 100);
            PdfPageInfo pageInfo2 = new PdfPageInfo(2, "Page containing the search text.", null, null, 100, 100);
            PdfPageInfo pageInfo3 = new PdfPageInfo(3, "Another page containing the search text.", null, null, 100, 100);
            pages.addPageInfo(pageInfo1);
            pages.addPageInfo(pageInfo2);
            pages.addPageInfo(pageInfo3);

            // when
            Collection<PdfPageInfo> foundPages = pages.findText("search text");

            // then
            assertEquals(2, foundPages.size());
            assertTrue(foundPages.contains(pageInfo2));
            assertTrue(foundPages.contains(pageInfo3));
        }

        @Test
        void shouldReturnEmptyListWhenTextIsNotFound() {
            // given
            PdfPages pages = new PdfPages();
            PdfPageInfo pageInfo1 = new PdfPageInfo(1, "Text that does not match.", null, null, 100, 100);
            pages.addPageInfo(pageInfo1);

            // when
            Collection<PdfPageInfo> foundPages = pages.findText("missing text");

            // then
            assertTrue(foundPages.isEmpty());
        }
    }
}
