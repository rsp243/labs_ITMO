package src.data.classes;

import java.lang.reflect.Field;
import java.util.HashMap;

import src.data.classes.Annotations.Complex;

public class FieldFetcher {
    public HashMap<String, String> fetchFields() {
        var resultHashMap = new HashMap<String, String>();
        Field[] cityClass = City.class.getDeclaredFields();
        for (Field cityField : cityClass) {
            if (cityField.isAnnotationPresent(Complex.class)) {
                Field[] fieldsOfCityField = cityField.getType().getDeclaredFields();
                for (Field field : fieldsOfCityField) {
                    if (!cityField.getType().isEnum()) {
                        resultHashMap.put(cityField.getDeclaringClass().getSimpleName() + "."
                                + cityField.getType().getSimpleName() + "." + field.getName(), field.getType().getSimpleName());
                    }
                }
            } else {
                resultHashMap.put(cityField.getDeclaringClass().getSimpleName() + "." + cityField.getName(), cityField.getType().getSimpleName());
            }
        }
        return resultHashMap;
    }
}
