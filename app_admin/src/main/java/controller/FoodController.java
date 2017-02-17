package controller;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import model.Food;

/**
 * Created by yunho on 2017. 2. 18..
 */
public class FoodController {
    private static final String TAG = FoodController.class.getSimpleName();

    public static void createAdminFood(String adminid,String foodname,long foodprice) {
        Food food = new Food(foodname, foodprice);

        Map<String, Object> userValues = food.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/admin/" + adminid, userValues);

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);
    }


    public static void editAdminFood(String adminid, Food user) {
        Map<String, Object> userValues = user.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/admin/" + adminid, userValues);

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);
    }

}