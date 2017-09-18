package extractor;

/**
 * Created by juliano on 16/09/17.
 */
public class Connection {

    public static final String CONNECTION_OPENING = "UP";
    public static final String CONNECTION_CLOSING = "DOWN";

    private int from;
    private int to;
    private double openingTime;
    private double closingTime;

    public Connection(int from, int to, double openingTime){
        this.from = from;
        this.to = to;
        this.setOpeningTime(openingTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection that = (Connection) o;

        if (from == that.from && to == that.to) {
            return true;
        } else if (from == that.to && to == that.from){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int max = Math.max(from, to);
        int min = Math.min(from, to);

        int result = max;
        result = 31 * result + min;
        return result;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(double openingTime) {
        this.openingTime = openingTime;
    }

    public double getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(double closingTime) {
        this.closingTime = closingTime;
    }
}
