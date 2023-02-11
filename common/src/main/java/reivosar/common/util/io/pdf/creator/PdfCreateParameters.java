package reivosar.common.util.io.pdf.creator;

import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class PdfCreateParameters extends Model {
    
    private final Map<PdfPage, Collection<PdfCreateParameter>> pageCollectionMap;
    
    public PdfCreateParameters() {
        this.pageCollectionMap = new LinkedHashMap<>();
    }
    
    Collection<Integer> pageNumbers() {
        return pageCollectionMap.keySet()
                .stream().map(pdfPage -> pdfPage.pageNumber())
                .collect(Collectors.toList());
    }
    
    Collection<PdfCreateParameter> getParameters(final PdfPage pdfPage) {
        return pageCollectionMap.get(pdfPage);
    }
    
    public PdfCreateParameters addParameter(final PdfCreateParameter pdfCreateParameter) {
        final PdfPage pdfPage = pdfCreateParameter.pdfPage();
        final Collection<PdfCreateParameter> parameters = pageCollectionMap.containsKey(pdfPage) ?
                pageCollectionMap.get(pdfPage) : new LinkedList<>();
        parameters.add(pdfCreateParameter);
        this.pageCollectionMap.put(pdfPage, parameters);
        return this;
    }
}
