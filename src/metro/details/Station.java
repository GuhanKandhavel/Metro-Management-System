package metro.details;

public class Station {
    private int stationId;
    private String stationName;
    public Station(){
    }
    public Station(int stationId,String stationName)
    {
        this.stationId=stationId;
        this.stationName=stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
