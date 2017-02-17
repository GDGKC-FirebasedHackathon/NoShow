package model;

import java.util.ArrayList;

/**
 * Created by yunho on 2017. 2. 17..
 */

public class Admin {
    public String uid;
    public String name;
    public String imgurl;
    public String location;
    public double lat;
    public double lon;
    public ArrayList<Food> foods = new ArrayList<>();

    public Admin() {}

    public Admin(String uid, String name, String location) {
        this.uid = uid;
        this.name = name;
        this.location = location;
    }
    public Admin(String uid, String name, String location,ArrayList<Food> foods) {
        this.uid = uid;
        this.name = name;
        this.foods = foods;
        this.location = location;
    }
}
