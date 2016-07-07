/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techidea.data.cache.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Class  as Serializer/Deserializer
 */
public class JsonSerializer<T> {

    // TODO: 2016/7/6 使用 typetoken 总觉得麻烦，但是还不知道该怎么写更好。不想构造函数传进来参数

    private final Gson gson = new Gson();

    public JsonSerializer() {
    }

    /**
     * Serialize to Json.
     *
     * @param {@link } to serialize.
     */
    public String serialize(T entity) {
        String jsonString = gson.toJson(entity, entity.getClass());
        return jsonString;
    }

    public String serialize(T entity, TypeToken<T> type) {
        String jsonString = gson.toJson(entity, type.getType());
        return jsonString;
    }

    /**
     * Deserialize a json representation of
     *
     * @param jsonString A json string to deserialize.
     * @return {@link }
     */
    public T deserialize(String jsonString, Class<T> type) {
        T entity = gson.fromJson(jsonString, type);
        return entity;
    }

    public T deserialize(String jsonString, TypeToken<T> type) {
        T entity = gson.fromJson(jsonString, type.getType());
        return entity;
    }

}
