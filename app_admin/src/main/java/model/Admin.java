package model;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.LoginController;

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

    public Admin(String name, String location) {
        this.uid = LoginController.getUid();
        this.name = name;
        this.location = location;
    }
    public Admin(String name, String location,ArrayList<Food> foods) {
        this.uid = LoginController.getUid();
        this.name = name;
        this.foods = foods;
        this.location = location;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> foodhash = new HashMap<>();

        result.put("name", name);
        result.put("location", location);

        for (Food food : foods){
            foodhash.put(food.getKey(),food.toMap());
        }
        result.put("foods", foodhash);
        return result;
    }
    public String getKey(){
        return uid;
    }
    public static Admin parseSnapshot(DataSnapshot snapshot){
        Admin admin = new Admin();
        admin.name = (String) snapshot.child("name").getValue();
        admin.location = (String) snapshot.child("location").getValue();
        admin.foods = Food.parseSnapshotList(snapshot.child("foods"));

        return admin;
    }
}
