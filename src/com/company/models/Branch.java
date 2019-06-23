package com.company.models;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    public String name;
    public Node nodeA;
    public Node nodeB;
    public float current;
    public List<Resistor> resistors = new ArrayList<>();
    public List<Source> sources = new ArrayList<>();
}
