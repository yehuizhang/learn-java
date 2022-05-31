package com.yehuizhang.design_patterns.solid.openclosed;

import com.yehuizhang.design_patterns.solid.openclosed.specification.Specification;

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class SizeSpecification extends Specification<Product> {
    private final Size size;


    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}