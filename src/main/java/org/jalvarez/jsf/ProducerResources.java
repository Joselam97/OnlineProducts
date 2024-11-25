package org.jalvarez.jsf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;
import org.jalvarez.jsf.qualifiers.CustomFacesContext;

@ApplicationScoped
public class ProducerResources {

    @Produces
    @RequestScoped
    @CustomFacesContext
    public FacesContext beanFacesContext() {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            return facesContext;
    }
}
