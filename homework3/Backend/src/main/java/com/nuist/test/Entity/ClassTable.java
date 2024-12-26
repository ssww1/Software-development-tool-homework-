package com.nuist.test.Entity;

import javax.persistence.*;

@Entity
@Table(name="class")
public class ClassTable {

    @Id
    private int cid;

    @Column(name="classname")
    private String classname;

    @OneToMany(mappedBy = "classTable")


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
