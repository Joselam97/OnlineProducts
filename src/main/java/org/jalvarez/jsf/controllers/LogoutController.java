package org.jalvarez.jsf.controllers;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

// Combines @Named and @RequestScoped, making this class accessible in JSF views
@Model
public class LogoutController {

    // Provides access to the JSF context for managing messages and external resources
    @Inject
    private FacesContext facesContext;

    public String logout() throws ServletException {

        // Retrieves the HTTP request from the JSF FacesContext
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        // Logs out the current user, removing their authentication information
        request.logout();
        // Invalidates the HTTP session to clear any session-scoped data
        request.getSession().invalidate();
        // Adds a message to the FacesContext to inform the user that they have logged out
        facesContext.addMessage(null, new FacesMessage("Has cerrado sesion"));

        // Redirects the user to the login page after logout
        return "login.xhtml";
    }
}
