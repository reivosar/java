package reivosar.common.util.resources;

import reivosar.common.util.lang.ObjectUtil;

import java.util.Collection;
import java.util.Optional;

/**
 * Utility class for accessing resources on the classpath.
 */
public class ClassPathResources {
    
    /**
     * The underlying collection of {@link ResourceFile}s to filter and search.
     */
    private final ResourceFiles resourceFiles;
    
    /**
     * Constructs a new {@code ClassPathResources} instance.
     *
     * @param fileExtension the file extension to filter the resources by
     */
    public ClassPathResources(final FileExtension fileExtension) {
        ObjectUtil.requireNonNull("fileExtension", fileExtension);
        this.resourceFiles = ClassPathResourcesScanner.scanBy(fileExtension);
    }
    
    /**
     * Filters the underlying collection of {@link ResourceFile}s by filename.
     *
     * @param fileName the name of the file to filter by
     * @return a collection of {@link ResourceFile}s that match the given filename
     */
    public Collection<ResourceFile> filterByFilename(final String fileName) {
        ObjectUtil.requireNonNull("fileName", fileName);
        return resourceFiles.filterByFileName(fileName);
    }
    
    /**
     * Filters the underlying collection of {@link ResourceFile}s by file path.
     *
     * @param filePath the path of the file to filter by, in Unix format
     * @return a collection of {@link ResourceFile}s that match the given file path
     */
    public Collection<ResourceFile> filterByFilePath(final String filePath) {
        ObjectUtil.requireNonNull("filePath", filePath);
        return resourceFiles.filterByFilePath(filePath.startsWith("/") ? filePath : "/" + filePath);
    }
    
    /**
     * Finds the first {@link ResourceFile} in the underlying collection that matches the given file path.
     *
     * @param filePath the path of the file to search for, in Unix format
     * @return an {@link Optional} containing the first {@link ResourceFile} that matches the given file path, or an empty {@link Optional} if no match is found
     */
    public Optional<ResourceFile> findFirstMatchingFilePath(final String filePath) {
        ObjectUtil.requireNonNull("filePath", filePath);
        return filterByFilePath(filePath).stream().findFirst();
    }
    
    /**
     * Returns the entire collection of {@link ResourceFile}s.
     *
     * @return a collection of all the {@link ResourceFile}s
     */
    public Collection<ResourceFile> resourceFiles() {
        return resourceFiles.resourceFiles();
    }
}