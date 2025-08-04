/**
 * MODEL – list of user-defined no-go zones (Polygons).
 * Observable so MapHandler and BoundaryEditor reflect changes.
 */
import java.util.*;
import java.awt.Polygon;

public class BoundaryStorage implements Subject {
    private final List<Polygon> noGoZones = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    public void addZone(Polygon z)    { noGoZones.add(z);  notifyObservers(); }
    public void removeZone(Polygon z) { noGoZones.remove(z); notifyObservers(); }
    public List<Polygon> getZones()   { return Collections.unmodifiableList(noGoZones); }

    @Override public void registerObserver(Observer o) { observers.add(o); }
    @Override public void removeObserver(Observer o)   { observers.remove(o); }
    @Override public void notifyObservers() {
        for (Observer o : observers) o.update(Collections.unmodifiableList(noGoZones));
    }
}

