package com.busapp.user.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class ObjectMapperHelper {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public <T> List<T> fromResourceJson(String filename, Class<T> clazz) {
        try (InputStream inputStream = ObjectMapperHelper.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + filename);
            }
            return objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
//            throw new OutboundOrchestratorException(OutboundOrchestratorExceptionTypes.ERROR_READING_RESOURCE_FILE, e);
            throw new RuntimeException("Resource file not found: ");
        }
    }
}
