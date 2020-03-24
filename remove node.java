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
