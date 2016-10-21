package com.github.galiaf47.forcepm.builder.services;

import java.util.HashMap;
import java.util.Map;

public class PackageTypesResolver {
    private static final Map<String, String> typesMap = new HashMap<String, String>();
    static {
        typesMap.put("classes", "ApexClass");
        typesMap.put("components", "ApexComponent");
        typesMap.put("labels", "CustomLabel");
        typesMap.put("objects", "CustomObject");
        typesMap.put("pages", "ApexPage");
        typesMap.put("staticresources", "StaticResource");
        typesMap.put("triggers", "ApexTrigger");
    }

    public static String getType(String dir) {
        return typesMap.get(dir);
    }
}
