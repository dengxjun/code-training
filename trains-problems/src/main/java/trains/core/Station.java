package trains.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2019/12/30 14:09
 *
 */
public class Station {

    private String name;

    private List<RouteInfo> nextStations = new ArrayList<RouteInfo>();

    public Station(String sname, RouteInfo routeInfo) {
        this.name = sname;
        nextStations.add(routeInfo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RouteInfo> getNextStations() {
        return nextStations;
    }

    public void addRouteInfo(RouteInfo routeInfo){
        nextStations.add(routeInfo);
    }

    public void setNextStations(List<RouteInfo> nextStations) {
        this.nextStations = nextStations;
    }
}
