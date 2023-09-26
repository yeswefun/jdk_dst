package com.z.graph_00;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
邻接矩阵
 */
public class AdjMatrix {

    //Vertex
    private int v;

    //Edge
    private int e;

    private int[][] adj;

    public AdjMatrix(String fileName) {
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
            adj = new int[v][v];
            for (int i = 0; i < e; i++) {
                int x = scanner.nextInt();
                validateVertex(x);
                int y = scanner.nextInt();
                validateVertex(y);
                //无向图, 简单图: 没有 自环边，没有 平行边
                if (x == y) { // 自环边
                    throw new IllegalArgumentException("self loop is detected");
                }
                if (adj[x][y] == 1) { // 平行边
                    throw new IllegalArgumentException("parallel edges are detected");
                }
                adj[y][x] = adj[x][y] = 1;
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
        return adj[x][y] == 1;
    }

    /*
        与 vertex 相连的那些顶点
     */
    public List<Integer> adj(int vertex) {
        validateVertex(vertex);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (adj[vertex][i] == 1) {
                list.add(i);
            }
        }
        return list;
    }

    /*
        顶点的度: 顶点有多少条领边
     */
    public int degree(int vertex) {
        return adj(vertex).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", v, e));
        //v X v
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                sb.append(String.format("%d  ", adj[i][j]));
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
        AdjMatrix adjMatrix = new AdjMatrix("data/g.txt");
        System.out.println(adjMatrix);
    }
}
