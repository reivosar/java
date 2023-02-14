package reivosar.common.util.resources;

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
        this.resourceFiles = ClassPathResourcesScanner.scanBy(fileExtension);
    }
    
    /**
     * Filters the underlying collection of {@link ResourceFile}s by filename.
     *
     * @param fileName the name of the file to filter by
     * @return a collection of {@link ResourceFile}s that match the given filename
     */
    public Collection<ResourceFile> filterByFilename(final String fileName) {
        return resourceFiles.resourceFiles()
                .stream()
                .filter(resourceFile -> resourceFile.matchFilename(fileName)).toList();
    }
    
    /**
     * Finds the first {@link ResourceFile} in the underlying collection that matches the given filename.
     *
     * @param fileName the name of the file to search for
     * @return an {@link Optional} containing the first {@link ResourceFile} that matches the given filename, or an empty {@link Optional} if no match is found
     */
    public Optional<ResourceFile> findFirstMatchingFileName(final String fileName) {
        return filterByFilename(fileName).stream().findFirst();
    }
    
    /**
     * Filters the underlying collection of {@link ResourceFile}s by file path.
     *
     * @param filePath the path of the file to filter by, in Unix format
     * @return a collection of {@link ResourceFile}s that match the given file path
     */
    public Collection<ResourceFile> filterByFilePath(final String filePath) {
        return resourceFiles.resourceFiles()
                .stream()
                .filter(resourceFile -> resourceFile.matchUnixFilePath(filePath)).toList();
    }
    
    /**
     * Finds the first {@link ResourceFile} in the underlying collection that matches the given file path.
     *
     * @param fileName the path of the file to search for, in Unix format
     * @return an {@link Optional} containing the first {@link ResourceFile} that matches the given file path, or an empty {@link Optional} if no match is found
     */
    public Optional<ResourceFile> findFirstMatchingFilePath(final String fileName) {
        return filterByFilePath(fileName).stream().findFirst();
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