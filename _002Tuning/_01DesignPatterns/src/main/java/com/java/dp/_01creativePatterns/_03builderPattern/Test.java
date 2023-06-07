package com.java.dp._01creativePatterns._03builderPattern;

public class Test {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        vegMeal.showItems();
        vegMeal.getCost();

    }
}
