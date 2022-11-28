public class Libro {

    public boolean enPosesion = false;

    public String name;

    public Libro(String name){
        this.name=name;
    }

    public boolean isEnPosesion() {
        return enPosesion;
    }

    public void setEnPosesion(boolean enPosesion) {
        this.enPosesion = enPosesion;
    }
}
