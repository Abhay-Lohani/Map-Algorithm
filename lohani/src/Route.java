import java.util.List;
import java.util.ArrayList;

/**
 * Define a route to travel in the map. It's a sequence of turns and streets in the city map.
 * The first leg of a route is leg 1.
 */

public class Route {

    private static List<Helper.Leg> legs;
    private List<Helper.Street> tempStreets = new ArrayList<>();

    // Constructor to create an empty route
    public Route() {
        this.legs = new ArrayList<>();
    }

    // Constructor to create a route with a given list of legs
    public Route(List<Helper.Leg> legs) {
        this.legs = legs;
    }

    /**
     * Grow a Route by adding one step (called a "leg") of the route at a time.
     * This method adds one more leg to an existing route.
     *
     * @param turn -- from the current route, what kind of turn do you make onto the next leg
     * @param streetTurnedOnto -- the street id onto which the next leg of the route turns
     * @return -- true if the leg was added to the route.
     */
    public Boolean appendTurn(TurnDirection turn, Helper.Street streetTurnedOnto) {
        if (turn == null || streetTurnedOnto == null) {
            return false;
        }

        Helper.Leg newLeg = new Helper.Leg(turn, streetTurnedOnto);
        legs.add(newLeg);

        Point start = null;
        Point end = null;

        for (Helper.Street st : tempStreets) {
            if (st.getId().equals(streetTurnedOnto.getId())) {
                start = st.getStart();
                end = st.getEnd();
            }
        }

        Helper.Street newStreet = new Helper.Street(streetTurnedOnto.getId(), start, end);
        tempStreets.add(newStreet);
        return true;
    }

    /**
     * Given a route, report the street of the given leg number of the route.
     *
     * @param legNumber -- the leg number for which we want the next street.
     * @return -- the street id of the next leg, or null if there is an error.
     */
    public static String turnOnto(int legNumber) {
        if (legNumber < 1 || legNumber > legs.size()) {
            return null;
        }

        Helper.Leg leg = legs.get(legNumber - 1);
        return leg.getStreet().getId();
    }

    /**
     * Given a route, report the turn direction that initiates the given leg number of the route.
     *
     * @param legNumber -- the leg number for which we want the next turn.
     * @return -- the turn direction for the leg, or null if there is an error.
     */
    public static TurnDirection turnDirection(int legNumber) {
        if (legNumber < 1 || legNumber > legs.size()) {
            return null;
        }

        Helper.Leg leg = legs.get(legNumber - 1);
        return leg.getTurnDirection();
    }

    /**
     * Report how many legs exist in the current route.
     *
     * @return -- the number of legs in this route.
     */
    public static int legs() {
        return legs.size();
    }

    public void streetPassLength(ArrayList<Helper.Street> tempS) {
        tempStreets = tempS;
    }

    /**
     * Report the length of the current route in metres by Euclidean distance.
     *
     * @return -- the length of the current route.
     */
    public Double length() {
        double length = 0.0;

        for (Helper.Leg leg : legs) {
            Helper.Street street = leg.getStreet();
            double legLength = Math.sqrt(Math.pow(street.getEnd().getX() - street.getStart().getX(), 2)
                    + Math.pow(street.getEnd().getY() - street.getStart().getY(), 2));

            if (street.getId().equals(legs.get(0).getStreet().getId()) ||
                    street.getId().equals(legs.get(legs.size() - 1).getStreet().getId())) {
                length += legLength / 2;
            } else {
                length += legLength;
            }
        }

        return length;
    }

    /**
     * Given a route, return all loops in the route.
     *
     * @return -- a list of subroutes (starting and ending legs) of each loop.
     */
    public List<SubRoute> loops() {
        List<SubRoute> loops = new ArrayList<>();
        // Implementation for finding loops (if needed)
        return loops;
    }

    /**
     * Given a route, produce a new route with simplified instructions.
     *
     * @return -- the simplified route.
     */
    public static Route simplify() {
        // Filter the legs to exclude straight legs, using the helper method
        List<Helper.Leg> simplifiedLegs = filterLegs();

        // Create and return a new Route object using the simplified legs
        return new Route(simplifiedLegs);
    }

    private static List<Helper.Leg> filterLegs() {
        // Initialize an empty list to store legs that have a turn direction other than 'Straight'
        List<Helper.Leg> filteredLegs = new ArrayList<>();

        // Iterate through each leg in the 'legs' list (assuming 'legs' is a class-level field)
        for (Helper.Leg leg : legs) {

            // If the current leg does not have a 'Straight' turn direction
            if (leg.getTurnDirection() != TurnDirection.Straight) {

                // Add the leg to the filteredLegs list since it involves a turn
                filteredLegs.add(leg);
            }
        }

        // Return the list of legs that involve turns (not straight)
        return filteredLegs;
    }


}
