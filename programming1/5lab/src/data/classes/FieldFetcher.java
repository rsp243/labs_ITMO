package src.data.classes;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import src.data.classes.Annotations.Complex;

public class FieldFetcher {
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
                        if (field.getName() != "ENUM$VALUES") {
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
