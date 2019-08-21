package apps.sharabash.bzender.Models;

import org.jetbrains.annotations.NotNull;

public class AddTenderType {
    private int id ;
    private String name ;

    public AddTenderType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String toString() {
        return "AddTenderType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
