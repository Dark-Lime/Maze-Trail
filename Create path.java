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
