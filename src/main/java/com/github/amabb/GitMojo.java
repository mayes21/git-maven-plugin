package com.github.amabb;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.io.IOException;

/**
 * MOJO to get git branch name of repository, check if match given regexp and build the suffix of the fat JAR
 * > Default maven lifecyce phase : VALIDATE
 */
@Mojo(name = "git-info", defaultPhase = LifecyclePhase.VALIDATE)
public class GitMojo extends AbstractMojo {

    /**
     * suffix for the generated fat JAR
     */
    @Parameter(name = "fatJarSuffix")
    private String fatJarSuffix;

    /**
     * the regular expression that the branch name have to match
     */
    @Parameter(name = "regex", required = true)
    private String regex;

    /**
     * the repository that contains .git folder
     */
    @Parameter(name = "basedir", required = true)
    private String basedir;


    @Parameter(readonly = true, defaultValue = "${project}")
    private MavenProject project;

    private String branchName;

    private Git git;


    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            git = Git.open(new File(basedir));
            branchName = git.getRepository().getBranch();
            checkBranchName(branchName, regex);
        } catch (IOException e) {
            getLog().error(e.getMessage());
        } finally {
            git.close();
        }

        project.getProperties().put(Constant.JAR_SUFFIX, checkBranchName(branchName, regex));
    }


    /**
     * check if the branch name does not match the regexp
     */
    private String checkBranchName(String branchName, String regex) {
        if (branchName.matches(regex)) {
            return branchName.concat(Constant.SEPARATOR).concat(fatJarSuffix);
        } else {
            return Constant.SEPARATOR.concat(fatJarSuffix);
        }
    }


    private class Constant {
        Constant(){}
        private static final String JAR_SUFFIX = "jar.suffix";
        private static final String SEPARATOR = "-";
    }
}
