package com.example.zig_zag;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class admin_entity extends RealmObject {

    @PrimaryKey
    private String adminid;
    private String adminname, adminpassword;

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
}
