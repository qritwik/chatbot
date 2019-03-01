package com.library.apple.scify;

public class ModelChat {


    int user_type;
    String mess;


    public ModelChat() {
    }

    public ModelChat(int user_type, String mess) {
        this.user_type = user_type;
        this.mess = mess;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }


}
