 public void printNodesConnection() {

        for (var fromNode : connectedNodes.keySet()) {
            var listOfNodes = connectedNodes.get(fromNode);
            if (!listOfNodes.isEmpty())
                System.out.println(fromNode + " is connected to " + listOfNodes);
        }
    }
