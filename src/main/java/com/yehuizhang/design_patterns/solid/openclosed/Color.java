package com.yehuizhang.design_patterns.solid.openclosed;

import com.yehuizhang.design_patterns.solid.openclosed.specification.Specification;

enum Color {
    RED, GREEN, BLUE, YELLOW
}

class ColorSpecification extends Specification<Product> {
    private final Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product p) {
        return p.color == color;
    }
}