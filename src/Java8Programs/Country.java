package Java8Programs;

import java.util.List;

class Country {
    String name;
    List list;
    public Country(String name) {
        this.name = name;
    }
    public void setList(List list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public List<City> getList() {
        return list;
    }
}
