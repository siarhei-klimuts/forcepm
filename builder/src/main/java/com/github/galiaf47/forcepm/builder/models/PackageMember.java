package com.github.galiaf47.forcepm.builder.models;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PackageMember {
    @XmlValue
    String name;
    @XmlTransient
    String type;

    public PackageMember(String path) {
        Path p = Paths.get(path);
        this.name = extractName(p);
        this.type = extractType(p);
    }

    private String extractName(Path p) {
        return p.getFileName().toString();
    }

    private String extractType(Path p) {
        return p.getName(p.getNameCount() - 2).toString();
    }

    public String getType() {
        return type;
    }
}
