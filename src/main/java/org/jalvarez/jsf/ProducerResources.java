package org.jalvarez.jsf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.jalvarez.jsf.qualifiers.CustomFacesContext;

import java.util.Locale;
import java.util.ResourceBundle;

// Ensures this bean lives for the entire application lifecycle
@ApplicationScoped
public class ProducerResources {

    @Produces // Allows this method to produce a `FacesContext` for dependency injection
    @RequestScoped // Limits the lifecycle of the produced `FacesContext` to the current HTTP request
    @CustomFacesContext // Custom qualifier to distinguish this producer in case of multiple `FacesContext` injections

    public FacesContext beanFacesContext() {
        // Retrieves the current `FacesContext` instance
        FacesContext facesContext = FacesContext.getCurrentInstance();
        // Ensures flash messages persist across redirects
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        // Returns the configured `FacesContext` for injection elsewhere
        return facesContext;
    }

    // Allows this method to produce a `ResourceBundle` for dependency injection
    @Produces
    // Makes this `ResourceBundle` accessible in JSF views with the name "msg"
    @Named("msg")
    public ResourceBundle beanBundle() {
        // Gets the current locale from the view
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        // Loads the `messages` resource bundle for the current locale
        return ResourceBundle.getBundle("messages", locale);
    }
}
