package fan.akua.day9.bean;

public class Node {
    private int id;
    private String node_id;
    private String name;

    public int getId() {
        return id;
    }

    public String getNode_id() {
        return node_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", node_id='" + node_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
