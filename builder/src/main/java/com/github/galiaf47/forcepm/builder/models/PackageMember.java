package com.github.galiaf47.forcepm.builder.models;

import com.github.galiaf47.forcepm.builder.services.PackageTypesResolver;
import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PackageMember {
    @XmlValue
    @NotNull
    String name;
    @XmlTransient
    String type;

    public PackageMember(String path) {
        Path p = Paths.get(path);
        this.name = extractName(p);
        this.type = extractType(p);
    }

    private String extractName(Path p) {
        String filename = p.getFileName().toString();
        int index = filename.indexOf(".");

        return filename.substring(0, index);
    }

    private String extractType(Path p) {
        String dir = p.getName(p.getNameCount() - 2).toString();
        return PackageTypesResolver.getType(dir);
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        PackageMember other = (PackageMember)obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
