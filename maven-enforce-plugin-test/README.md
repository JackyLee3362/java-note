## Enforced 插件

排除某个规则

```xml
<executions>
  <execution>
    <!--id一定要一样才能覆盖之前的规则-->
    <id>enforce-banned-dependencies</id>
    <goals>
      <goal>enforce</goal>
    </goals>
    <phase>validate</phase>
    <configuration>
      <rules>
        <bannedPlugins>
          <includes>
            <include>org.apache.maven.plugins:maven-surefire-plugin:2.20</include>
          </includes>
        </bannedPlugins>
      </rules>
      <fail>true</fail>
    </configuration>
  </execution>
</executions>

```