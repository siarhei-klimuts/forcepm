package com.github.galiaf47.forcepm.builder.models;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class PackageType {
    @XmlElement
    private String name;
    @XmlElement
    private List<PackageMember> members;

    public PackageType(String name) {
        this.name = name;
        this.members = new ArrayList<PackageMember>();
    }

    public void addMember(PackageMember member) {
        members.add(member);
    }
}
