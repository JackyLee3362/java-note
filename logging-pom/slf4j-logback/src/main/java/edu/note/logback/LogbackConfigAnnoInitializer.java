package edu.note.logback;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class LogbackConfigAnnoInitializer {

    public static void initialize(Class<?> clazz) {
        if (clazz.isAnnotationPresent(ConfigFile.class)) {
            ConfigFile configFile = clazz.getAnnotation(ConfigFile.class);
            String fileName = configFile.value();
            if (Objects.isNull(fileName) || fileName.isEmpty()) {
                fileName = clazz.getSimpleName() + ".xml";
            }
            URL resource = clazz.getClassLoader().getResource(fileName);
            if (resource == null) {
                System.out.println("资源不存在");
                return;
            }
            try {
                String logConfigPath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
                // TODO 可能在更高版本存在，需要调整版本
                // System.setProperty(ClassicConstants.CONFIG_FILE_PROPERTY, logConfigPath);
                System.setProperty("logback.configurationFile", logConfigPath);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
