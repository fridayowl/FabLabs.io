package android.fablabs.io.model;

public class Myhistorymodel {

    String date ;
    int id ;
    int imageresources;
    String status,headnode,childnode;


    public Myhistorymodel(String date, int id, int imageresources, String status, String headnode, String childnode) {
        this.date = date;
        this.id = id;
        this.imageresources = imageresources;
        this.status = status;
        this.headnode = headnode;
        this.childnode = childnode;
    }

    public String getHeadnode() {
        return headnode;
    }

    public void setHeadnode(String headnode) {
        this.headnode = headnode;
    }

    public String getChildnode() {
        return childnode;
    }

    public void setChildnode(String childnode) {
        this.childnode = childnode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageresources() {
        return imageresources;
    }

    public void setImageresources(int imageresources) {
        this.imageresources = imageresources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override()
    public boolean equals(Object other) {
        // This is unavoidable, since equals() must accept an Object and not something more derived
        if (other instanceof Myhistorymodel) {
            Myhistorymodel myhistorymodel= (Myhistorymodel) other;
            return myhistorymodel.getId()==(this.getId());
        }

        return false;
    }
}
