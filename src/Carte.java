import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Carte {
    protected int number;
    protected String name;
    protected String def;
    // protected String description;
    protected String descriptionQualities;
    protected String descriptionFault;
    protected String descriptionFate;
    protected String descriptionAbsence;
    protected ImageIcon img;
    protected JButton select = new JButton("Select");
  
    public Carte(String name,  String descriptionQualities, String descriptionFault,String descriptionFate, String descriptionAbsence, ImageIcon img) {
        this.name = name;
        this.descriptionQualities = descriptionQualities;
        this.descriptionFault = descriptionFault;
        this.descriptionFate = descriptionFate;
        this.descriptionAbsence = descriptionAbsence;
        this.img = img;
    }
}
