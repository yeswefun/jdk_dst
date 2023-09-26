package com.z.graph_00;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
邻接矩阵
 */
public class AdjList {

    //Vertex
    private int v;

    //Edge
    private int e;

    //数组的元素 为 LinkedList<Integer>
    //链表数组
    private LinkedList<Integer>[] adj;

    public AdjList(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            v = scanner.nextInt();
            if (v < 0) {
                throw new IllegalArgumentException("v must be non-negative");
            }
            e = scanner.nextInt(); // e -> row
            if (e < 0) {
                throw new IllegalArgumentException("e must be non-negative");
            }
//            adj = new LinkedList<Integer>[v]; //error
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }

            for (int i = 0; i < e; i++) {
                int x = scanner.nextInt();
                validateVertex(x);
                int y = scanner.nextInt();
                validateVertex(y);
                //无向图, 简单图: 没有 自环边，没有 平行边
                if (x == y) { // 自环边
                    throw new IllegalArgumentException("self loop is detected");
                }
                if (adj[x].contains(y)) { // 平行边, O(V), max == V-1
                    throw new IllegalArgumentException("parallel edges are detected");
                }
                adj[x].add(y);
                adj[y].add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= v) {
            throw new IllegalArgumentException("vertex: " + v + " is invalid");
        }
    }

    public int V() {
        return v;
    }

    public int E() {
        return e;
    }

    public boolean hasEdge(int x, int y) {
        validateVertex(x);
        validateVertex(y);
        return adj[x].contains(y);
    }

    /*
        与 vertex 相连的那些顶点
     */
    public List<Integer> adj(int vertex) {
        validateVertex(vertex);
        return adj[vertex];
    }

    /*
        顶点的度: 顶点有多少条领边
     */
    public int degree(int vertex) {
        //return adj[vertex]; // 需要验证 vertex 是否合法
        return adj(vertex).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", v, e));
        for (int i = 0; i < v; i++) {
            sb.append(String.format("%d : ", i));
            for (Integer vertex : adj[i]) {
                sb.append(String.format("%d  ", vertex));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /*
        只处理简单图
     */
    public static void main(String[] args) {
        //此处的路径相对项目根路径, E:\k\dst
        AdjList adjList = new AdjList("data/g.txt");
        System.out.println(adjList);
    }
}
