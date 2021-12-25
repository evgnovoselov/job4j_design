package ru.job4j.ood.ocp.bad3;

public class Cat {
    private String name;
    private int qtyPaw = 4;
    private boolean hasTail = true;
    private String type = "Кот";

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, int qtyPaw, boolean hasTail) {
        this.name = name;
        this.qtyPaw = qtyPaw;
        this.hasTail = hasTail;
    }

    public String say() {
        return "мяяяяуууу";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQtyPaw() {
        return qtyPaw;
    }

    public void setQtyPaw(int qtyPaw) {
        this.qtyPaw = qtyPaw;
    }

    public boolean isHasTail() {
        return hasTail;
    }

    public void setHasTail(boolean hasTail) {
        this.hasTail = hasTail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
