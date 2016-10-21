package com.github.galiaf47.forcepm.builder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "Package")
public class Package {
    @XmlTransient
    private Map<String, PackageType> types;
    @XmlElement
    private String version = "36.0";

    public  Package() {
        this.types = new HashMap<String, PackageType>();
    }

    public Package(List<String> paths) {
        this();

        for (String path : paths) {
            PackageMember member = new PackageMember(path);
            addMember(member);
        }
    }

    public void addMember(PackageMember member) {
        String memberType = member.getType();
        PackageType type = types.get(memberType);

        if (type == null) {
            type = new PackageType(memberType);
            types.put(memberType, type);
        }

        type.addMember(member);
    }

    @XmlElement
    public Collection<PackageType> getTypes() {
        return types.values();
    }
}
