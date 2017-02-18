package controller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import model.Admin;
import model.Food;

/**
 * Created by yunho on 2017. 2. 18..
 */

public class AdminController {
    private static String userUid;
    public static void createAdmin(String name,String location){
        Admin admin = new Admin(name,location);

        Map<String, Object> userValues = admin.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        userUid = LoginController.getUid();
        childUpdates.put("/admin/" + userUid, userValues);
    }
    public static void editAdmin(String name,String location){
        createAdmin(name,location);
    }
    public static void createAdminFood(String foodname,long foodprice) {
        Food food = new Food(foodname, foodprice);

        Map<String, Object> userValues = food.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        userUid = LoginController.getUid();

        childUpdates.put("/admin/" + userUid+"/foods", userValues);

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);
    }
    public static void editAdminFood(String foodname,long foodprice) {
        createAdminFood(foodname,foodprice);
    }
}
