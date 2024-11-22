import java.util.*;

public class MapPlanner {

    // Helper class containing sub-classes for streets and legs
    public static class Helper {

        // Class representing a Street
        public static class Street {
            private final String id;    // Unique identifier for the street
            private final Point start;  // Starting point of the street
            private final Point end;    // Ending point of the street

            // Constructor for the Street class
            public Street(String id, Point start, Point end) {
                this.id = id;
                this.start = start;
                this.end = end;
            }

            // Getter for the street's ID
            public String getId() {
                return id;
            }

            // Getter for the starting point of the street
            public Point getStart() {
                return start;
            }

            // Getter for the ending point of the street
            public Point getEnd() {
                return end;
            }
        }

        // Inner class representing a Leg in a route
        public static class Leg {
            private TurnDirection turn;  // Specifies the direction of the turn whether left or right
            private Street street;       // Street that corresponds to this leg in the route

            // Constructor for the Leg class
            public Leg(TurnDirection turn, Street street) {
                this.turn = turn;
                this.street = street;
            }

            // Getter for the turn direction
            public TurnDirection getTurnDirection() {
                return turn;
            }

            // Getter for the street associated with this leg
            public Street getStreet() {
                return street;
            }
        }
    }

    private int degrees;  // Some degree-related property (e.g., compass direction or angle limit)
    private Location centralDepot;  // Location of the depot
    public List<Helper.Street> streets = new ArrayList<>();  // List to store all streets in the map

    // Constructor for the MapPlanner class
    public MapPlanner(int degrees) {
        this.degrees = degrees;
    }

    // Method to set the depot location based on a given Location object
    public Boolean depotLocation(Location depot) {
        // Loop through all streets to find the one that matches the depot's street ID
        for (Helper.Street d : streets) {
            if (d.getId().equals(depot.getStreetId())) {
                centralDepot = new Location(d.getId(), StreetSide.Right);  // Set depot location on the right side of the street
                return true;  // Successfully set the depot location
            }
        }
        return false;  // Depot location not found
    }

    // Method to add a new street to the list of streets

    public Boolean addStreet(String streetId, Point start, Point end) {
        if (isStreetExists(streetId)) {
            return false;  // Street already exists, so don't add it again
        }
        addNewStreet(streetId, start, end);
        return true;  // Street successfully added
    }

    private boolean isStreetExists(String streetId) {
        // Check if the street already exists in the list
        for (Helper.Street street : streets) {
            if (street.getId().equals(streetId)) {
                return true;
            }
        }
        return false;
    }

    private void addNewStreet(String streetId, Point start, Point end) {
        // Create and add the new street to the list of streets
        Helper.Street newStreet = new Helper.Street(streetId, start, end);
        streets.add(newStreet);
    }

    /*public Boolean addStreet(String streetId, Point start, Point end) {
        // Check if the street already exists in the list
        for (Helper.Street street : streets) {
            if (street.getId().equals(streetId)) {
                return false;  // Street already exists, so don't add it again
            }
        }

        // Create a new street object with the given parameters
        Helper.Street street = new Helper.Street(streetId, start, end);

        // Add the new street to the list of streets
        streets.add(street);
        return true;  // Street successfully added
    }*/

    // Method to find the street that is the furthest from the depot
    public String FindfurthestStreet(List<Helper.Street> streets, Location depot) {
        double maxDistance = 0.0;  // Variable to track the maximum distance
        String furthestStreet = null;  // ID of the furthest street

        Point depotStart = null;  // Start point of the depot's street
        Point depotEnd = null;    // End point of the depot's street

        // Loop through all streets to find the depot's street and calculate distances
        for (Helper.Street streetLocation : streets) {

            // If the current street is the depot's street, save its start and end points
            if (streetLocation.getId().equals(depot.getStreetId())) {
                depotStart = streetLocation.getStart();
                depotEnd = streetLocation.getEnd();
            }

            // Calculate the Euclidean distance from the depot to the current street's end
            double distance = Math.sqrt(Math.pow(streetLocation.getEnd().getX() - depotStart.getX(), 2)
                    + Math.pow(streetLocation.getEnd().getY() - depotEnd.getY(), 2));

            // Update the furthest street if this distance exceeds the current max distance
            if (distance > maxDistance) {
                maxDistance = distance;
                furthestStreet = streetLocation.getId();
            }
        }
        return furthestStreet;  // Return the ID of the furthest street
    }

    // Wrapper method to find the street that is furthest from the depot
    public String furthestStreet() {
        return FindfurthestStreet(streets, centralDepot);
    }

    // Method to compute the Euclidean distance between two points
    public double distance(Point p, Point q) {
        return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + Math.pow(q.getY() - p.getY(), 2));
    }

    // List to store the route as a sequence of legs
    List<Helper.Leg> route = new ArrayList<>();

    // Method to compute a route to the destination without making any left turns
    public Route routeNoLeftTurn(Location destination) throws RuntimeException {
        String dest = destination.getStreetId();  // Get the destination street ID
        Queue<Helper.Street> queue = new LinkedList<>(streets);  // Initialize a queue with all streets
        List<Location> visited = new ArrayList<>();  // List to track visited locations

        // Loop through the streets to find the depot's street and mark it as visited
        for (Helper.Street str : streets) {
            if (str.getId().equals(centralDepot.getStreetId())) {
                visited.add(centralDepot);  // Mark the depot as visited
            }
        }
        return null;
    }
}
