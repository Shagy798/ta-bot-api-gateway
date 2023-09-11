package com.netcracker.edu.api.controller.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class AppUtility {
    private static String absolutePath = new File("").getAbsolutePath();

    public static String getContentFromResourceFile(String fileName) {
        try {
            InputStream inputStream = AppUtility.class.getClassLoader().getResourceAsStream(fileName);
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static String getContentFromFile(String fullyQualifiedPath) {
        try {
            return new String(Files.readAllBytes(Paths.get(absolutePath + fullyQualifiedPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mergeNode(JsonNode origNode, JsonNode updateNode) {
        Iterator<String> fieldNames = updateNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode jsonNode = origNode.get(fieldName);
            if (null != jsonNode && (jsonNode.isObject())) {
                mergeNode(jsonNode, updateNode.get(fieldName));
            } else if (null != jsonNode && (jsonNode.isArray())) {
                mergeArray(jsonNode, updateNode.get(fieldName));
            } else if (updateNode.hasNonNull(fieldName)) {
                ((ObjectNode) origNode).set(fieldName, updateNode.get(fieldName));
            }
        }
    }

    public static void mergeArray(JsonNode mainNode, JsonNode updateNode) {
        for (int i = 0; i < updateNode.size(); i++) {
            JsonNode jsonNode = mainNode.get(i);
            if (null != jsonNode && jsonNode.isObject()) {
                mergeNode(jsonNode, updateNode.get(i));
            } else if (null != jsonNode && (jsonNode.isArray())) {
                mergeArray(jsonNode, updateNode.get(i));
            } else if (null == jsonNode) {
                ((ArrayNode) mainNode).add(updateNode.get(i));
            } else {
                ((ArrayNode) mainNode).set(i, updateNode.get(i));
            }
        }
    }
}
