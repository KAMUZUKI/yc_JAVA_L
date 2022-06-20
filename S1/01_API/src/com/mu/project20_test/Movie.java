package src.com.mu.project20_test;

import java.util.List;
import java.util.Objects;

public class Movie {
    private Integer mid;
    private String name;
    private Integer year;
    private List<String> types;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getMid(), movie.getMid()) && Objects.equals(getName(), movie.getName()) && Objects.equals(getYear(), movie.getYear()) && Objects.equals(getTypes(), movie.getTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMid(), getName(), getYear(), getTypes());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", types=" + types +
                '}';
    }
}
