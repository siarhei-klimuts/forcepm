package com.github.galiaf47.forcepm.builder.services;

import com.github.galiaf47.forcepm.builder.models.Package;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Writer;

public class FilesGenerator {
    public void makePackageXml(Package pack, Writer writer) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Package.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(pack, writer);
    }
}
