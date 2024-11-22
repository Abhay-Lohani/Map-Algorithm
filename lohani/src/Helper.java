public class Helper {

    // Inner class representing a Street
    public static class Street {
        private final String id;
        private final Point start;
        private final Point end;

        public Street(String id, Point start, Point end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        public String getId() {
            return id;
        }

        public Point getStart() {
            return start;
        }

        public Point getEnd() {
            return end;
        }
    }

    // Inner class representing a Leg
    public static class Leg {
        private TurnDirection turn;
        private Street street;

        public Leg(TurnDirection turn, Street street) {
            this.turn = turn;
            this.street = street;
        }

        public TurnDirection getTurnDirection() {
            return turn;
        }

        public Street getStreet() {
            return street;
        }
    }
}
