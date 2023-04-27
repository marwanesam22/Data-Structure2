package com.company;

public class Pair implements Comparable<Pair> {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int compareTo(Pair other) {
        if (this.first != other.first) {
            return Integer.compare(this.first, other.first);
        }
        return Integer.compare(this.second, other.second);
    }
}