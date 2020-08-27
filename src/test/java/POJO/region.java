package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class region {

    private int region_id;
    private String region_name;

    public region(){

    }

    @Override
    public String toString() {
        return "region{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                '}';
    }

    public region(int region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}