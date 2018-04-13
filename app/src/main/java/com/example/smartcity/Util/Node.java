package com.example.smartcity.Util;

public class Node{
    public int id;
    public String name;
    public String vName;
    public Node(int id,String name){
        this.id = id;
        this.name = name;
    }

    public Node(int id,String name,String vName){
        this(id,name);
        this.vName = vName;
    }
}