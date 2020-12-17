import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddCardListener implements ActionListener {

  protected JLabel field;
  protected JTextArea area;

  public AddCardListener(JLabel field, JTextArea area) {
    this.field = field;
    this.area = area;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton b = ((JButton) e.getSource());
    System.out.println(b.getText());
    this.field.setText(this.area.getText());
    
  }
}
