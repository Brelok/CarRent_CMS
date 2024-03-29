package com.github.brelok;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Car API")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("Simple REST API to learn")
                .title("Rent a car")
                .build();
    }

    /*
    from Bealdung about time
     */
//    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//    SpringTemplateEngine engine = new SpringTemplateEngine();
//    engine.addDialect(new Java8TimeDialect());
//    engine.setTemplateResolver(templateResolver);
//    return engine;
//}
}
