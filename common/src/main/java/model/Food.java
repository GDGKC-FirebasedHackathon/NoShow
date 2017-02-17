package model;

/**
 * Created by yunho on 2017. 2. 17..
 */

public class Food {
    public String uid;
    public String name;
    public String imgurl;
    public String price;
    public int count;

    public Food(){}
    public Food(String uid, String name, String price, int count) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.count = count;
    }
    public Food(String uid, String name, String price ) {
        this.uid = uid;
        this.name = name;
        this.price = price;
    }
}
