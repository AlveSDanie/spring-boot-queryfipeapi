package com.fipeapi.queryfipeapi.service;

import com.fasterxml.jackson.core.type.TypeReference;

public interface IConvertData {
    <T> T convertJsonToObjectList(String json, TypeReference<T> type);

    <T> T convertJsonToObject(String json, Class<T> Iclass);
}
