package trains.core;

/**
 * @author: 邓小军
 * @since: 2019/12/30 14:36
 *
 */
public class RouteInfo {

    private String toStationName;

    private int distance;

    public RouteInfo(String name, int dis) {
        toStationName = name;
        distance = dis;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
