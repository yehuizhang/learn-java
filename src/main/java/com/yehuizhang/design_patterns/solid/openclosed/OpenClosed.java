package com.yehuizhang.design_patterns.solid.openclosed;

import com.yehuizhang.design_patterns.solid.openclosed.filter.Filter;

import java.util.List;

/**
 * Open-Closed Principle (OCP)
 * Open for extension, but closed for modification.
 * In this example, filter and specifications no longer need to be modified when new combination of specifications comes in.
 */
public class OpenClosed {

    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.RED, Size.SMALL);
        Product mango = new Product("Mango", Color.YELLOW, Size.MEDIUM);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product iceberg = new Product("Iceberg", Color.BLUE, Size.LARGE);
        Product whale = new Product("Whale", Color.BLUE, Size.LARGE);
        Product mountain = new Product("Mountain", Color.GREEN, Size.HUGE);

        List<Product> products = List.of(apple, mango, tree, iceberg, whale, mountain);

        Filter<Product> productFilter = new Filter<>();
        System.out.println("Yellow products:");
        productFilter.filter(products, new ColorSpecification(Color.YELLOW)).forEach(p -> System.out.println(p.name + " is Yellow"));

        System.out.println("Blue and Large products");
        productFilter.filter(products, List.of(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE))).forEach(p -> System.out.println(p.name + " is Blue and Large"));


    }
}
