package com.cbhx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author WangJiangQi
 * @since 2023-02-06
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJsonString(Object arg) {
        try {
            return getObjectMapper().writeValueAsString(arg);
        } catch (JsonProcessingException e) {
            log.error("JsonUtil write Object as String error", e);
        }
        return null;
    }

    /**
     * get object mapper
     */
    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }

    /**
     * get object node
     */
    public static ObjectNode getObjectNode() {
        return MAPPER.createObjectNode();
    }

    /**
     * get array node
     */
    public static ArrayNode getArrayNode() {
        return MAPPER.createArrayNode();
    }

    /**
     * convert String to java object
     */
    public static <T> T readValue(String content, Class<T> classze) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(content, classze);
        } catch (Exception e) {
            log.error("JsonUtil read value as java Object error", e);
        }
        return null;
    }

    /**
     * convert String to json object
     */
    public static JsonNode readTree(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper().readTree(content);
        } catch (Exception e) {
            log.error("JsonUtil read value as java Object error", e);
        }
        return null;
    }

    /**
     * convert String to java object list
     */
    public static <T> List<T> readValueList(String content, Class<T> classze) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(content, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            log.error("JsonUtil read value as java Object list error", e);
        }
        return null;
    }

    /**
     * convert String to List<Map<String,String>>
     */
    public static List<Map<String, String>> readMapList(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper()
                    .readValue(content, new TypeReference<List<Map<String, String>>>() {
                    });
        } catch (Exception e) {
            log.error("JsonUtil read value as List<Map<String, String>> error", e);
        }
        return null;
    }


    /**
     * convert String to Map
     */
    public static Map<String, String> readMap(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(content, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            log.error("JsonUtil read value as Map<String, String> error", e);
        }
        return null;
    }

    /**
     * convert String to Map
     */
    public static Map<String, Object> readMapObject(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.error("JsonUtil read value as Map<String, Object> error", e);
        }
        return null;
    }

    /**
     * convert string to Set<String>
     */
    public static Set<String> readSet(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            return getObjectMapper().readValue(content, new TypeReference<Set<String>>() {
            });
        } catch (Exception e) {
            log.error("JsonUtil read value as Set<String> error", e);
        }
        return null;
    }
}
