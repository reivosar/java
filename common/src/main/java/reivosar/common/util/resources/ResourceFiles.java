package reivosar.common.util.resources;

import reivosar.common.util.model.Model;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class ResourceFiles extends Model {
    
    private final Collection<ResourceFile> files;
    
    ResourceFiles() {
        this.files = new HashSet<>();
    }
    
    void add(final URL url) {
        add(toFile(url));
    }
    
    private File toFile(final URL url) {
        try {
            return new File(url.toURI());
        } catch (Exception e) {
            return new File(url.getPath());
        }
    }
    
    void add(final File file) {
        this.files.add(new ResourceFile(file));
    }
    
    Collection<ResourceFile> findByName(final String fileName) {
        return files.stream()
                .filter(resourceFile -> resourceFile.matchFilename(fileName)).toList();
    }
    
    Collection<ResourceFile> findByPath(final String filePath) {
        return files.stream()
                .filter(resourceFile -> resourceFile.matchUnixFilePath(filePath)).toList();
    }
    
    Collection<ResourceFile> resourceFiles() {
        return Collections.unmodifiableCollection(files);
    }
}
