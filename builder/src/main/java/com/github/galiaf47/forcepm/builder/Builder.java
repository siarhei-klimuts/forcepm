package com.github.galiaf47.forcepm.builder;

import com.github.galiaf47.forcepm.builder.models.Package;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Builder {

    public static void main(String[] args) throws Exception {
        String svnURL = args[0];
        String marker = args[1];
        SVNRepository repository = getRepository(svnURL);
        List<String> files = getPackageFiles(repository, marker);
        Package psckage = new Package(files);
        JAXBContext jaxbContext = JAXBContext.newInstance(Package.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(psckage, sw);
        String xmlString = sw.toString();

        System.out.println(xmlString);
    }

    private static SVNRepository getRepository(String url) throws SVNException {
        DAVRepositoryFactory.setup();
        SVNURL svnUrl = SVNURL.parseURIDecoded(url);
        SVNRepository repository = SVNRepositoryFactory.create(svnUrl, null);
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager();
        repository.setAuthenticationManager(authManager);

        return repository;
    }

    private static List<String> getPackageFiles(SVNRepository repository, String marker) throws SVNException {
        List<String> files = new ArrayList<String>();
        Collection entries = repository.log(new String[] { "" }, null, 4000, 4001, true, true);

        for (Object entry : entries) {
            SVNLogEntry logEntry = (SVNLogEntry)entry;

            if (logEntry.getMessage().contains(marker)) {
                files.addAll(logEntry.getChangedPaths().keySet());
                System.out.println( "Changed: " + logEntry.getChangedPaths());
            }
        }

        return files;
    }
}
