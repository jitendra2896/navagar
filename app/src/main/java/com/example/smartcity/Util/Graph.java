package com.example.smartcity.Util;

import java.util.LinkedList;

public class Graph {
    LinkedList<Node> adjcencyList[];
    int V, E;

    public Graph(int V) {
        this.V = V;
        E = 0;
        adjcencyList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjcencyList[i] = new LinkedList<>();
        }
    }
    public int getVertexCount(){
        return V;
    }
}
