package com.example.sagar.internproject.model;
/**
 * Created by sagar on 11/5/2017.
 */

public class UserInfo {

    private Long id;

    private  String name;

    public UserInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public UserInfo() {
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }
}
