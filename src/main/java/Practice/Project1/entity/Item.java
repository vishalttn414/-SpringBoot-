package Practice.Project1.entity;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private static final long serialVersionUID = 5L;

    private String name;
    private Integer id;
    private String salary;

    @Override
    public String toString() {
        return "item{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary='" + salary + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(id, item.id) &&
                Objects.equals(salary, item.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Item() {
    }

    public Item(String name, Integer id, String salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
}
