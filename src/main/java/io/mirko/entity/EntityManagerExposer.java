package io.mirko.entity;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerExposer {
    @Produces
    @PersistenceContext(unitName="DaschnerPersistenceUnit")
    @UsersEntityManager
    EntityManager entityManager;

}
