import java.util.List;
import java.util.ArrayList;

public class NoGoZoneModel {
    private List<NoGoZone> zones = new ArrayList<>();
    private List<ModelObserver> observers = new ArrayList<>();

    public void addZone(NoGoZone zone) {
        zones.add(zone);
        notifyObservers();
    }

    public void removeZone(NoGoZone zone) {
        zones.remove(zone);
        notifyObservers();
    }

    public List<NoGoZone> getZones() {
        return zones;
    }

    public void addObserver(ModelObserver obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        for (ModelObserver obs : observers) {
            obs.modelUpdated(zones);
        }
    }
}