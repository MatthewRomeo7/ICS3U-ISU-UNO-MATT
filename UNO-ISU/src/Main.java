import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean isPlayAgain = true;

        //Main Game Loop
        do {

            //Initialize the Main Deck with Array List.
            ArrayList<String> mainDeck = new ArrayList<>();
            int[] cardNums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

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
            System.out.println("Welcome to Uno! Final Scoring is 50 points per Card.\nWould you like to play against another player, or against AI? (answer with either pvp or pve)");
            String isPVP = input.nextLine();
            boolean isCorrect = false;

            while (!isCorrect) {
                if (!isPVP.equalsIgnoreCase("pvp") && !isPVP.equalsIgnoreCase("pve")) {
                    System.out.println("Please enter a valid option!");
                    isPVP = input.nextLine();
                } else {
                    isCorrect = true;
                }
            }
            //Initialize the table card and make sure it is not a wild card
            String tableCard = "";
            for (int i = 0; i < mainDeck.size(); i++) {
                if (!mainDeck.get(i).equals("Wild Card") || !mainDeck.get(i).equals("Wild +4")) {
                    tableCard = mainDeck.get(i);
                    mainDeck.remove(i);
                    break;
                }
            }
            //Initialize colour and number variables
            String colour = tableCard.substring(0, tableCard.indexOf(" "));
            String number = tableCard.substring(tableCard.indexOf(" ") + 1);

            String cardUse;

            //Initialize variable for skipping turns, possible cards, and if the input is valid for colour
            boolean isSkip = false;
            boolean isPossible = false;

            //Main Game for PVP and PVE
            if (isPVP.equalsIgnoreCase("PVP")) {
                while (true) {

                    //Turn for Player 1
                    //Loop if player skips other player and make sure that the loop does not continue
                    System.out.println("\nThe table card is currently: " + tableCard);

                    do {
                        isPossible = false;

                        //Checks if Skip variable is still active after the card being used, returns value to false if so
                        if (isSkip) {
                            System.out.println("\nTurn has been skipped! Player 1 go again!");
                            isSkip = false;
                        }

                        //Checks if the deck has any possible cards
                        for (String card : deck1) {
                            if (card.contains(colour) || card.contains(number) || card.contains("Wild")) {
                                isPossible = true;
                            }
                        }

                        //Chooses whether to let the player play or force draw a card for them
                        if (isPossible) {
                            System.out.println("Player 1, choose a card you would like to play or draw a card (Case Sensitive):\n" + deck1);
                            cardUse = input.nextLine();

                            //Loop to check if the card that the player wants to use is valid, if so, set the table card to that card,
                            //remove it from the deck, and set the colour and number to the values of that card
                            while (true) {
                                if (cardUse.contains(colour) || cardUse.contains(number) || cardUse.contains("Wild") && deck1.contains(cardUse)) {
                                    tableCard = cardUse;
                                    colour = tableCard.substring(0, tableCard.indexOf(" "));
                                    number = tableCard.substring(tableCard.indexOf(" "));
                                    deck1.remove(cardUse);

                                    //Checks for special properties within the card, and acts accordingly
                                    if (number.equals(" Skip")) {
                                        //Set skip variable to true
                                        isSkip = true;
                                        break;
                                    } else if (number.equals(" +2")) {
                                        isSkip = true;
                                        //Add 2 to opponent
                                        System.out.println("+2 to Player Two.");
                                        for (int i = 0; i < 2; i++) {
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
                                        for (int i = 0; i < 4; i++) {
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
                                        break;
                                    } else {
                                        break;
                                    }
                                } else {
                                    System.out.println("Please enter a valid card!");
                                    cardUse = input.nextLine();
                                }

                            }
                        } else {
                            //Draws a card from the main deck for that player if there is no possible card to play
                            System.out.println("No possible card play for Player 1, card drawn from deck");
                            System.out.println("+" + mainDeck.get(0));
                            deck1.add(mainDeck.get(0));
                            mainDeck.remove(0);
                            System.out.println(deck1 + "\n");
                        }

                        if (deck1.size() == 1) {
                            System.out.println("UNO for Player 1!");
                        }

                    } while (isSkip && !deck1.isEmpty());

                    //Win message
                    if (deck1.isEmpty()) {
                        System.out.println("Player One Wins!");
                        System.out.println("Final Score: " + deck2.size() * 50);
                        break;
                    }

                    System.out.println("\nThe table card is currently: " + tableCard);

                    //Turn for Player 2 (NOTE: ALL CODE IS THE SAME AS PLAYER 1! TO REFER TO WHAT THE CODE DOES LOOK AT THE NOTES FOR PLAYER 1)
                    do {
                        isPossible = false;

                        if (isSkip) {
                            System.out.println("\nTurn has been skipped! Player 2 go again!");
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
                                    number = tableCard.substring(tableCard.indexOf(" "));
                                    deck2.remove(cardUse);

                                    if (number.equals(" Skip")) {
                                        //Set skip variable to true
                                        isSkip = true;
                                        break;
                                    } else if (number.equals(" +2")) {
                                        isSkip = true;
                                        //Add 2 to opponent
                                        System.out.println("+2 to Player One.");
                                        for (int i = 0; i < 2; i++) {
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
                                        for (int i = 0; i < 4; i++) {
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
                                        break;
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
                            System.out.println("+" + mainDeck.get(0));
                            deck2.add(mainDeck.get(0));
                            mainDeck.remove(0);
                            System.out.println(deck2 + "\n");
                        }

                        if (deck2.size() == 1) {
                            System.out.println("UNO for Player 2!");
                        }

                    } while (isSkip && !deck2.isEmpty());

                    if (deck2.isEmpty()) {
                        System.out.println("Player Two Wins!");
                        System.out.println("Final Score: " + deck1.size() * 50);
                        break;
                    }
                }


            } else if (isPVP.equalsIgnoreCase("PVE")) {
                while (true) {

                    //Turn for Player 1 (NOTE: ALL CODE IS THE SAME AS PLAYER 1 IN PVP! TO REFER TO WHAT THE CODE DOES LOOK AT THE NOTES FOR PLAYER 1 IN PVP)
                    System.out.println("\nThe table card is currently: " + tableCard);

                    do {
                        isPossible = false;

                        if (isSkip) {
                            System.out.println("\nTurn has been skipped! Player 1 go again!");
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
                                    number = tableCard.substring(tableCard.indexOf(" "));
                                    deck1.remove(cardUse);

                                    if (number.equals(" Skip")) {
                                        //Set skip variable to true
                                        isSkip = true;
                                        break;
                                    } else if (number.equals(" +2")) {
                                        isSkip = true;
                                        //Add 2 to opponent
                                        System.out.println("+2 to Player Two.");
                                        for (int i = 0; i < 2; i++) {
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
                                        for (int i = 0; i < 4; i++) {
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
                                        break;
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
                            System.out.println("+" + mainDeck.get(0));
                            deck1.add(mainDeck.get(0));
                            mainDeck.remove(0);
                            System.out.println(deck1 + "\n");
                        }

                        if (deck1.size() == 1) {
                            System.out.println("UNO for Player 1!");
                        }

                    } while (isSkip && !deck1.isEmpty());

                    if (deck1.isEmpty()) {
                        System.out.println("Player One Wins!");
                        System.out.println("Final Score: " + deck2.size() * 50);
                        break;
                    }

                    System.out.println("\nThe table card is currently: " + tableCard);

                    //Turn for AI (NOTE: CODE IS MOSTLY THE SAME, ALL CODE THAT DOESN'T HAVE A NOTE HAS BEEN EXPLAINED BEFORE)
                    do {
                        isPossible = false;


                        //Count each colour in the case of wild cards, store in an array. Index 0 is Green, index 1 is Yellow, index 2 is Red, index 3 is Blue
                        int[] colourCounts = new int[4];

                        int greatestCount = 0;
                        String greatestColour = "";

                        for (String card : deck2) {
                            if (card.contains("Green")) {
                                colourCounts[0]++;
                            } else if (card.contains("Yellow")) {
                                colourCounts[1]++;
                            } else if (card.contains("Red")) {
                                colourCounts[2]++;
                            } else if (card.contains("Blue")) {
                                colourCounts[3]++;
                            }
                        }

                        for (int i = 0; i < 4; i++) {
                            if (colourCounts[i] >= greatestCount) {
                                greatestCount = colourCounts[i];
                                switch (colourCounts[i]) {
                                    case 0:
                                        greatestColour = "Green";
                                        break;
                                    case 1:
                                        greatestColour = "Yellow";
                                        break;
                                    case 2:
                                        greatestColour = "Red";
                                        break;
                                    case 3:
                                        greatestColour = "Blue";
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }


                        //Get the possible cards that the AI can use
                        ArrayList<String> possibleCards = new ArrayList<>();

                        //Checks if a Skip Card was used to turn off the isSkip variable in the future
                        if (isSkip) {
                            System.out.println("\nTurn has been skipped! AI go again!");
                            isSkip = false;
                        }

                        //Checks if the deck has any possible cards, and adds any possible cards to an array for the AI to use
                        for (String card : deck2) {
                            if (card.contains(colour) || card.contains(number) || card.contains("Wild")) {
                                isPossible = true;
                                possibleCards.add(card);
                            }
                        }

                        if (isPossible) {

                            while (true) {
                                //Use the first possible card that the AI can use
                                tableCard = possibleCards.get(0);
                                possibleCards.remove(tableCard);
                                deck2.remove(tableCard);
                                colour = tableCard.substring(0, tableCard.indexOf(" "));
                                number = tableCard.substring(tableCard.indexOf(" "));
                                System.out.println("AI has used: " + tableCard);

                                if (number.equals(" Skip")) {
                                    //Set skip variable to true
                                    isSkip = true;
                                    break;
                                } else if (number.equals(" +2")) {
                                    isSkip = true;
                                    //Add 2 to opponent
                                    System.out.println("+2 to Player One.");
                                    for (int i = 0; i < 2; i++) {
                                        deck1.add(mainDeck.get(0));
                                        mainDeck.remove(0);
                                    }
                                    break;
                                } else if (tableCard.equals("Wild +4")) {
                                    isSkip = true;
                                    //Colour Pick for AI (Uses the greatestColour variable to maximise efficiency with that colour)
                                    colour = greatestColour;
                                    tableCard = colour;
                                    System.out.println("The Colour is: " + colour);
                                    //Add 4 to opponent
                                    System.out.println("+4 to Player One.");
                                    for (int i = 0; i < 4; i++) {
                                        deck1.add(mainDeck.get(0));
                                        mainDeck.remove(0);
                                    }
                                    break;
                                } else if (tableCard.contains("Wild Card")) {
                                    //Colour Pick for AI (Uses the greatestColour variable to maximise efficiency with that colour)
                                    colour = greatestColour;
                                    tableCard = colour;
                                    System.out.println("The Colour is: " + colour);
                                    break;
                                } else {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("No possible card play for AI, card drawn from deck");
                            deck2.add(mainDeck.get(0));
                            mainDeck.remove(0);
                        }
                        if (deck2.size() == 1) {
                            System.out.println("UNO for AI!");
                        }
                    } while (isSkip && deck2.isEmpty());

                    if (deck2.isEmpty()) {
                        System.out.println("AI Wins!");
                        System.out.println("Final Score: " + deck1.size() * 50);
                        break;
                    }
                }
            }

            System.out.println("Do you want to play again? (Yes or No)");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("no")) {
                isPlayAgain = false;
            } else if (!answer.equalsIgnoreCase("yes")) {
                System.out.println("Invalid Option, exiting game.");
            }

        } while (isPlayAgain);

        input.close();
    }
}