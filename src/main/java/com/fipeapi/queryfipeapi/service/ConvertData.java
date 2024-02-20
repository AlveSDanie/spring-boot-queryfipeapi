package com.fipeapi.queryfipeapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertJsonToObjectList(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T convertJsonToObject(String json, Class<T> Iclass) {
        try {
            return mapper.readValue(json, Iclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

