package com.fruitday.boot.domain.valid;

import com.fruitday.boot.config.valid.DemoValid;

public class WorkTest {

    @DemoValid(max = 2)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
