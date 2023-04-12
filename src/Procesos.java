public class Procesos {
    String id;
    int cedula;
    int tiempo;

    public Procesos(String id, int cedula, int tiempo) {
        this.id = id;
        this.cedula = cedula;
        this.tiempo = tiempo;
    }

    public String toString() {
        return (id + ", " + cedula + ", " + tiempo +"\n");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
