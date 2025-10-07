package edu.note.algorithm.graphs;

import java.util.Arrays;

/**
 * MyUnionFind
 * 
 * @author Jacky Lee
 * @date 2024/03/16
 */
public class MyUnionFind {
    int[] union;
    int[] rank;

    /**
     * capacity 容量
     * 
     * @param capacity
     */
    public MyUnionFind(int capacity) {
        union = new int[capacity + 1];
        rank = new int[capacity + 1];
        for (int i = 0; i < capacity + 1; i++) {
            union[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 找到 x 的祖先结点，并进行路径压缩
     * 
     * @param x
     * @return
     */
    public int find(int x) {
        int px = x;
        // 找到 x 的父结点 px
        while (px != union[px])
            px = union[px];
        // 现在 px 是根节点，进行路径压缩
        while (x != px) {
            int tmp = union[x];
            union[x] = px;
            x = tmp;
        }
        return px;

    }

    /**
     * 合并两个结点
     * 
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int px = find(x), py = find(y);
        // 如果相等，则无需操作
        if (px == py)
            return;
        // 小秩 向 大秩 合并
        if (rank[px] < rank[py]) {
            union[px] = union[py];
            rank[py] = rank[px] + 1;
        } else {
            union[py] = union[px];
            rank[px] = rank[py] + 1;
        }
    }

    /**
     * 判断两个结点是否相连
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    @Override
    public String toString() {
        return Arrays.toString(union);
    }

    public static void main(String[] args) {
        MyUnionFind uf = new MyUnionFind(6);
        uf.union(4, 2);
        System.out.println(uf.isConnected(1, 2));
        uf.union(1, 3);
        uf.union(5, 3);
        uf.union(1, 5);
        System.out.println(uf);
    }

}