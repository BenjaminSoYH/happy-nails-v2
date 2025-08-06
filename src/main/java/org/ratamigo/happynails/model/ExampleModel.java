//TODO make an model with a string field, int
//TODO make an example POJO
package org.ratamigo.happynails.model;

public class ExampleModel {
    private String name;
    private int value;

    public ExampleModel() {
    }

    // Parameterized constructor
    public ExampleModel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // toString method
    @Override
    public String toString() {
        return "ExampleModel{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
