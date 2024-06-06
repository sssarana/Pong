package pong.model;

public interface Resizable {
    /**
     * Resize X values of the object
     * @param factor - factor which used for resizing
     **/
    public void resizeX(double factor);
    /**
     * Resize Y values of the object
     * @param factor - factor which used for resizing
     **/
    public void resizeY(double factor);
}
