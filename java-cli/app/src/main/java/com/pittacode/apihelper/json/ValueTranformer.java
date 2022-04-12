package com.pittacode.apihelper.json;

import com.google.gson.JsonElement;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ValueTranformer extends UnaryOperator<JsonElement> {
}
