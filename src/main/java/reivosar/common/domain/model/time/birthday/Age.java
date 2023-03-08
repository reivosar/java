package reivosar.common.domain.model.time.birthday;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.util.lang.ObjectUtil;

/**
 * Represents an age value.
 */
public class Age extends ValueObject<Age> {
    
    /**
     * The age value.
     */
    final int value;
    
    /**
     * Constructs a new age object with the specified value.
     *
     * @param value the age value
     */
    public Age(final int value) {
        if ((value < 0) || (value > 200)) {
            throw new IllegalArgumentException("Age must be between 1 and 200");
        }
        this.value = value;
    }
    
    /**
     * Checks if this age is the same as another age object.
     *
     * @param age the age object to compare with
     * @return true if this age is the same as the given age object, false otherwise
     * @throws NullPointerException if the given age object is null
     */
    public boolean isSame(final Age age) {
        ObjectUtil.requireNonNull("age", age);
        return this.value == age.value;
    }
    
    /**
     * Checks if this age is greater than another age object.
     *
     * @param age the age object to compare with
     * @return true if this age is greater than the given age object, false otherwise
     * @throws NullPointerException if the given age object is null
     */
    public boolean isOver(final Age age) {
        ObjectUtil.requireNonNull("age", age);
        return this.value > age.value;
    }
    
    /**
     * Checks if this age is less than another age object.
     *
     * @param age the age object to compare with
     * @return true if this age is less than the given age object, false otherwise
     * @throws NullPointerException if the given age object is null
     */
    public boolean isLess(final Age age) {
        ObjectUtil.requireNonNull("age", age);
        return this.value < age.value;
    }
}

