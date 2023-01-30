package reivosar.common.util.resources;

import reivosar.common.util.model.Model;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class ResourceFiles extends Model {
    
    private final Collection<ResourceFile> resourceFileSet;
    
    ResourceFiles() {
        this.resourceFileSet = new HashSet<>();
    }
    
    void add(final URL url) {
        this.resourceFileSet.add(new ResourceFile(new ResourceFilePath(url)));
    }
    
    Collection<ResourceFile> resourceFiles() {
        return Collections.unmodifiableCollection(resourceFileSet);
    }
}
