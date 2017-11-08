package com.example.sagar.internproject.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sagar on 11/5/2017.
 */
@Entity
public class userInfo {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "NAME")
    private  String name;
    @Generated(hash = 1692793437)
    public userInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1574069124)
    public userInfo() {
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
