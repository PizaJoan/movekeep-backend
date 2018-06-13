package com.movekeep.api.movekeepapi.parser;

public interface Parser<T> {

    T parse(T toParse);
}
