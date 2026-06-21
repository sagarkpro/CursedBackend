package com.cursedbackend.configs;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
// @SecurityScheme(name = "bearerAuth", // Name of the security scheme
//         type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class AppConfig {

    /**
     * swagger-core (bundled by springdoc) is Jackson 2 only and does not recognize the
     * Jackson 3 JsonNode type; left to introspect it as a bean, OpenAPI generation NPEs.
     * This converter intercepts any JsonNode-typed member (including nested fields) before
     * swagger's resolver runs and emits a free-form object schema instead.
     */
    @Bean
    public ModelConverter jsonNodeModelConverter() {
        return new ModelConverter() {
            @Override
            public Schema<?> resolve(AnnotatedType annotatedType, ModelConverterContext context,
                    Iterator<ModelConverter> chain) {
                Type t = annotatedType.getType();
                Class<?> raw = null;
                if (t instanceof Class<?> c) {
                    raw = c;
                } else if (t instanceof ParameterizedType pt && pt.getRawType() instanceof Class<?> c) {
                    raw = c;
                }
                if (raw != null && JsonNode.class.isAssignableFrom(raw)) {
                    return new ObjectSchema();
                }
                return chain.hasNext() ? chain.next().resolve(annotatedType, context, chain) : null;
            }
        };
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder().build();
    }
}
