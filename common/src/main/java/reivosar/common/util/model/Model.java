package reivosar.common.util.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This is the base class of the model that overrides the 
 * equals, hashCode, and toString methods of {@code Object}.
 */
public abstract class Model
{
	/**
     * <p>Override the equals method ({@link Object#equals(Object)}) 
     * to check for equivalence with the other object passed as an argument.
     * </p>
     * 
	 * @param obj other object
	 * @see EqualsBuilder#reflectionEquals(Object, Object, String...)
     * @return {@code true} if the two Objects have tested equals.
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * <p>Override the hashcode method ({@link Object#hashCode()}) to returns
	 * a hash code value for the object.</p>
	 * 
     * @return int hash code
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * <p>Override the toString method ({@link Object#toString})
	 * to output the value of the variable held in the field of 
	 * this class. 
	 * </p>
	 * 
	 * The output format is class name[field name=value,field name=value,...].
	 * 
     * @return the String result
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
			this, ToStringStyle.SHORT_PREFIX_STYLE
		);
	}
}
