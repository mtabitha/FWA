package edu.school21.cinema.models;

public class Image {

    private String id;
    private String name;
    private String size;
    private String path;

    public Image() {
    }

    public Image(String id, String name, String size, String path) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
