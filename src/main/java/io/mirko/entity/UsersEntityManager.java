package io.mirko.entity;

import javax.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface UsersEntityManager {
}
