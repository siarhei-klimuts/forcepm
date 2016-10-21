package com.github.galiaf47.forcepm.builder.models;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Set;

public class PackageType {
    @XmlElement
    private String name;
    @XmlElement
    private Set<PackageMember> members;

    public PackageType(String name) {
        this.name = name;
        this.members = new HashSet<PackageMember>();
    }

    public void addMember(PackageMember member) {
        members.add(member);
    }
}
