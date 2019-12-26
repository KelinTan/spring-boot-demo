// Copyright 2019 Alo7 Inc. All rights reserved.

package com.alo7.kelin.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Kelin Tan
 */
@Log4j2
public class JsonConverter {
    public static Object deserialize(String json, Type type) {
        try {
            return JsonMapperFactory.defaultMapper().readValue(json,
                    JsonMapperFactory.defaultMapper().getTypeFactory().constructType(type));
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return JsonMapperFactory.defaultMapper().readValue(json, clazz);
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

    public static <T> T deserializeList(String json, Class<T> clazz) {
        try {
            return JsonMapperFactory.defaultMapper().readValue(json,
                    JsonMapperFactory.defaultMapper().getTypeFactory().constructCollectionType(
                            List.class, clazz));
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

    public static JsonNode readTree(String json) {
        try {
            return JsonMapperFactory.defaultMapper().readTree(json);
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }

    public static <T> String serialize(Class<T> clazz) {
        try {
            return JsonMapperFactory.defaultMapper().writeValueAsString(clazz);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    }

    public static String serialize(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return JsonMapperFactory.defaultMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    }

    public static byte[] serializeAsBytes(Object obj) {
        try {
            return JsonMapperFactory.defaultMapper().writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    }
}