package com.sunny;

import com.ib.controller.ApiController;
import java.util.ArrayList;

public class ConnectionHandler implements ApiController.IConnectionHandler {

    @Override
    public void connected() {
        System.out.println("Connected - " + System.currentTimeMillis());
    }

    @Override
    public void disconnected() {
        System.out.println("Disconnected - " + System.currentTimeMillis());
    }

    @Override
    public void accountList(ArrayList<String> list) {
        System.out.println("Printing account list? " + list);
    }

    @Override
    public void error(Exception e) {
        System.out.println("Error:  " + e.getMessage());
        System.out.println(e.getStackTrace());
    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {
        System.out.println("Message{id= " + id + ", errorCode= " + errorCode + ", errorMsg= " + errorMsg);
    }

    @Override
    public void show(String string) {
        System.out.println("Showing something: " + string);
    }
}
