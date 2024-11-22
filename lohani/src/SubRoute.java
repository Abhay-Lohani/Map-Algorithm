public class SubRoute {

    private Route route;
    private int startLeg;
    private int endLeg;
    /**
     * Identify a sub-part of a route.  The sub-part of the route goes from the start of the startLeg
     * to the end of the endLeg.
     *
     * The Route from which we start may contain loops.
     * @param walk -- the route from which we are identifying a subroute
     * @param startLeg -- the starting leg of the subroute
     * @param endLeg -- the ending leg of the subroute
     */
    public SubRoute( Route walk, int startLeg, int endLeg ) {
        if (walk == null || startLeg < 1 || startLeg > endLeg || endLeg > walk.legs()) {
            throw new IllegalArgumentException("Invalid arguments for SubRoute constructor");
        }
        this.route = walk;
        this.startLeg = startLeg;
        this.endLeg = endLeg;
    }

    /**
     * Return the leg number that starts this subroute
     * @return -- the starting leg number
     */
    public int subrouteStart() {

        return startLeg;
    }

    /**
     * The ending leg number for this subroute
     * @return - the leg number that ends the subroute
     */
    public int subrouteEnd() {
        return endLeg;
    }

    /**
     * Convert this subroute into a pure route of its own.
     * @return -- the Route that represents the subroute all on its own.
     */
    public Route extractRoute() {
        Route subRoute = new Route();
//
//        // Add each leg of the subroute to the new Route
//        for (int i = subrouteStart(); i <= subrouteEnd(); i++) {
//            subRoute.appendTurn(Route.turnDirection(i), Route.turnOnto(i));
//        }

        return subRoute;
    }
}
