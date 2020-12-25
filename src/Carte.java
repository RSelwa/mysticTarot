import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Carte {
    protected String name;
    protected String descriptionQualities;
    protected String descriptionFault;
    protected String descriptionFate;
    protected String descriptionAbsence;
    protected ImageIcon img;
  
    public Carte(String name,  String descriptionQualities, String descriptionFault,String descriptionFate, String descriptionAbsence, ImageIcon img) {
        this.name = name;
        this.descriptionQualities = descriptionQualities;
        this.descriptionFault = descriptionFault;
        this.descriptionFate = descriptionFate;
        this.descriptionAbsence = descriptionAbsence;
        this.img = img;
    }
}
