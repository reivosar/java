package reivosar.common.util.resources;

import reivosar.common.util.model.Model;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

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
    
    Collection<ResourceFile> filterByFileName(final String fileName) {
        return filter(resourceFile -> resourceFile.matchFilename(fileName));
    }
    
    Collection<ResourceFile> filterByFilePath(final String filePath) {
        return filter(resourceFile -> resourceFile.matchUnixFilePath(filePath));
    }
    
    private List<ResourceFile> filter(Predicate<ResourceFile> predicate) {
        return files.stream()
                .filter(predicate)
                .filter(this::isValidFile)
                .sorted(sortFilePath())
                .toList();
    }
    
    private boolean isValidFile(final ResourceFile resourceFile) {
        final File file = resourceFile.toFile();
        return file.exists() && file.isFile() && file.canRead();
    }
    
    private Comparator<ResourceFile> sortFilePath() {
        return Comparator.comparing(resourceFile -> resourceFile.toFile().getAbsolutePath());
    }
    
    Collection<ResourceFile> resourceFiles() {
        return Collections.unmodifiableCollection(files);
    }
}
