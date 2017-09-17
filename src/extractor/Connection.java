package extractor;

import java.util.Date;

/**
 * Created by juliano on 16/09/17.
 */
public class Connection {
    private int from;
    private int to;
    private Date openingTime;
    private Date closingTime;



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
}
