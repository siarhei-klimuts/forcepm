package com.github.galiaf47.forcepm.builder.services;

import com.github.galiaf47.forcepm.builder.models.Package;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PackageBuilder {
    private Config cfg;

    public PackageBuilder() {
        cfg = new Config();
    }

    public Package build() throws SVNException {
        SVNRepository repository = getRepository(cfg.getSvnUrl());
        List<String> files = getPackageFiles(repository, cfg.getMarker());

        return new Package(files);
    }

    private SVNRepository getRepository(String url) throws SVNException {
        DAVRepositoryFactory.setup();
        SVNURL svnUrl = SVNURL.parseURIEncoded(url);
        SVNRepository repository = SVNRepositoryFactory.create(svnUrl, null);
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager();
        repository.setAuthenticationManager(authManager);

        return repository;
    }

    private List<String> getPackageFiles(SVNRepository repository, String marker) throws SVNException {
        List<String> files = new ArrayList<String>();
        SVNDirEntry info = repository.info(".", -1);
        long endRevision = info.getRevision();
        long startRevision = endRevision - 15;
        Collection entries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);

        for (Object entry : entries) {
            SVNLogEntry logEntry = (SVNLogEntry)entry;
            Map<String, SVNLogEntryPath> paths = logEntry.getChangedPaths();

            if (logEntry.getMessage().contains(marker)) {
                System.out.println("Revision: " + logEntry.getRevision());

                for (String pathKey : paths.keySet()) {
                    SVNLogEntryPath path = paths.get(pathKey);
                    if (path.getKind() != SVNNodeKind.FILE) {
                        continue;
                    }
                    if (path.getType() != SVNLogEntryPath.TYPE_DELETED) {
                        files.add(path.getPath());
                    }

                    System.out.println(path);
                }
            }
        }

        return files;
    }
}
