package com.pittacode.apihelper.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class NullTranformer implements ValueTranformer {
    @Override
    public JsonElement apply(JsonElement jsonElement) {
        return jsonElement.isJsonNull()
                ? new JsonPrimitive("")
                : jsonElement;
    }
}
