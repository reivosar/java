package reivosar.common.util.io.pdf.creator;

import java.util.Objects;

record TextContent(Object value) {
    
    public String asString() {
        return (value() != null) ? value().toString() : "";
    }
    
}
