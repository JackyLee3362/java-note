## 配置文件

```sh
M2_HOME/conf/setting.xml
```

### 本地仓库修改

```xml
<localRepository>D:/path/to/repository</localRepository>
```

## 最佳实践：springboot 的 pom 文件

## 最佳实践：打包 springboot 项目

### 遇到的问题

```bash
cd /path/to/maven/project
mvn package
java -jar xxx.jar包

# 输出
.\xxx.jar中没有主清单属性
```

### 打包需要的插件

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>${spring-boot.version}</version>
    <configuration>
        <mainClass>com.example.SpringWebSimpleDemoApplication</mainClass>
        <!--一定要把这个注释掉 ！-->
        <!--<skip>true</skip>-->
    </configuration>
    <executions>
        <execution>
            <id>repackage</id>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

配置好以后

```bash
mvn clean package
```
