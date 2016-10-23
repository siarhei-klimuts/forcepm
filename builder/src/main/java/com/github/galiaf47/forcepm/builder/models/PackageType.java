package com.github.galiaf47.forcepm.builder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

@XmlType(propOrder = {"members", "name"})
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
