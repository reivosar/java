package reivosar.common.util.resources;

import reivosar.common.util.model.Model;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

class ResourceFilePath extends Model {
    
    private final URL url;
    
    ResourceFilePath(final URL url) {
        this.url = url;
    }
    
    File toFile() {
        try {
            return new File(url.toURI());
        } catch (Exception e) {
            return new File(url.getPath());
        }
    }
    
    Path toPath() {
        return toFile().toPath();
    }
    
    String fileName() {
        return toFile().getName();
    }
}
