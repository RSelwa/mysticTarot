# Tarot project report

The main goal of the project is to create a software with an user interface that can read cards from the divinatory tarot.

## Modelling

Our project is divided into two files.

First,the 'Carte' part, which will contain all classes and methods that will be necessary for the proper functioning of our cards.

Then, the 'user interface' part in the main file.

### Card Class

For this part, I'm using two classes:

- **Card** (Defined by a name, a description for each signification for the divination and an image File.




### Main Class

- **Deck** (Defined by an ArrayList of multiple Cards)



Here we have different panels

- mainPanel (contain all the others pannel)
- addCardPanel (display the menu to add a Card to the deck, and show the result of the Mystic Tarot)
- saveDeckPanel (display the screen where you can save and import the deck)
- containerDeckPanel (display the cards in the deck)
- deckModifierPanel (display the screen where you can modfy a card or delete it)




##  User actions

What our user can do in our app?

He can generate cards, he can create his own cards by filling Text Field (pay attention to delete the default value of each Text Area), he can see his deck (and modify or delete each card), and he can read his future, by selecting a card for each aspect of his future.

## Displaying images

To display images, I use a method that I called showDeck, I use ImageIcon wich allow me to use a Label to display the name and the IMG of each card.

```java
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
```




## Saving the deck

To save the deck when the user click to the button 'Save', I'm using Serializable on my Deck, then it saves our deck into ./deck.ser.

```java
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
```

After that, inside the main function when we click to import:

```java
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
```


## Reading the future

In our app,we let the user select 4 cards (it can be the same cards), then it show in a Label the meaning of his future.

## Difficulties

I have encountered many difficulties with JFrame as it was my first project using Swing, particulary on the Panel, it was hard to organize the App as I wanted but I succeeded to add some colors but i know that the result is ugly.

## The future for the app

We could implement:

- a selecotr of IMG by the user, instead of a default img card
- add some design
- add some responsive to the app (for the deck especially)
- optimize the system of selection
