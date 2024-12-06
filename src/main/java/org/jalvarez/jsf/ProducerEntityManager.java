package org.jalvarez.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// Defines the lifecycle of this bean to be tied to a single HTTP request
@RequestScoped
public class ProducerEntityManager {

    // Injects an EntityManager for the persistence unit named "ejemploJpa"
    @PersistenceContext(name = "ejemploJpa")
    private EntityManager em;

    // Marks this method as a producer to make the EntityManager available for injection elsewhere
    @Produces
    // Ensures the produced EntityManager is tied to the current HTTP request scope
    @RequestScoped
    private EntityManager beanEntityManager() {
        // Returns the injected EntityManager instance for use in other beans or services
        return em;
    }
}
