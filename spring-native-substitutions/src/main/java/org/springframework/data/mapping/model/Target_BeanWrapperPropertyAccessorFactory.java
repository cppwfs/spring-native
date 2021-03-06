package org.springframework.data.mapping.model;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;

import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.nativex.substitutions.OnlyIfPresent;

@TargetClass(className = "org.springframework.data.mapping.model.BeanWrapperPropertyAccessorFactory", onlyWith = { OnlyIfPresent.class })
public final class Target_BeanWrapperPropertyAccessorFactory implements PersistentPropertyAccessorFactory {

	@Alias
	public static Target_BeanWrapperPropertyAccessorFactory INSTANCE;

	@Alias
	public <T> PersistentPropertyAccessor<T> getPropertyAccessor(PersistentEntity<?, ?> entity, T bean) {
		return null;
	}

	@Alias
	public boolean isSupported(PersistentEntity<?, ?> entity) {
		return false;
	}
}
