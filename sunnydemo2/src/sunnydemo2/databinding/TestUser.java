package sunnydemo2.databinding;

import java.io.Serializable;

/**
 * Created by sunny on 2016/3/14.
 * Annotion:
 */
public class TestUser implements Serializable{

    private String userName;
    private String age;
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
