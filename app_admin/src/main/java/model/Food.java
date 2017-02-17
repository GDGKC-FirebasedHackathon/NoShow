package model;

import com.google.common.hash.Hasher;
import com.google.firebase.database.DataSnapshot;

import org.apache.commons.codec.digest.Md5Crypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yunho on 2017. 2. 17..
 */

public class Food {
    public String uid;
    public String name;
    public String imgurl;
    public long price;
    public long count;

    public Food(){}
    public Food(String name, long price){

        this.uid = name+price;
        this.name = name;
        this.price = price;
        this.count = 1;
    }
    public Food(String uid, String name, long price, int count) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.count = count;
    }
    public Food(String uid, String name, long price ) {
        this.uid = uid;
        this.name = name;
        this.price = price;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", name);
        result.put("price", price);
        result.put("count", count);
        return result;
    }
    public String getKey(){
        return uid;
    }
    public static Food parseSnapshot(DataSnapshot snapshot){
        Food food = new Food();
        food.uid = (String) snapshot.getKey();
        food.name = (String) snapshot.child("name").getValue();
        food.price = (long) snapshot.child("location").getValue();
        food.count = (long) snapshot.child("count").getValue();

        return food;
    }
    public static ArrayList<Food> parseSnapshotList(DataSnapshot dataSnapshot){
        ArrayList<Food> foods = new ArrayList<>();
        for(DataSnapshot child : dataSnapshot.getChildren()) {
            Food food = Food.parseSnapshot(child);
            foods.add(food);
        }
        return foods;
    }

}
