package sample;


public class Tasks {
    private String name;
    private String description;
    private String importance;
    private String status;

    public Tasks(String name, String description, String importance, String status) {
        this.name = name;
        this.description = description;
        this.importance = importance;
        this.status = status;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
