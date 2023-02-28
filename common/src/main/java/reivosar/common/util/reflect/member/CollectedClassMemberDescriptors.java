package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
class CollectedClassMemberDescriptors<T extends ClassMemberDescriptor,
                                      S extends ClassMemberDescriptors<T, S>>
        extends Model
        implements ClassMemberDescriptors<T, S> {
    
    private final Collection<T> descriptors;
    
    protected CollectedClassMemberDescriptors(final Collection<T> descriptors) {
        this.descriptors = descriptors;
    }
    
    @Override
    public S filterByName(final String name) {
        ObjectUtil.requireNonNull("name", name);
        return (S) new CollectedClassMemberDescriptors<T, S>(filter(getDescriptors(), t -> t.getName().equals(name)));
    }
    
    @Override
    public S filterByDescribedMember(final String describedMember) {
        ObjectUtil.requireNonNull("describedMember", describedMember);
        return (S) new CollectedClassMemberDescriptors<T, S>(filter(getDescriptors(), t -> t.getDescribedMember().equals(describedMember)));
    }
    
    protected final Collection<T> filter(final Collection<T> collection, final Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .toList();
    }
    
    @Override
    public Collection<T> getDescriptors() {
        return this.descriptors;
    }
}
