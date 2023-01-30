package reivosar.common.util.resources;

import reivosar.common.util.model.Model;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

class ResourceFile extends Model {
    
    private final ResourceFilePath path;
    
    ResourceFile(final ResourceFilePath path) {
        this.path = Objects.requireNonNull(path, "resourcePath must not be null");
    }
    
    File toFile() {
        return path.toFile();
    }
    
    Path toPath() {
        return path.toPath();
    }
    
    String fileName() {
        return path.fileName();
    }
}
