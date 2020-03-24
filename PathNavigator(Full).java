package com.company;

import java.util.*;

//directed one way or two way graph
public class GraphStructure {
    //this is the node class for the string and a constructor to wrap the string in a node
    private class Node {
        String label;
        String direction;
        int distance;
        Map<String, Node> distanceDirection = new HashMap<>();

        public Node(String label) {
            this.label = label;
        }

        public Node() {
            direction = null;
            distance = 0;
        }

        //this is so I can print the nodes
        @Override
        public String toString() {
            return label;
        }
    }

    //the map stores the graph nodes for the edges hashmap to use
    private Map<String, Node> nodeList = new HashMap<>();

    //this map keeps track of which nodes are connected. It is the edges
    private Map<Node, List<Node>> connectedNodes = new HashMap<>();

    private Stack<Node> route = new Stack<>();

    private LinkedList<Node> deleterList = new LinkedList<>();

    private LinkedList<Node> backTracker = new LinkedList<>();

    //method to add Node to graph and create an array object to connect the Nodes
    public void addNode(String label) {

        if (nodeList.containsKey(label))
            throw new IllegalArgumentException("Duplicates are not allowed");

        var node = new Node(label);
        //this will handle possible duplicates without an if statement
        nodeList.putIfAbsent(label, node);

        connectedNodes.putIfAbsent(node, new ArrayList<>());
    }
    //this method connects nodes together by adding nodes to an array list attached to a node
    public void addDoubleEdge(String from, String to, String direction, int distance) {

        var fromNode = nodeList.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodeList.get(to);

        if (toNode == null)
            throw new IllegalArgumentException();

        if (!direction.matches("west|east|south|north"))
            throw new IllegalArgumentException("For direction type west, east, north, or south");

        if (distance < 0)
            throw new IllegalArgumentException("Distance less than 0");

        if (toNode.distanceDirection.keySet().size() == 4)
            throw new IllegalArgumentException("That node is already full. The max number of connections is 4");

        for (var key : toNode.distanceDirection.values()) {
            if (key.direction.equals(direction))
                throw new IllegalArgumentException("You cannot connect " + fromNode + " in the direction " + direction + " to " + toNode + ". Another node is already connecting " + direction + " to " + toNode);
        }




        toNode.distanceDirection.put(fromNode.label, new Node());
        toNode.distanceDirection.get(fromNode.label).direction = direction;
        toNode.distanceDirection.get(fromNode.label).distance = distance;
        connectedNodes.get(fromNode).add(toNode);

        String w = "west";
        String e = "east";
        String s = "south";
        String n = "north";

        fromNode.distanceDirection.put(toNode.label, new Node());
        fromNode.distanceDirection.get(toNode.label).distance = distance;
        if (direction.equals(e))
            fromNode.distanceDirection.get(toNode.label).direction = w;
        if (direction.equals(w))
            fromNode.distanceDirection.get(toNode.label).direction = e;
        if (direction.equals(s))
            fromNode.distanceDirection.get(toNode.label).direction = n;
        if (direction.equals(n))
            fromNode.distanceDirection.get(toNode.label).direction = s;

        connectedNodes.get(toNode).add(fromNode);
    }

    //this method connects nodes together by adding nodes to an array list attached to a node
    public void addEdge(String from, String to, String direction, int distance) {

        var fromNode = nodeList.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodeList.get(to);

        if (toNode == null)
            throw new IllegalArgumentException();

        if (!direction.matches("west|east|south|north"))
            throw new IllegalArgumentException("For direction type west, east, north, or south");

        if (distance < 0)
            throw new IllegalArgumentException("Distance less than 0");

        toNode.distanceDirection.put(fromNode.label, new Node());
        toNode.distanceDirection.get(fromNode.label).direction = direction;
        toNode.distanceDirection.get(fromNode.label).distance = distance;
        connectedNodes.get(fromNode).add(toNode);
    }

    //this will print out the connected nodes by iterating over all keys and printing the array attached to each key
    public void printNodesConnection() {

        for (var fromNode : connectedNodes.keySet()) {
            var listOfNodes = connectedNodes.get(fromNode);
            if (!listOfNodes.isEmpty())
                System.out.println(fromNode + " is connected to " + listOfNodes);
        }
    }

    //this method deletes a node by removing the node from the connectedNodes values (an array list) with a for loop, then deleting it completely from each hashmap
    public void removeNode(String label) {

        var node = nodeList.get(label);
        if (node == null)
            throw new IllegalArgumentException();

        for (var nodes : connectedNodes.keySet()) {
            connectedNodes.get(nodes).remove(node);
        }

        connectedNodes.remove(node);
        nodeList.remove(label, node);
    }

    //this method removes the node from the array list attached to fromNode
    public void removeEdge(String from, String to) {

        var fromNode = nodeList.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        var toNode = nodeList.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        connectedNodes.get(fromNode).remove(toNode);

    }

    private void routeChecker(Node root, Node endNode, Set<Node> visited) {

        if (connectedNodes.get(root).size() == 0 || visited.containsAll(connectedNodes.get(root)))
            if (root != endNode)
                deleterList.addLast(root);

        int count = 0;
        if (visited.containsAll(connectedNodes.get(root)) || connectedNodes.get(root).size() == 0) {
            for (var node : connectedNodes.values()) {
                if (root == endNode) {
                    break;
                }
                if (node.contains(root)) {
                        for (var key : connectedNodes.keySet()) {
                            if (node.equals(connectedNodes.get(key))) {
                                if (!key.equals(root)) {
                                    if (visited.containsAll(connectedNodes.get(key))) {
                                        if (connectedNodes.get(key).size() >= 2) {
                                            for (var nodes : connectedNodes.get(key)) {
                                                if (deleterList.contains(nodes))
                                                    count++;
                                            }
                                            if (deleterList.containsAll(connectedNodes.get(key)) || count == connectedNodes.get(key).size() - 1) {
                                                deleterList.addLast(key);
                                                if (!backTracker.contains(key)) {
                                                    backTracker.addLast(key);
                                                    routeChecker(key, endNode, visited);
                                                }
                                            }
                                            for (var checker : connectedNodes.get(root)) {
                                                if (!deleterList.contains(checker))
                                                    if (!backTracker.contains(checker)) {
                                                        backTracker.addLast(key);
                                                        routeChecker(checker, endNode, visited);
                                                    }
                                            }
                                        } else if (connectedNodes.get(key).size() < 2) {
                                            deleterList.addLast(key);
                                            if (!backTracker.contains(key)) {
                                                backTracker.addLast(key);
                                                routeChecker(key, endNode, visited);
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    private void routeGetter(Stack<Node> route) {

        Stack<Node> routeBucket = new Stack<>();
        Stack<Node> directionBucket = new Stack<>();
        final int num = route.size();

        for (int i = 0; i < num; i++) {
            if (deleterList.contains(route.peek()))
                route.pop();
            else if (!deleterList.contains(route.peek())) {
                routeBucket.push(route.peek());
                directionBucket.push(route.peek());
                route.pop();
            }
        }
        final int size = routeBucket.size();
        int totalDistance = 0;

        for (int i = 0; i < size; i++) {
            if (i >=1)
                totalDistance += routeBucket.peek().distanceDirection.get(directionBucket.peek().label).distance;

            if (i == 0) {
                System.out.println("First from " + routeBucket.peek() + " go: ");
                routeBucket.pop();
                }
            else if (i == size - 1) {
                    System.out.println("Finally " + routeBucket.peek().distanceDirection.get(directionBucket.peek().label).distance + " " + routeBucket.peek().distanceDirection.get(directionBucket.peek().label).direction + " to " + routeBucket.peek() + "\nA total distance of " + totalDistance);

                }
            else
                {System.out.println(routeBucket.peek().distanceDirection.get(directionBucket.peek().label).distance + " " + routeBucket.peek().distanceDirection.get(directionBucket.peek().label).direction  + " to " + routeBucket.peek() + " then");
                routeBucket.pop();
                directionBucket.pop();
                        }
                    }
                }



    //recursion to traverse the graph with a HashSet implemented from the set interface
    public void pathCalculator(String root, String endNode) {
        if (nodeList.get(root) == null)
            throw new IllegalArgumentException();

        if (connectedNodes.get(nodeList.get(root)).size() == 0)
            throw new IllegalArgumentException(root + " is not connected to anything.");

        if (connectedNodes.get(nodeList.get(endNode)).size() == 0)
            throw new IllegalArgumentException(endNode + " is not connected to anything.");

        pathCalculator(nodeList.get(root), new HashSet<>(), nodeList.get(endNode));

    }

    //this method will do all the recursion work and one parameter will be a set to keep track of the depth traversal without duplicates
    private void pathCalculator(Node root, Set<Node> visited, Node endNode) {

        if (visited.size() == 0) {
            System.out.println("To get from " + root + " to " + endNode);
        }
        route.push(root);
        visited.add(root);
        routeChecker(root, endNode, visited);



        for (var node : connectedNodes.get(root)) {

            if (visited.contains(endNode)) {
                routeGetter(route);
                backTracker.clear();
                break;
            }


            if (!visited.contains(node)) {
                backTracker.clear();
                pathCalculator(node, visited, endNode);
                }
        }
    }
}
