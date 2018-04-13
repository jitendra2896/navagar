package com.example.smartcity.Util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GraphUtil {
    private static ArrayList<Node> parents;
    private static Node parents2[];
    static ArrayList<Node> path = new ArrayList<>();
    public static void addEdge(Graph graph, Node src, Node dest){
        graph.adjcencyList[src.id].addFirst(dest);
        graph.adjcencyList[dest.id].addFirst(src);
    }

    public static ArrayList<Node> BFS(Graph graph,Node s){
        boolean visited[] = new boolean[graph.V];
        ArrayList<Node> nodes = new ArrayList<>();
        parents = new ArrayList<>(graph.getVertexCount());
        parents2 = new Node[graph.getVertexCount()];
        Log.d("***********YO*********",String.valueOf(parents.size()));
        LinkedList<Node> queue = new LinkedList<>();
        visited[s.id]=true;
        //parents.add(s.id,null);
        parents2[s.id] = null;
        queue.add(s);

        while (queue.size() != 0)
        {
            s = queue.poll();
            nodes.add(s);

            Iterator<Node> i = graph.adjcencyList[s.id].listIterator();
            //Log.d("**********HEL****",String.valueOf(graph.adjcencyList[s.id].size()));
            Log.d("*****BFS****",String.valueOf(s.id));
            while (i.hasNext())
            {
                Node n = i.next();
                if (!visited[n.id])
                {
                    visited[n.id] = true;
                    //parents.add(n.id,temp);
                    parents2[n.id] = s;
                    queue.add(n);
                }
            }
        }
        return nodes;
    }

    public static ArrayList<Node> findPath(Graph graph,Node start, Node end){
        BFS(graph,start);
        for(int i = 0;i<parents2.length;i++){
            if(parents2[i] != null)
                Log.d("*****Parent*****",String.valueOf(i)+": "+String.valueOf(parents2[i].id));
            else
                Log.d("******Parent****",String.valueOf(i)+": "+"NULL");
        }
        //Node n = null;
       //int nn =  n.id;
        path.clear();
        return getPath(start,end);
    }

    public static ArrayList<Node> getPath(Node start,Node end){
        if((start.id == end.id) || (end == null))
            path.add(start);
        else{
            //getPath(graph,start,parents.get(end.id));
            getPath(start,parents2[end.id]);
            path.add(end);
        }
        return path;
    }
}
