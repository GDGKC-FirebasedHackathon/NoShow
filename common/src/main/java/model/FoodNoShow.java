package model;

import java.util.ArrayList;

/**
 * Created by yunho on 2017. 2. 17..
 */

public class FoodNoShow {
    public String id;
    public Admin admin;
    public String name;
    public String imgurl;
    public int totalPrice;
    public String userPhone;
    public ArrayList<Food> foods = new ArrayList<>();
    public FoodNoShow(){}
    public FoodNoShow(Admin admin, String userPhone, int totalPrice, String name) {
        this.admin = admin;
        this.userPhone = userPhone;
        this.totalPrice = totalPrice;
        this.name = name;
        this.id = id;
    }
}
