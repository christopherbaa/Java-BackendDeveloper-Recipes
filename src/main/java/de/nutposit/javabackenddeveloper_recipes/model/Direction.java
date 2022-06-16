package de.nutposit.javabackenddeveloper_recipes.model;

import javax.persistence.Embeddable;

@Embeddable
public class Direction {

    private String direction;

    public Direction(String direction) {
        this.direction = direction;
    }

    public Direction() {
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
