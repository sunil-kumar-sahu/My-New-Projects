package com.bipros.functionalities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A directed graph using
// adjacency list representation
public class GraphRepresent {

    // No. of vertices in graph,v-vertices
    private int v;
    static ArrayList<Integer[]> arr=new ArrayList<>();

    // adjacency list
    private ArrayList<Integer>[] adjList;

    // Constructor
    public GraphRepresent(int vertices)
    {

        this.v = vertices;
        initAdjList();
    }
    public ArrayList<Integer[]> getAllPaths(int s , int e){ //s-start,e-end
        printAllPaths(s , e);
        return arr;
    }
  
    private void initAdjList()
    {
        adjList = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // add edge from u to v
    public void addEdge(int u, int v)
    {
        // Add v to u's list.
        adjList[u].add(v);
    }
    public void printAllPaths(int s, int d) //s-strat,d-destination
    {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);

        // Call recursive utility
       // ArrayList<List<Integer>> arr=new ArrayList<>();//array of list of adjacency list representation
        printAllPathsUtil(s, d, isVisited, pathList);

    }

    // A recursive function to print all paths from 'u' to 'd'.
    // isVisited[] keeps track of vertices in current path.
    // localPathList<> stores actual vertices in the current path
    private void printAllPathsUtil(Integer u, Integer d,
                                   boolean[] isVisited,
                                   List<Integer> localPathList)//This function is used for dfs traversal
    {

        if (u.equals(d)) {
//            System.out.println(localPathList);
            // if match found then no need to traverse more till depth
            Integer[] ar2 = new Integer[localPathList.size()];
            for(int i=0;i<localPathList.size();i++){
                ar2[i] = localPathList.get(i);
            }
            arr.add(ar2);
            return;
        }

        // Mark the current node as visited ,if the node is visited then print it and move to the unvisited nodes
        isVisited[u] = true;

        // Recur for all the vertices adjacent to current vertex
        for (Integer i : adjList[u]) {
            if (!isVisited[i]) { //it will check the ith index visited nodes next node
                // store current node in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node in path[]
                localPathList.remove(i);
            }
        }

        // Mark the current node as unvisited
        isVisited[u] = false;

    }

    // Driver program
    public static ArrayList<Integer[]> getBusData(int s , int d)
    {
        //Add the size of  graph 
        GraphRepresent g = new GraphRepresent(14);
        
        //addEdge is used to add directed graph where 
        //it show which path is connected
      /*  g.addEdge(1, 3);//add edge into the graph(This add edge is for 2nd TestCase)
        g.addEdge(3, 5);
        g.addEdge(5, 7);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 6);
        g.addEdge(6, 7);
        g.addEdge(6, 5);*/
        g.addEdge(1, 2);
        g.addEdge(6, 2);
        g.addEdge(2, 7);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(7, 8);
        g.addEdge(8, 4);
        g.addEdge(10,9);
        g.addEdge(9,8);
        g.addEdge(11,8);
        g.addEdge(8,12);
        g.addEdge(13, 7);
        g.addEdge(7, 3);
        System.out.println("These are all different paths from " + s + " to " + d);
       
        
        //This Method is used to print all the path present between source to destination
        g.printAllPaths(s,d);

      //  System.out.println(arr);//
        return arr;
    }
}
