import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// import org.json.simple.JSONObject;

public class App extends JFrame {

  public static void main(String[] args) throws Exception {
    Color darkBlue = new Color(46, 108, 109), yellow = new Color(
      255,
      174,
      90
    ), lightGray = new Color(237, 232, 226), white = new Color(
      255,
      255,
      255
    ), brown = new Color(115, 64, 54);
    ArrayList<Carte> deck = new ArrayList();
    //* CREATE FRAME
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setTitle("Best Mystic Tarot in English");
    f.setPreferredSize(new Dimension(800, 600));
    //* Create PANEL
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    f.getContentPane().add(new JScrollPane(mainPanel));
    //? ADD CART PANEL - TOP
    JPanel addCardPanel = new JPanel();
    mainPanel.add(addCardPanel, BorderLayout.NORTH);
    //!
    JTextField nameCard = new JTextField("Name of new Card");
    JTextField descCardQualities = new JTextField("Card's Description for Qualities");
    JTextField descCardFault = new JTextField("Card's Description for Fault");
    JTextField descCardFate = new JTextField("Card's Description for Fate");
    JTextField descCardAbsent = new JTextField("Card's Description for Absent");
    descCardQualities.setPreferredSize(new Dimension(200, 50));
    descCardFault.setPreferredSize(new Dimension(200, 50));
    descCardFate.setPreferredSize(new Dimension(200, 50));
    descCardAbsent.setPreferredSize(new Dimension(200, 50));
    nameCard.setPreferredSize(new Dimension(200, 24));
    JButton addCart = new JButton("Add a cart");
    addCardPanel.add(nameCard);
    addCardPanel.add(descCardQualities);
    addCardPanel.add(descCardFault);
    addCardPanel.add(descCardFate);
    addCardPanel.add(descCardAbsent);
    addCardPanel.add(addCart);
    //?SAVE DECK PANEL - BOTTOM
    JPanel saveDeckPanel = new JPanel();
    mainPanel.add(saveDeckPanel, BorderLayout.SOUTH);
    //!
    JButton saveButton = new JButton("Save");
    JButton importButton = new JButton("Import");
    saveDeckPanel.add(saveButton, BorderLayout.LINE_START);
    saveDeckPanel.add(importButton, BorderLayout.LINE_END);
    //? CONTAINER DECK PANEL - RIGHT
    JPanel containerDeckPanel = new JPanel();
    JPanel deckPanel = new JPanel();
    JButton divinatory = new JButton("Divin");
    mainPanel.add(containerDeckPanel, BorderLayout.CENTER);
    containerDeckPanel.add(deckPanel, BorderLayout.LINE_END);
    containerDeckPanel.add(divinatory, BorderLayout.LINE_END);
    //!
    JButton showDeck = new JButton("ShowDeck");
    JButton generateDeck = new JButton("Generate Deck");
    containerDeckPanel.add(generateDeck, BorderLayout.LINE_START);
    //? MODIFIER DECK - LEFT
    JPanel deckModifierPanel = new JPanel();
    deckModifierPanel.setLayout(
      new BoxLayout(deckModifierPanel, BoxLayout.Y_AXIS)
    );
    mainPanel.add(deckModifierPanel, BorderLayout.WEST);
    //? MODIFIER CARD PANEL - LEFT TOP
    JPanel modifierCard = new JPanel();
    // modifierCard.setBounds(61, 11, 81, 140);
    modifierCard.setLayout(new BoxLayout(modifierCard, BoxLayout.Y_AXIS));
    modifierCard.setBorder(new EmptyBorder(20, 20, 20, 20));
    deckModifierPanel.add(modifierCard, BorderLayout.NORTH);
    //!
    JTextField cardSelector = new JTextField("Select a card by name");
    JTextField newNameCard = new JTextField("Type new name card");
    JTextField newDescCardQualities = new JTextField("Type new description card Qualities");
    JTextField newDescCardFault = new JTextField("Type new description card Fault");
    JTextField newDescCardFate = new JTextField("Type new description card Fate");
    JTextField newDescCardAbsent = new JTextField("Type new description card Absent");
    JLabel updateCardResponse = new JLabel();
    updateCardResponse.setForeground(Color.RED);
    JButton updateCard = new JButton("Modify a card");
    newNameCard.setPreferredSize(new Dimension(200, 24));
    newDescCardQualities.setPreferredSize(new Dimension(200, 50));
    newDescCardFault.setPreferredSize(new Dimension(200, 50));
    newDescCardFate.setPreferredSize(new Dimension(200, 50));
    newDescCardAbsent.setPreferredSize(new Dimension(200, 50));
    modifierCard.add(cardSelector);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(newNameCard);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(newDescCardQualities);
    modifierCard.add(newDescCardFault);
    modifierCard.add(newDescCardFate);
    modifierCard.add(newDescCardAbsent);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(updateCard);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(updateCardResponse);
    //? MODIFIER CARD PANEL - LEFT BOTTOM
    JPanel deleteCard = new JPanel();
    deleteCard.setBounds(61, 11, 81, 140);
    deleteCard.setLayout(new BoxLayout(deleteCard, BoxLayout.Y_AXIS));
    deleteCard.setBorder(new EmptyBorder(10, 20, 10, 20));
    deckModifierPanel.add(deleteCard, BorderLayout.NORTH);
    //!
    JTextField cardToDelete = new JTextField("Select a card by name");
    JLabel deletedCardResponse = new JLabel();
    deletedCardResponse.setForeground(Color.RED);
    JButton deleteCardButton = new JButton("Delete a card");
    cardToDelete.setSize(new Dimension(200, 24));
    deckModifierPanel.add(cardToDelete);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 20)));
    deckModifierPanel.add(deleteCardButton);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 20)));
    deckModifierPanel.add(deletedCardResponse);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 20)));

    //? COLORS
    mainPanel.setBackground(darkBlue);
    addCardPanel.setBackground(lightGray);
    saveDeckPanel.setBackground(lightGray);
    containerDeckPanel.setBackground(yellow);
    deckPanel.setBackground(yellow);
    modifierCard.setBackground(white);

    //*------------------------------------------------------------
    //*ACTION LISTENER
    
    divinatory.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("div");
          JPanel responseDivin = new JPanel();
          JLabel divinResponse = new JLabel();
          JButton submitDivin = new JButton("Submit your fate");
          JTextField selectCardQualities = new JTextField("Select a card for Qualities");
          JTextField selectCardFalt = new JTextField("Select a card for Falt");
          JTextField selectCardFate = new JTextField("Select a card for Fate");
          JTextField selectCardAbsent = new JTextField("Select a card for Absent");
          divinResponse.setText("");
          containerDeckPanel.add(responseDivin, BorderLayout.PAGE_END);
          responseDivin.add(submitDivin, BorderLayout.PAGE_END);
          responseDivin.add(selectCardQualities, BorderLayout.PAGE_END);
          responseDivin.add(selectCardFalt, BorderLayout.PAGE_END);
          responseDivin.add(selectCardFate, BorderLayout.PAGE_END);
          responseDivin.add(selectCardAbsent, BorderLayout.PAGE_END);
          responseDivin.add(divinResponse, BorderLayout.PAGE_END);
          submitDivin.addActionListener(
            (ActionListener) new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                if (!selectCardQualities.getText().isEmpty() && !selectCardFalt.getText().isEmpty() &&!selectCardFate.getText().isEmpty() &&!selectCardAbsent.getText().isEmpty() ) {
                  for (Carte carte : deck) {
                    if (carte.name.equals(selectCardQualities.getText())) {
                      divinResponse.setText(divinResponse.getText()+"Qualities : "+carte.descriptionQualities);
                    } else {
                      updateCardResponse.setText("Card for Qualities doesn't exists");
                    }
                    if (carte.name.equals(selectCardFalt.getText())) {
                      divinResponse.setText(divinResponse.getText()+"    Default : "+carte.descriptionFault);
                    } else {
                      updateCardResponse.setText("Card for Fault doesn't exists");
                    }
                    if (carte.name.equals(selectCardFate.getText())) {
                      divinResponse.setText(divinResponse.getText()+"    Fate : "+carte.descriptionFate);
                    } else {
                      updateCardResponse.setText("Card for Fate doesn't exists");
                    }
                    if (carte.name.equals(selectCardAbsent.getText())) {
                      divinResponse.setText(divinResponse.getText()+"    Absent : "+carte.descriptionAbsence);
                    } else {
                      updateCardResponse.setText("Card for Absent doesn't exists");
                    }
                  }
                  System.out.println();
                  //Delete the text in the text field
                  selectCardQualities.setText(" Qualities");
                  selectCardFalt.setText(" Falt");
                  selectCardFate.setText(" Fate");
                  selectCardAbsent.setText("Absent");
                }
              }});

        }});
    saveButton.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
			    	  
            FileOutputStream fileOut =
            new FileOutputStream("./deck.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(deck);
            out.close();
            fileOut.close();
            System.out.printf("Deck is saved in ./deck.ser");
         } catch (IOException i) {
            i.printStackTrace();
         }
        }
      }
    );
    importButton.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            FileInputStream fileIn = new FileInputStream("./deck.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println(in.readUTF());
            // deck =   in.readUTF();
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return;
         }  
        }
      }
    );
    addCart.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (!nameCard.getText().isEmpty()) {
            if (!descCardQualities.getText().isEmpty()) deck.add(
              new Carte(
                nameCard.getText(),
                descCardQualities.getText(),descCardFault.getText(),descCardFate.getText(),descCardAbsent.getText(),
                new ImageIcon("./img/0-fou.jpg")
              )
            ); else deck.add(
              new Carte(
                nameCard.getText(),
                "","","","",
                new ImageIcon("./img/0-fou.jpg")
              )
            );

            //Delete the text in the text field
            nameCard.setText("");
            descCardQualities.setText("");
            descCardFault.setText("");
            descCardFate.setText("");
            descCardAbsent.setText("");
            showDeck(deck, deckPanel);
          }
        }
      }
    );
    deleteCardButton.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (!cardToDelete.getText().isEmpty()) {
            for (int i = 0; i < deck.size(); i++) {
              if (deck.get(i).name.equals(cardToDelete.getText())) {
                deletedCardResponse.setText("");
                deck.remove(deck.get(i));
                deletedCardResponse.setText("Card deleted");
              } else {
                deletedCardResponse.setText("Card doesn't exists");
              }
            }
            //Delete the text in the text field
            cardToDelete.setText("");
            showDeck(deck, deckPanel);
          }
        }
      }
    );
    updateCard.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (!cardSelector.getText().isEmpty()) {
            for (Carte carte : deck) {
              if (carte.name.equals(cardSelector.getText())) {
                updateCardResponse.setText("");
                carte.name = newNameCard.getText();
                if (!newDescCardQualities.getText().isEmpty()) {
                  carte.descriptionQualities = newDescCardQualities.getText();
                  carte.descriptionFault = newDescCardFault.getText();
                  carte.descriptionFate = newDescCardFate.getText();
                  carte.descriptionAbsence = newDescCardAbsent.getText();
                  break;
                }
              } else {
                updateCardResponse.setText("Card doesn't exists");
              }
            }
            //Delete the text in the text field
            cardSelector.setText("");
            newNameCard.setText("");
            newDescCardQualities.setText("");
            newDescCardFault.setText("");
            newDescCardFate.setText("");
            newDescCardAbsent.setText("");
            showDeck(deck, deckPanel);
          }
        }
      }
    );

    generateDeck.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          generateCard(deck, deckPanel);
          generateDeck.setVisible(false);
          deleteCard.setVisible(false);
        }
      }
    );
    showDeck.addActionListener(
      (ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          showDeck(deck, deckPanel);
        }
      }
    );

    //*------------------------------------------------------------
    f.pack();
    f.setVisible(true);
  }

  public static void showDeck(ArrayList<Carte> deck, JPanel deckPanel) {
    deckPanel.removeAll();
    for (Carte carte : deck) {
      Image newimg = carte.img
        .getImage()
        .getScaledInstance(120, 230, java.awt.Image.SCALE_SMOOTH);
      ImageIcon icon = new ImageIcon(newimg);
      JLabel newJLabel = new JLabel(carte.name, icon, SwingConstants.LEADING);
      newJLabel.setHorizontalTextPosition(JLabel.CENTER);
      newJLabel.setVerticalTextPosition(JLabel.BOTTOM);
      deckPanel.add(newJLabel);
    }
  }

  public static void generateCard(ArrayList<Carte> deck, JPanel deckPanel) {
    String[] names = {
      "fou",
      "bateleur",
      "papesse",
      "imperatrice",
      "empereur",
      "pape",
      "amoureux",
      "chariot",
      "justice",
      "hermite",
      "roue-fortune",
      "force",
      "pendu",
      "mort",
      "temperance",
      "diable",
      "maison-dieu",
      "etoile",
      "lune",
      "soleil",
      "jugement",
      "monde",
    };

    for (int i = 0; i < (names.length); i++) {
      deck.add(
        new Carte(
          names[i],
          "","","","",
          new ImageIcon("./img/" + String.valueOf(i) + "-" + names[i] + ".jpg")
        )
      );
    }
    showDeck(deck, deckPanel);
  }
}
