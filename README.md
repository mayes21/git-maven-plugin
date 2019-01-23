# Git maven plugin

##### Example to use the plugin

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