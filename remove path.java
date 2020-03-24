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
