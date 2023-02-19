package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.reflect.Modifier;

/**
 * The ClassModifier class provides methods to check the modifiers of a Java class.
 * It extends the Model class, which provides basic getter and setter methods for
 * its properties.
 */
public class ClassModifier extends Model {
    
    /** The modifier value of the class. */
    private final int mod;
    
    /**
     * Instantiates a new ClassModifier object with the specified modifier value.
     *
     * @param mod the modifier value of the class.
     */
    public ClassModifier(final int mod) {
        this.mod = mod;
    }
    
    /**
     * Checks whether the class is declared as public.
     *
     * @return true if the class is declared as public, false otherwise.
     */
    public boolean isPublic() {
        return Modifier.isPublic(mod);
    }
    
    /**
     * Checks whether the class is declared as static.
     *
     * @return true if the class is declared as static, false otherwise.
     */
    public boolean isStatic() {
        return Modifier.isStatic(mod);
    }
    
    /**
     * Checks whether the class is declared as abstract.
     *
     * @return true if the class is declared as abstract, false otherwise.
     */
    public boolean isAbstract() {
        return Modifier.isAbstract(mod);
    }
    
    /**
     * Checks whether the class is declared as private.
     *
     * @return true if the class is declared as private, false otherwise.
     */
    public boolean isPrivate() {
        return Modifier.isPrivate(mod);
    }
    
    /**
     * Checks whether the class is declared as final.
     *
     * @return true if the class is declared as final, false otherwise.
     */
    public boolean isFinal() {
        return Modifier.isFinal(mod);
    }
    
    /**
     * Checks whether the class is declared as an interface.
     *
     * @return true if the class is declared as an interface, false otherwise.
     */
    public boolean isInterface() {
        return Modifier.isInterface(mod);
    }
    
    /**
     * Checks whether the class is declared as native.
     *
     * @return true if the class is declared as native, false otherwise.
     */
    public boolean isNative() {
        return Modifier.isNative(mod);
    }
    
    /**
     * Checks whether the class is declared as protected.
     *
     * @return true if the class is declared as protected, false otherwise.
     */
    public boolean isProtected() {
        return Modifier.isProtected(mod);
    }
    
    /**
     * Checks whether the class is declared as synchronized.
     *
     * @return true if the class is declared as synchronized, false otherwise.
     */
    public boolean isSynchronized() {
        return Modifier.isSynchronized(mod);
    }
    
    /**
     * Checks whether the class is declared as transient.
     *
     * @return true if the class is declared as transient, false otherwise.
     */
    public boolean isTransient() {
        return Modifier.isTransient(mod);
    }
    
    /**
     * Checks whether the class is declared as volatile.
     *
     * @return true if the class is declared as volatile, false otherwise.
     */
    public boolean isVolatile() {
        return Modifier.isVolatile(mod);
    }
}