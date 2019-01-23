
# Git maven plugin 
[![Build Status](https://travis-ci.com/mayes21/git-maven-plugin.svg?branch=master)](https://travis-ci.com/mayes21/git-maven-plugin)

### Description
Maven Plugin used to get git branch name of repository, check if match given regexp and build the suffix of the fat JAR

### Example to use the plugin

```
<plugin>
    <groupId>com.github.amabb</groupId>
    <artifactId>git-maven-plugin</artifactId>
    <version>1.0</version>
    <executions>
        <execution>
            <goals>
                <goal>git-info</goal>
            </goals>
            <configuration>
                <fatJarSuffix>exec</fatJarSuffix>
                <basedir>${basedir}</basedir>
                <regex>\b(?!develop|master)\b\S+</regex>
            </configuration>
        </execution>
    </executions>
</plugin>
```
