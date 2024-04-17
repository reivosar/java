package reivosar.common.lang.reflect.member;

/**
 * An interface for accessing a field's value in an object.
 */
public interface FieldAccessor {
    
    /**
     * Returns the value of the field in the specified target object.
     *
     * @param target the object to retrieve the field value from
     * @return the field value
     */
    Object readField(Object target);
    
    /**
     * Returns the value of the static field.
     *
     * @return the static field value
     */
    Object readStaticField();
    
    /**
     * Sets the value of the field in the specified target object.
     *
     * @param target the object to set the field value in
     * @param value  the new value to set
     */
    void writeField(Object target, Object value);
    
    /**
     * Sets the value of the static field.
     *
     * @param value the new value to set
     */
    void writeStaticField(Object value);
}