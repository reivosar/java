package reivosar.common.resources;

import org.apache.commons.io.FilenameUtils;
import reivosar.common.data.model.Model;
import reivosar.common.lang.ObjectUtil;

import java.io.*;
import java.util.function.Consumer;

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

    /**
     * Returns the base name of the file, which is the file name without its extension.
     * <p>
     * This method uses {@link FilenameUtils#getBaseName(String)} to remove the extension
     * from the file name returned by {@link #fileName()}. For example, if the file name is
     * "example.txt", this method will return "example".
     * </p>
     *
     * @return the base name of the file, without the extension
     */
    public String baseName() {
        return FilenameUtils.getBaseName(fileName());
    }

    /**
     * Executes a given action using an {@link InputStream} created from the file associated with this instance.
     * The InputStream is automatically closed after the action is executed.
     *
     * <p>This method ensures that the {@link InputStream} is properly opened and closed,
     * allowing the provided {@link Consumer} to perform operations on the stream.
     * If an {@link IOException} occurs during the process, it is wrapped in an {@link UncheckedIOException}
     * and rethrown to the caller.</p>
     *
     * @param action the {@link Consumer} that performs an operation on the {@link InputStream}
     */
    public void useInputStream(final Consumer<InputStream> action) {
        try (final InputStream input = new FileInputStream(this.file)) {
            action.accept(input);
        } catch (IOException e) {
            // The InputStream may fail to be created due to issues with the file,
            // but since this is a problem related to the file itself and not the process,
            // we choose not to throw an exception to avoid impacting the overall operation.
        }
    }
}
