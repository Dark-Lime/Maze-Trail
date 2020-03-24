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
