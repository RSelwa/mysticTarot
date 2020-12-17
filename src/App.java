import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.FileWriter;
import java.io.IOException;
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
    JTextField descCard = new JTextField("Description of new Card");
    descCard.setPreferredSize(new Dimension(200, 50));
    nameCard.setPreferredSize(new Dimension(200, 24));
    JButton addCart = new JButton("Add a cart");
    addCardPanel.add(nameCard);
    addCardPanel.add(descCard);
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
    mainPanel.add(containerDeckPanel, BorderLayout.CENTER);
    containerDeckPanel.add(deckPanel, BorderLayout.LINE_END);
    //!
    JButton showDeck = new JButton("ShowDeck");
    JButton generateDeck = new JButton("Generate Deck");
    containerDeckPanel.add(generateDeck, BorderLayout.LINE_START);
    //? MODIFIER DECK - LEFT
    JPanel deckModifierPanel = new JPanel();
    deckModifierPanel.setLayout(new BoxLayout(deckModifierPanel, BoxLayout.Y_AXIS));
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
    JTextField newDescCard = new JTextField("Type new description card");
    JLabel updateCardResponse = new JLabel();
    updateCardResponse.setForeground(Color.RED);
    JButton updateCard = new JButton("modifiez une carte");
    newNameCard.setPreferredSize(new Dimension(200, 24));
    newDescCard.setPreferredSize(new Dimension(200, 50));
    modifierCard.add(cardSelector);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(newNameCard);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(newDescCard);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(updateCard);
    modifierCard.add(Box.createRigidArea(new Dimension(0, 10)));
    modifierCard.add(updateCardResponse);
    //? MODIFIER CARD PANEL - LEFT BOTTOM
    JPanel deleteCard = new JPanel();
    deleteCard.setBounds(61, 11, 81, 140);
    deleteCard.setLayout(new BoxLayout(deleteCard, BoxLayout.Y_AXIS));
    deleteCard.setBorder(new EmptyBorder(10, 20, 10, 20));
    deckModifierPanel.add(deleteCard,BorderLayout.NORTH);
    //!
    JTextField cardToDelete = new JTextField("Select a card by name");
    JLabel deletedCardResponse = new JLabel();
    deletedCardResponse.setForeground(Color.RED);
    JButton deleteCardButton= new JButton("Supprimer une carte");
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

    //* TEST
    deck.add(new Carte("test", "", new ImageIcon("./img/x.jpg")));

    showDeck(deck, deckPanel);
    //*------------------------------------------------------------
    //*ACTION LISTENER
    addCart.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (!nameCard.getText().isEmpty()) {
            if (!descCard.getText().isEmpty()) deck.add(
              new Carte(
                nameCard.getText(),
                descCard.getText(),
                new ImageIcon("./img/x.jpg")
              )
            ); else deck.add(
              new Carte(nameCard.getText(), "", new ImageIcon("./img/x.jpg"))
            );

            //Delete the text in the text field
            nameCard.setText("");
            descCard.setText("");
            showDeck(deck, deckPanel);
          }
        }
      }
    );
    deleteCardButton.addActionListener((ActionListener) new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          if (!cardToDelete.getText().isEmpty()) {
            for (int i = 0; i < deck.size(); i++) {
              
              if (deck.get(i).name.equals(cardToDelete.getText())) {
                deletedCardResponse.setText("");
                deck.remove(deck.get(i));
                deletedCardResponse.setText("Card deleted");
              }else{
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
    updateCard.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (!cardSelector.getText().isEmpty()) {
            for (Carte carte : deck) {
              if (carte.name.equals(cardSelector.getText())) {
                updateCardResponse.setText("");
                carte.name = newNameCard.getText();
                if (!newDescCard.getText().isEmpty()) {
                  carte.description = newDescCard.getText();
                }
              }else{
                updateCardResponse.setText("Card doesn't exists");
              }
            }
            //Delete the text in the text field
            cardSelector.setText("");
            newNameCard.setText("");
            newDescCard.setText("");
            showDeck(deck, deckPanel);
          }
        }
      }
    );

    generateDeck.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          generateCard(deck, deckPanel);
          generateDeck.setVisible(false);
          deleteCard.setVisible(false);
        }
      }
    );
    showDeck.addActionListener((ActionListener) new ActionListener() {
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

//   public static void saveDeck(ArrayList<Carte> deck) {
//      //Creating a JSONObject object
//      JSONObject jsonObject = new JSONObject();
//      //Inserting key-value pairs into the json object
//      jsonObject.put("ID", "1");
//      jsonObject.put("First_Name", "Shikhar");
//      jsonObject.put("Last_Name", "Dhawan");
//      jsonObject.put("Date_Of_Birth", "1981-12-05");
//      jsonObject.put("Place_Of_Birth", "Delhi");
//      jsonObject.put("Country", "India");
//      try {
//         FileWriter file = new FileWriter("E:/output.json");
//         file.write(jsonObject.toJSONString());
//         file.close();
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }
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
          "",
          new ImageIcon("./img/" + String.valueOf(i) + "-" + names[i] + ".jpg")
        )
      );
    }
    showDeck(deck, deckPanel);

  }
}
