package com.yehuizhang.design_patterns.solid.openclosed.filter;

import com.yehuizhang.design_patterns.solid.openclosed.specification.Specification;

import java.util.List;
import java.util.stream.Stream;

public class Filter<T> {
    public Stream<T> filter(List<T> items, List<Specification<T>> specs) {
        Stream<T> itemStream = items.stream();
        for (Specification<T> spec : specs) {
            itemStream = itemStream.filter(spec::isSatisfied);
        }
        return itemStream;
    }

    public Stream<T> filter(List<T> items, Specification<T> spec) {
        return items.stream().filter(spec::isSatisfied);
    }
}