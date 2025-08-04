public class NoGoZone {
    public int xStart, yStart, xEnd, yEnd;
    public String label;

    public NoGoZone(int xStart, int yStart, int xEnd, int yEnd, String label) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.label = label;
    }
}