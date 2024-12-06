package org.jalvarez.jsf.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Named // Makes this bean accessible in JSF views with the name "lenguajeController" (or specified name)
@SessionScoped // Keeps the bean alive during the user's session
public class LenguajeController implements Serializable {

    // Unique identifier for Serializable class
    private static final Long serialVersionUID = 14685489L;

    private Locale locale; // Represents the current locale (language and region) of the user
    private String lenguaje; // Holds the language code selected by the user
    private Map<String, String> lenguajesSoportados; // Stores supported languages as key-value pairs

    // Initializes the locale to the user's default or current locale
    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

        // Populates the map of supported languages with their names and codes
        lenguajesSoportados = new HashMap<>();
        lenguajesSoportados.put("English", "en");
        lenguajesSoportados.put("Español", "es");
    }

    // Handles language selection changes from the UI
    public void seleccionar(ValueChangeEvent event) {
        // Gets the new language code from the event
        String nuevo = event.getNewValue().toString();

        // Iterates over supported languages and updates the locale if the code matches
        lenguajesSoportados.values().forEach(v -> {
            if (v.equals(nuevo)) {
                this.locale = new Locale(nuevo); // Sets the new locale
                FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);  // Updates the view locale
            }
        });
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Map<String, String> getLenguajesSoportados() {
        return lenguajesSoportados;
    }

    public void setLenguajesSoportados(Map<String, String> lenguajesSoportados) {
        this.lenguajesSoportados = lenguajesSoportados;
    }
}
