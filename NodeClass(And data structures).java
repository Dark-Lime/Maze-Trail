public class GraphStructure {

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
        
}
