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
