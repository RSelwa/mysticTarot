import javax.swing.ImageIcon;

public class Carte {
    protected int number;
    protected String name;
    protected String def;
    protected String description;
    protected String descriptionWork;
    protected String descriptionFate;
    protected ImageIcon img;
  
    public Carte(String name, String description, ImageIcon img) {
    // public Carte(int n, String name, String description, ImageIcon img) {
        // this.number = n;
        this.name = name;
        this.description = description;
        this.img = img;
    }
}
