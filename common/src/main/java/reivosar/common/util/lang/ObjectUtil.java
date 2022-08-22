package reivosar.common.util.lang;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Utility Library for object
 */
public class ObjectUtil {

    private ObjectUtil() {
    }

    /**
     * Returns a default value if the object passed is null.
     *
     * @param object – the Object to test, may be null defaultValue – the default value to return, may be null
     *        defaultObject - the default value to return, may be null
     * @return object if it is not null, defaultValue otherwise
     */
    public static <T> T getIfNull(T object, T defaultObject) {
        return ObjectUtils.defaultIfNull(object, defaultObject);
    }
}
