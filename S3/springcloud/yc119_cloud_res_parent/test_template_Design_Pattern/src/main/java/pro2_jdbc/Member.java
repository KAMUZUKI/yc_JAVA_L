package pro2_jdbc;


public class Member {

    private String raid;
    private String raname;
    private String rapwd;

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid;
    }

    public String getRaname() {
        return raname;
    }

    public void setRaname(String raname) {
        this.raname = raname;
    }

    public String getRapwd() {
        return rapwd;
    }

    public void setRapwd(String rapwd) {
        this.rapwd = rapwd;
    }

    @Override
    public String toString() {
        return "Member{" +
                "raid='" + raid + '\'' +
                ", raname='" + raname + '\'' +
                ", rapwd='" + rapwd + '\'' +
                '}';
    }
}
