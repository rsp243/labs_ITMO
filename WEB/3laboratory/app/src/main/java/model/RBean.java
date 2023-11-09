package model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Named
@ApplicationScoped
@Data
public class RBean implements Serializable {
    private Float value = (float) 1;

    public void rValueAction() {
        System.out.println("RValue - " + value);
    }

    public void validateR(FacesContext facesContext,
            UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Задайте значение R");
            throw new ValidatorException(message);
        }
    }

    public String getRValue() {
        if (value == null)
            return "Not Selected.";
        else
            return value.toString() + ".";
    }
}
