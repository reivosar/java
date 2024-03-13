package reivosar.common.util.io.pdf;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

/**
 * Represents a password for a PDF document. This class encapsulates the password
 * string and provides a safe way to handle password data within the application.
 * <p>
 * The {@code toString()} method has been overridden to prevent accidental
 * exposure of the password in logs or other outputs.
 */
public class PdfPassword extends Model {
    /**
     * The password for the PDF document.
     */
    final String value;

    /**
     * Constructs a new {@code PdfPassword} with the specified password.
     *
     * @param value the password string for the PDF document. It should not be null.
     * @throws NullPointerException if the provided path is null.
     */
    public PdfPassword(final String value) {
        this.value = ObjectUtil.requireNonNull("password", value);
    }

    /**
     * Returns a string representation of the {@code PdfPassword}.
     * <p>
     * To enhance security, this method returns a masked version of the password
     * instead of the actual password string.
     *
     * @return a masked string representation of the password
     */
    @Override
    public String toString() {
        return "********";
    }
}
