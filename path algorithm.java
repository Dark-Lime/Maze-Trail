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
