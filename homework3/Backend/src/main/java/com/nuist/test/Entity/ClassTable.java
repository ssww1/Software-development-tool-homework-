package com.nuist.test.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class ClassTable {

    @Id
    private int cid;

    @Column(nullable = false)
    private String classname;

    // Getters and Setters
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
