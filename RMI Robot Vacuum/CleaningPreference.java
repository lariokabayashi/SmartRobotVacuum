/**
 * Per-room user preferences (intensity, passes, wet/dry, …).
 */
public class CleaningPreference {
    private int intensity = 1;   // 1-5
    private boolean wetMop;

    /* Getters & setters */
    public int  getIntensity()        { return intensity; }
    public void setIntensity(int i)   { intensity = i;    }

    public boolean isWetMop()         { return wetMop;    }
    public void   setWetMop(boolean w){ wetMop = w;       }
}

