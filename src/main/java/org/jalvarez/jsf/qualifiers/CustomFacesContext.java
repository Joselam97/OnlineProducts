package org.jalvarez.jsf.qualifiers;

import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom CDI qualifier annotation to differentiate injection points
@Qualifier
@Retention(RetentionPolicy.RUNTIME) // This annotation will be retained at runtime, allowing it to be used during dependency injection

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
// - FIELD: Can be applied to fields
// - PARAMETER: Can be applied to method parameters
// - METHOD: Can be applied to methods

public @interface CustomFacesContext {
    /* This annotation has no methods; it's used by 'ProducerResources'
    to avoid Ambiguous Dependencies */
}
