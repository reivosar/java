package reivosar.common.util.io.pdf.creator;

record TextContent(Object value) {
    
    public String asString() {
        return (value() != null) ? value().toString() : "";
    }
    
}
