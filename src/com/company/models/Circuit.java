package com.company.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Circuit {
    public List<Node> nodes = new ArrayList<>();
    public List<Branch> branches = new ArrayList<>();

    public void printData () {
        for (Node n : this.nodes) {
            System.out.println("node " + n.name);
        }
        for (Branch b : this.branches) {
            System.out.println("Branch "+ b.name + ":");
            System.out.print("(" + b.nodeA.name + " )");
            for (Source s : b.sources)
                System.out.print("---(" + s.name + "=" + s.value + ")");
            for (Resistor r : b.resistors)
                System.out.print("---(" + r.name + "=" + r.value + ")");

            System.out.println("---(" + b.nodeB.name + ")");
        }
    }

    public void fillData(Scanner input){
        System.out.println("Enter node count: ");
        int nodeCount = input.nextInt();
        input.skip("\n");
        for (int i = 0; i < nodeCount; i++) {
            System.out.println("Enter node " + (i + 1) + "/" + nodeCount + " name: ");
            Node node = new Node();
            node.name = input.nextLine();
            this.nodes.add(node);
        }
        for (Node n : this.nodes) {
            System.out.println("node " + n.name);
        }
        while (true) {
            System.out.println("Do you want to add a new branch?(Y/N)");
            String response = input.nextLine();
            if (response.toLowerCase().equals("n"))
                break;
            if (!response.toLowerCase().equals("y"))
                continue;
            System.out.println("Enter branch name: ");
            String branchName = input.nextLine();
            System.out.println("Enter first connected node name: ");
            String nodeAName = input.nextLine();
            System.out.println("Enter second connected node name: ");
            String nodeBName = input.nextLine();
            final Branch branch = new Branch();
            branch.name = branchName;
            branch.nodeA = null;
            branch.nodeB = null;
            this.nodes.forEach(node -> {
                if (node.name.equals(nodeAName))
                    branch.nodeA = node;
                if (node.name.equals(nodeBName))
                    branch.nodeB = node;
            });
            boolean isInvalid = false;
            if (branch.nodeA == null) {
                System.out.println("First node is invalid.");
                isInvalid = true;
            }
            if (branch.nodeB == null) {
                System.out.println("Second node is invalid.");
                isInvalid = true;
            }
            if (nodeAName.toLowerCase().equals(nodeBName.toLowerCase())) {
                System.out.println("First node and second node can't be the same.");
                isInvalid = true;
            }
            if (isInvalid)
                continue;
            System.out.println("Do you want to add a source to this branch?(Y/N)");
            response = input.nextLine();
            if (response.toLowerCase().equals("y")) {
                System.out.println("Enter name of the source: ");
                String sourceName = input.nextLine();
                System.out.println("Enter value of the source: ");
                float sourceValue = input.nextFloat();
                input.skip("\n");
                Source source = new Source();
                source.name = sourceName;
                source.value = sourceValue;
                branch.sources.add(source);
            }
            System.out.println("Enter resistor count: ");
            int resistorCount = input.nextInt();
            input.skip("\n");
            for (int i = 0; i < resistorCount; i++) {
                System.out.println("Enter resistor " + (i + 1) + "/" + resistorCount + " name: ");
                String resName = input.nextLine();
                System.out.println("Enter resistor " + (i + 1) + "/" + resistorCount + " value: ");
                float resValue = input.nextFloat();
                input.skip("\n");
                Resistor resistor = new Resistor();
                resistor.name = resName;
                resistor.value = resValue;
                branch.resistors.add(resistor);
            }
            this.branches.add(branch);
        }
    }
}
