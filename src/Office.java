public class Office {
    private String location, openHours;

    Office(String location, String openHours) {
        this.location = location;
        this.openHours = openHours;
    }
    Office(){
        location = "Undefined.";
        openHours = "Undefined.";
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }
    public String getOpenHours() {
        return openHours;
    }
}