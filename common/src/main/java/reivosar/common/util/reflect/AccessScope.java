package reivosar.common.util.reflect;

import java.util.Arrays;

/**
 * Enum representing the scope of access for a Java class member.
 */
public enum AccessScope {
    
    /**
     * Public scope access.
     */
    PUBLIC() {
        @Override
        boolean isSatisfied(final ClassModifier modifier) {
            return modifier.isPublic();
        }
    },
    
    /**
     * Protected scope access.
     */
    PROTECTED() {
        @Override
        boolean isSatisfied(final ClassModifier modifier) {
            return modifier.isProtected();
        }
    },
    
    /**
     * Private scope access.
     */
    PRIVATE() {
        @Override
        boolean isSatisfied(final ClassModifier modifier) {
            return modifier.isPrivate();
        }
    };
    
    /**
     * Returns the AccessScope that corresponds to the given ClassModifier.
     *
     * @param modifier the ClassModifier to map to an AccessScope
     * @return the AccessScope that corresponds to the given modifier
     * @throws IllegalStateException if no matching AccessScope is found
     */
    public static AccessScope of(final ClassModifier modifier) {
        return Arrays.stream(values())
                .filter(accessScope -> accessScope.isSatisfied(modifier))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
    
    /**
     * Returns whether this AccessScope is satisfied by a given ClassModifier.
     *
     * @param modifier the ClassModifier to check against
     * @return true if this AccessScope is satisfied by the given modifier, false otherwise
     */
    abstract boolean isSatisfied(final ClassModifier modifier);
}
