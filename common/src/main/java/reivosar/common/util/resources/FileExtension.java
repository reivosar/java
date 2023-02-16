package reivosar.common.util.resources;

import com.google.common.io.Files;
import reivosar.common.util.lang.ObjectUtil;

import java.io.File;

/**
 * A record representing the file extension of a file.
 */
public record FileExtension(String extension) {
    
    public FileExtension(final String extension) {
        this.extension = ObjectUtil.requireNonNull("extension", extension);
    }
    
    /**
     * Returns a new FileExtension instance based on the file extension of the given file name.
     *
     * @param fileName the name of the file to extract the extension from
     * @return a new FileExtension instance
     */
    public static FileExtension of(final String fileName) {
        return new FileExtension(Files.getFileExtension(fileName));
    }
    
    /**
     * Returns a new FileExtension instance based on the file extension of the given file.
     *
     * @param file the file to extract the extension from
     * @return a new FileExtension instance
     */
    public static FileExtension of(final File file) {
        return of(file.getName());
    }
}
