import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Initialize the Main Deck with Array List.
        ArrayList<String> mainDeck = new ArrayList<>();
        int[] cardNums = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //Add Wild Cards to Deck
        for (int i = 0; i < 5; i++) {
            mainDeck.add("Wild Card");
            mainDeck.add("Wild +4");
        }
        //Add Green Cards to Deck
        mainDeck.add("Green 0");
        for (int i = 0; i < 2; i++) {
            mainDeck.add("Green Skip");
            mainDeck.add("Green +2");
            for (int j = 0; j < 9; j++) {
                mainDeck.add("Green " + String.valueOf(cardNums[j]));
            }
        }
        //Add Red Cards to Deck
        mainDeck.add("Red 0");
        for (int i = 0; i < 2; i++) {
            mainDeck.add("Red Skip");
            mainDeck.add("Red +2");
            for (int j = 0; j < 9; j++) {
                mainDeck.add("Red " + String.valueOf(cardNums[j]));
            }
        }
        //Add Blue Cards to Deck
        mainDeck.add("Blue 0");
        for (int i = 0; i < 2; i++) {
            mainDeck.add("Blue Skip");
            mainDeck.add("Blue +2");
            for (int j = 0; j < 9; j++) {
                mainDeck.add("Blue " + String.valueOf(cardNums[j]));
            }
        }
        //Add Green Cards to Deck
        mainDeck.add("Yellow 0");
        for (int i = 0; i < 2; i++) {
            mainDeck.add("Yellow Skip");
            mainDeck.add("Yellow +2");
            for (int j = 0; j < 9; j++) {
                mainDeck.add("Yellow " + String.valueOf(cardNums[j]));
            }
        }
        //Shuffle the Deck
        Collections.shuffle(mainDeck);

        //Initialize Deck 1
        ArrayList<String> deck1 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            deck1.add(mainDeck.get(0));
            mainDeck.remove(0);
        }

        //Initialize Deck 2
        ArrayList<String> deck2 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            deck2.add(mainDeck.get(0));
            mainDeck.remove(0);
        }

        //Introduce Game and Initialize whether it's a PvP or PvE game and make sure no option breaks the code
        System.out.println("Welcome to Uno!\nWould you like to play against another player, or against AI? (answer with either pvp or pve)");
        String isPVP = input.nextLine();
        boolean isCorrect = false;

        while (!isCorrect) {
            if (!isPVP.equalsIgnoreCase("pvp") && !isPVP.equalsIgnoreCase("pve")) {
                System.out.println("Please enter a valid option!");
                isPVP = input.nextLine();
            }  else {
                isCorrect = true;
            }
        }
        //Initialize the table card and make sure it is not a wild card
        String tableCard = "Wild Card";
        for (int i = 0; i < mainDeck.size(); i++) {
            if (!mainDeck.get(i).equals("Wild Card") || !mainDeck.get(i).equals("Wild +4")) {
                tableCard = mainDeck.get(i);
                mainDeck.remove(i);
                break;
            }
        }
        //Initialize colour and number variables
        String colour = tableCard.substring(0, tableCard.indexOf(" "));
        System.out.println(colour);
        String number = tableCard.substring(tableCard.indexOf(" ") + 1);
        System.out.println(number);

        String cardUse;

        //Initialize variable for skipping turns
        boolean isSkip = false;
        boolean isPossible = false;

        //Main Game for PVP and PVE
        if (isPVP.equalsIgnoreCase("PVP")) {
            while (deck1.size() > 0 || deck2.size() > 0) {
                //Turn for Player 1
                //Loop if player skips other player and make sure that the loop does not continue
                System.out.println("The table card is currently: " + tableCard);
                isPossible = false;

                do {
                    if (isSkip) {
                        System.out.println("Turn has been skipped! Player 1 go again!");
                        isSkip = false;
                    }

                    for (String card : deck1) {
                        if (card.contains(colour) || card.contains(number) || card.contains("Wild")) {
                            isPossible = true;
                        }
                    }

                    if (isPossible) {
                        System.out.println("Player 1, choose a card you would like to play or draw a card (Case Sensitive):\n" + deck1);
                        cardUse = input.nextLine();

                        while (true) {
                            if (cardUse.contains(colour) || cardUse.contains(number) || cardUse.contains("Wild") && deck1.contains(cardUse)) {
                                tableCard = cardUse;
                                colour = tableCard.substring(0, tableCard.indexOf(" "));
                                number = tableCard.substring(tableCard.indexOf(" ") + 1);
                                deck1.remove(cardUse);

                                if (number.equals("Skip")) {
                                    isSkip = true;
                                    break;
                                } else if (number.equals("+2")) {
                                    isSkip = true;
                                    //Add 2 to opponent
                                    System.out.println("+2 to Player Two.");
                                    for (int i = 0; i < 2; i ++) {
                                        deck2.add(mainDeck.get(0));
                                        mainDeck.remove(0);
                                    }
                                    break;
                                } else if (tableCard.equals("Wild +4")) {
                                    isSkip = true;
                                    //Colour Pick
                                    System.out.println("Choose Green, Yellow, Blue, or Red. (Case Sensitive)");
                                        while (true) {
                                            tableCard = input.nextLine();
                                            if (tableCard.equals("Green") || tableCard.equals("Yellow") || tableCard.equals("Blue") || tableCard.equals("Red")) {
                                                colour = tableCard;
                                                break;
                                            } else {
                                                System.out.println("Please enter a valid option!");
                                            }
                                        }
                                        //Add 4 to opponent
                                    System.out.println("+4 to Player Two.");
                                    for (int i = 0; i < 4; i ++) {
                                        deck2.add(mainDeck.get(0));
                                        mainDeck.remove(0);
                                    }
                                    break;
                                } else if (tableCard.contains("Wild Card")) {
                                    //Colour Pick
                                    System.out.println("Choose Green, Yellow, Blue, or Red. (Case Sensitive)");
                                    while (true) {
                                        tableCard = input.nextLine();
                                        if (tableCard.equals("Green") || tableCard.equals("Yellow") || tableCard.equals("Blue") || tableCard.equals("Red")) {
                                            colour = tableCard;
                                            break;
                                        } else {
                                            System.out.println("Please enter a valid option!");
                                        }
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println("Please enter a valid card!");
                                cardUse = input.nextLine();
                            }

                        }
                    } else {
                        System.out.println("No possible card play for Player 1, card drawn from deck");
                        System.out.println(deck1);
                        deck1.add(mainDeck.get(0));
                        mainDeck.remove(0);
                    }
                } while (isSkip);

                System.out.println("The table card is currently: " + tableCard);
                isPossible = false;

                //Turn for Player 2
                do {
                    if (isSkip) {
                        System.out.println("Turn has been skipped! Player 2 go again!");
                        isSkip = false;
                    }

                    for (String card : deck2) {
                        if (card.contains(colour) || card.contains(number) || card.contains("Wild")) {
                            isPossible = true;
                        }
                    }

                    if (isPossible) {
                        System.out.println("Player 2, choose a card you would like to play or draw a card (Case Sensitive):\n" + deck2);
                        cardUse = input.nextLine();

                        while (true) {
                            if (cardUse.contains(colour) || cardUse.contains(number) || cardUse.contains("Wild") && deck2.contains(cardUse)) {
                                tableCard = cardUse;
                                colour = tableCard.substring(0, tableCard.indexOf(" "));
                                number = tableCard.substring(tableCard.indexOf(" ") + 1);
                                deck2.remove(cardUse);

                                if (number.equals("Skip")) {
                                    isSkip = true;
                                    break;
                                } else if (number.equals("+2")) {
                                    isSkip = true;
                                    //Add 2 to opponent
                                    System.out.println("+2 to Player One.");
                                    for (int i = 0; i < 2; i ++) {
                                        deck1.add(mainDeck.get(0));
                                        mainDeck.remove(0);
                                    }
                                    break;
                                } else if (tableCard.equals("Wild +4")) {
                                    isSkip = true;
                                    //Colour Pick
                                    System.out.println("Choose Green, Yellow, Blue, or Red. (Case Sensitive)");
                                    while (true) {
                                        tableCard = input.nextLine();
                                        if (tableCard.equals("Green") || tableCard.equals("Yellow") || tableCard.equals("Blue") || tableCard.equals("Red")) {
                                            colour = tableCard;
                                            break;
                                        } else {
                                            System.out.println("Please enter a valid option!");
                                        }
                                    }
                                    //Add 4 to opponent
                                    System.out.println("+4 to Player One.");
                                    for (int i = 0; i < 4; i ++) {
                                        deck1.add(mainDeck.get(0));
                                        mainDeck.remove(0);
                                    }
                                    break;
                                } else if (tableCard.contains("Wild Card")) {
                                    //Colour Pick
                                    System.out.println("Choose Green, Yellow, Blue, or Red. (Case Sensitive)");
                                    while (true) {
                                        tableCard = input.nextLine();
                                        if (tableCard.equals("Green") || tableCard.equals("Yellow") || tableCard.equals("Blue") || tableCard.equals("Red")) {
                                            colour = tableCard;
                                            break;
                                        } else {
                                            System.out.println("Please enter a valid option!");
                                        }
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println("Please enter a valid card!");
                                cardUse = input.nextLine();
                            }
                        }
                    } else {
                        System.out.println("No possible card play for Player 2, card drawn from deck");
                        System.out.println(deck2);
                        deck2.add(mainDeck.get(0));
                        mainDeck.remove(0);
                    }
                } while (isSkip);
            }




        } else if (isPVP.equalsIgnoreCase("PVE")) {

        }
    }
}