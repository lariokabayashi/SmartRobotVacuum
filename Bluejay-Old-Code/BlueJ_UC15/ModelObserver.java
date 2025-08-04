import java.util.List;

public interface ModelObserver {
    void modelUpdated(List<NoGoZone> zones);
}