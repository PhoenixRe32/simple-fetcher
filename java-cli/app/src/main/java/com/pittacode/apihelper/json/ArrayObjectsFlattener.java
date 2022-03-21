package com.pittacode.apihelper.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.stream.StreamSupport;

public class ArrayObjectsFlattener {

    private final ObjectFlattener objectFlattener;

    public ArrayObjectsFlattener(ObjectFlattener objectFlattener) {
        this.objectFlattener = objectFlattener;
    }

    public JsonArray flattenObjectsIn(JsonArray jsonArray) {
        final var arrayWithFlattenedObjects = new JsonArray();
        StreamSupport.stream(jsonArray.spliterator(), false)
                     .map(JsonElement::getAsJsonObject)
                     .map(objectFlattener::flatten)
                     .forEach(arrayWithFlattenedObjects::add);
        return arrayWithFlattenedObjects;
    }
}
