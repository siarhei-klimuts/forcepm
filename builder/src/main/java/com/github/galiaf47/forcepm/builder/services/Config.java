package com.github.galiaf47.forcepm.builder.services;


import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String FILE_NAME = "config.properties";
    private static Properties props = new Properties();

    public Config() {
        if (props == null) {
            props = new Properties();

            try {
                props.load(getClass().getClassLoader().getResourceAsStream(FILE_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadCmdOptions(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("svn", "svnURL", true, "SVN URL");
        options.addOption("src", "srcPath", true, "src Path");
        options.addOption("m", "marker", true, "Package marker in commit messages");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        for (Option opt : options.getOptions()) {
            String optName = opt.getOpt();

            if (cmd.hasOption(optName)) {
                props.setProperty(opt.getLongOpt(), cmd.getOptionValue(optName));
            }
        }
    }

    public String getSvnUrl() {
        return props.getProperty("svnURL");
    }

    public String getSrcPath() {
        return props.getProperty("srcPath");
    }

    public String getMarker() {
        return props.getProperty("marker");
    }
}
