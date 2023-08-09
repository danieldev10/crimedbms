package dev.achonma.crimedbms;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path crimeRecordUploadDir = Paths.get("./static/assets/img/photos/");
        String crimeRecordUploadPath = crimeRecordUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("./static/assets/img/photos/**")
                .addResourceLocations("file:/" + crimeRecordUploadPath + "/");
    }

}
