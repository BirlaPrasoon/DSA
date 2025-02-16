package designPatterns.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeBox implements Box {
    List<Box> children = new ArrayList<>();

    CompositeBox(Box ...boxes) {
        this.children.addAll(Arrays.asList(boxes));
    }

    @Override
    public double calculatePrice() {
        return this.children.stream().mapToDouble(Box::calculatePrice).sum();
    }
}
