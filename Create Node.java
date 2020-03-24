//method to add Node to graph and create an array object to connect the Nodes
    public void addNode(String label) {

        if (nodeList.containsKey(label))
            throw new IllegalArgumentException("Duplicates are not allowed");

        var node = new Node(label);
        //this will handle possible duplicates without an if statement
        nodeList.putIfAbsent(label, node);

        connectedNodes.putIfAbsent(node, new ArrayList<>());
    }
    
