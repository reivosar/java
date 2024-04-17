package reivosar.common.resources;

import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Model;

import java.io.File;

/**
 * A model representing a resource file.
 */
public class ResourceFile extends Model {
    
    private final File file;
    private final String unixFilePath;
    private final String fileName;
    
    /**
     * Creates a new ResourceFile instance based on the given file.
     *
     * @param file the file to create the resource file from
     * @throws NullPointerException if the file is null
     */
    public ResourceFile(final File file) {
        this.file = ObjectUtil.requireNonNull("file", file);
        this.unixFilePath = file.getAbsolutePath().replace('\\', '/');
        this.fileName = file.getName();
    }
    
    /**
     * Returns the file associated with this resource file.
     *
     * @return the file associated with this resource file
     */
    public File toFile() {
        return file;
    }
    
    /**
     * Returns true if the file name of this resource file matches the given file name.
     *
     * @param fileName the file name to match against
     * @return true if the file name of this resource file matches the given file name
     */
    public boolean matchFilename(final String fileName) {
        ObjectUtil.requireNonNull("fileName", fileName);
        return this.fileName.equalsIgnoreCase(fileName);
    }
    
    /**
     * Returns true if the file path of this resource file matches the given file path.
     *
     * @param filePath the file path to match against
     * @return true if the file path of this resource file matches the given file path
     */
    public boolean matchUnixFilePath(final String filePath) {
        ObjectUtil.requireNonNull("filePath", filePath);
        return this.unixFilePath.endsWith(filePath);
    }
    
    /**
     * Returns the file name associated with this resource file.
     *
     * @return the file name associated with this resource file
     */
    public String fileName() {
        return fileName;
    }
}
