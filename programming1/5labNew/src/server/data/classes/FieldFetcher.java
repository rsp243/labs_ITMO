package server.data.classes;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import server.data.classes.Annotations.Complex;

/**
 * FieldFetcher Class
 * return LinkedHashMap of declared Fields of main Class 
 */

public class FieldFetcher {
    /**
     * fetchFields method
     * @return LinkedHashMap of declared Fields of main Class 
     */
    public LinkedHashMap<String, String> fetchFields() {
        var resultHashMap = new LinkedHashMap<String, String>();
        Field[] cityClass = City.class.getDeclaredFields();
        for (Field cityField : cityClass) {
            if (cityField.isAnnotationPresent(Complex.class)) {
                Field[] fieldsOfCityField = cityField.getType().getDeclaredFields();
                StringBuilder enumValues = new StringBuilder();
                for (Field field : fieldsOfCityField) {
                    if (!cityField.getType().isEnum()) {
                        resultHashMap.put(cityField.getDeclaringClass().getSimpleName() + "."
                                + cityField.getType().getSimpleName() + "." + field.getName(),
                                field.getType().getSimpleName());
                    } else {
                        if (field.getName() != "$VALUES") {
                            enumValues.append(field.getName() + ", ");
                        } else {
                            resultHashMap.put(cityField.getDeclaringClass().getSimpleName() + "."
                                    + cityField.getType().getSimpleName(),
                                    "Enum. Values: " + enumValues.delete(enumValues.length() - 2, enumValues.length())
                                            .toString());
                        }
                    }
                }
            } else {
                resultHashMap.put(cityField.getDeclaringClass().getSimpleName() + "." + cityField.getName(),
                        cityField.getType().getSimpleName());
            }
        }
        return resultHashMap;
    }
}
