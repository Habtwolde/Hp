package com.example.mhts.hp.Account.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

public class UserModel implements Serializable {

    @NonNull
    private String uid;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @Nullable
    private String password; //This should be hashed
    @Nullable
    private String phoneNumber;
    @NonNull
    private String imageLink;
    @NonNull
    private Boolean isVendor;

    public UserModel(@NonNull String uid , @NonNull String name, @NonNull String email, @Nullable String password, @Nullable String phoneNumber, @Nullable String imageLink, @NonNull Boolean isVendor) {
        this.name = name;
        this.uid = uid;
        this.email = email;

        if (password != null)
            this.password = password;

        this.phoneNumber = phoneNumber;

        if (imageLink != null || !imageLink.isEmpty())
            this.imageLink = imageLink;

        this.isVendor = isVendor;
    }

    @NonNull
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(@NonNull String imageLink) {
        this.imageLink = imageLink;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + " , " + email + " , " + password + " , " + phoneNumber + " , " + imageLink + "]";
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @NonNull
    public Boolean getVendor() {
        return isVendor;
    }

    public void setVendor(@NonNull Boolean vendor) {
        isVendor = vendor;
    }
}
