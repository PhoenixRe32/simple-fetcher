package com.pittacode.apihelper.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class ObjectFlattener {

    public JsonObject flatten(JsonObject jsonObject) {
        final var flat = new JsonObject();
        jsonObject.entrySet()
                  .stream()
                  .flatMap(field -> toStreamOfEntries(field, ""))
                  .toList()
                  .forEach(e -> flat.add(e.getKey(), e.getValue()));
        return flat;
    }

    private Stream<Map.Entry<String, JsonElement>> toStreamOfEntries(Map.Entry<String, JsonElement> field,
                                                                     String prefix) {
        if (not(object()).test(field)) {
            return Stream.of(new SimpleImmutableEntry<>(prefix + field.getKey(), field.getValue()));
        }

        return field.getValue()
                    .getAsJsonObject()
                    .entrySet()
                    .stream()
                    .flatMap(e -> toStreamOfEntries(e, buildPrefix(prefix, field.getKey())));
    }

    private String buildPrefix(String currentPrefix, String fieldKey) {
        if (currentPrefix.isEmpty()) {
            return fieldKey + ".";
        }
        return currentPrefix + fieldKey + ".";
    }

    private Predicate<Map.Entry<String, JsonElement>> object() {
        return e -> e.getValue().isJsonObject();
    }
}
