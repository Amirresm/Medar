package com.company;

import com.company.models.*;

import java.util.Scanner;

public class Main {
    static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        Circuit circuit = new Circuit();
        circuit.fillData(input);
        circuit.printData();
    }
}
