package com.onion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author gyc
 * @date 2020/4/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public List<Parameter> headParams (){

        //在配置好的配置类中增加此段代码即可
        ParameterBuilder ticketPar = new ParameterBuilder();

        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("登录校验")//name表示名称，description表示描述
                .modelRef(new ModelRef("string")).parameterType("header") //parameterType跟前文提到的过的一样，这里header代表的是头部
                .required(false).defaultValue("Bearer ").build();//required表示是否必填，defaultvalue表示默认值

        pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效

        return pars;
    }


    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.onion.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
//                .globalOperationParameters(headParams());
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(securityScheme()));

    }

    private ApiInfo apiInfo() {

        //联系方式，可以不传进去ApiInfo中
        Contact contact = new Contact("cong", "baidu.com", "123456@qq.com");
        return new ApiInfo(
                "测试API标题",
                "测试API描述",
                "1.0",
                null,
                contact,
                "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());
    }


    public SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("onion_auth_swagger", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("test", "")
        };
    }


    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8301/auth/oauth/token");

        return new OAuthBuilder()
                .name("onion_auth_swagger")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }
}

