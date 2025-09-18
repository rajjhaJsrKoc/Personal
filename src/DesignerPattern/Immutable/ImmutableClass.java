package DesignerPattern.Immutable;

import java.util.Date;

public final class ImmutableClass {
    private final int id;
    private final String name;
    private final Date date;

    public ImmutableClass(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = new Date(date.getTime());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public String toString() {
        return "ImmutableClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
