package com.example.rlp;

import java.util.Collection;
import java.util.Map;

import lombok.Builder;

@Builder
public class Raw<V> implements Container<V> {
    Class<V> rawType;

    Raw() {
    }

    public Raw(Class<V> rawType) {
        this.rawType = rawType;
    }

    public ContainerType getType() {
        return ContainerType.RAW;
    }

    @Override
    public Class<V> asRaw() {
        return rawType;
    }

    @Override
    public CollectionContainer<? extends Collection<V>, V> asCollection() {
        throw new RuntimeException("not a collection container");
    }

    @Override
    public MapContainer<? extends Map<?, V>, ?, V> asMap() {
        throw new RuntimeException("not a map container");
    }
}
