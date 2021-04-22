package com.antar.bmi_calculator;

import java.io.Serializable;

class BMI_Model implements Serializable {
   static enum Gender{
        MALE,
        FEMALE
    }

    static enum WeightUnits{
       kg,
        lb
    }

    static enum HeightUnits{
       cm,
        ft,
        mt
    }

    public BMI_Model(int age, int weight, int height, Gender gender, WeightUnits weightUnits, HeightUnits heightUnits) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.weightUnits = weightUnits;
        this.heightUnits = heightUnits;
    }

    private int age;
    private int weight;
    private int height;
    private Gender gender;
    private WeightUnits weightUnits;
    private HeightUnits heightUnits;

    public WeightUnits getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(WeightUnits weightUnits) {
        this.weightUnits = weightUnits;
    }

    public HeightUnits getHeightUnits() {
        return heightUnits;
    }

    public void setHeightUnits(HeightUnits heightUnits) {
        this.heightUnits = heightUnits;
    }

    public WeightUnits getUnits() {
        return weightUnits;
    }

    public void setUnits(WeightUnits units) {
        this.weightUnits = units;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Gender getGender() {
        return gender;
    }
}
