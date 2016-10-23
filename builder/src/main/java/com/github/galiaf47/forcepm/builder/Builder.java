package com.github.galiaf47.forcepm.builder;

import com.github.galiaf47.forcepm.builder.models.Package;
import com.github.galiaf47.forcepm.builder.services.Config;
import com.github.galiaf47.forcepm.builder.services.FilesGenerator;
import com.github.galiaf47.forcepm.builder.services.PackageBuilder;

import java.io.StringWriter;

public class Builder {

    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        cfg.loadCmdOptions(args);

        FilesGenerator generator = new FilesGenerator();
        PackageBuilder builder = new PackageBuilder();

        StringWriter sw = new StringWriter();
        Package pack = builder.build();
        generator.makePackageXml(pack, sw);
        String xmlString = sw.toString();

        System.out.println(xmlString);
    }
}
