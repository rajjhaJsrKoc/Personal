package Java8Programs;

public class Deparment {
    private String deparmentName;
    private int id;

    public Deparment(String deparmentName, int id) {
        this.deparmentName = deparmentName;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Deparment{" +
                "deparmentName='" + deparmentName + '\'' +
                ", id=" + id +
                '}';
    }
}
