package com.yehuizhang.design_patterns.solid.openclosed.specification;

public abstract class Specification<T> {
    public abstract boolean isSatisfied(T item);
}
