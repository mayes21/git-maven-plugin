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

@Mojo(name = "git-info", defaultPhase = LifecyclePhase.VALIDATE)
public class GitMojo extends AbstractMojo {

    private String branchName;

    private Git git;

    @Parameter(readonly = true, defaultValue = "${project}")
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            git = Git.open(new File(""));
            branchName = git.getRepository().getBranch();
        } catch (IOException e) {
            getLog().error(e.getMessage());
        } finally {
            git.close();
        }

        project.getProperties().put("branch.name", branchName);
    }
}
