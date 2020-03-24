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
