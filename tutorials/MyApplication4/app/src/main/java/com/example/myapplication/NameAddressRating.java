package com.example.myapplication;

public class NameAddressRating  implements Comparable<NameAddressRating> {
    public String name;
    public String address;
    public float rating;

    public NameAddressRating(String name, String address, float rating)
    {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    @Override
    public int compareTo(NameAddressRating o) {
        return Float.compare(this.rating, o.rating);
    }
}
