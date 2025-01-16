# DOS - UNO FOR TWO PLAYERS
Ever play the card game UNO? Well now you can play it digitally in a local, two player way! My code allows you to interact with a friend, or an AI that can play UNO with you!

What I do know is that UNO rules can sometimes be very vague between one another so here is a basic breakdown of the rules in this code:
* Player One and Player 2/AI are both given 7 cards to start with.
* Player One will always start the game, you can decide who is Player One between yourselves.
* If the player has a card that matches number or colour of the table card, the player can play that card, otherwise they must draw a card and lose their turn regardless if they pick up a usable card.
* Skip Cards will skip the opponent.
* Any + Card will add cards to and skip the opponent.
* Wild Cards Allow you to change the colour of the table.
* First Player to have no cards left wins, an UNO alert will be made to the player with one card left so pay attention!
* Final Score will display based on the amount of cards that the losing player has. E.g. If the losing player had 10 cards left, the final score is 500 at 50 points per card.

As for the actual code/process, here are some important notes to consider:
    - Decks are initialized just like a basic deck of UNO, just without Reverse Cards.
    - Each Deck will be given seven cards off the beginning of an array holding all the cards.
    - It is impossible for a game to start with a Wild Card, there is specific code ensuring that. The first card is still randomly chosen from the deck.
    - You must choose between PVP or PVE at the initial start of the code, this is where you choose to play against another player or an AI.
    - If you have a possible card to play, you must play it. Draws are done automatically if there are no possible cards.
    - Don't worry about time limits, but spelling must be EXACTLY like the card you want to play during input, the code will not recognize it as valid if it isn't.
    - The game ends when a player has no cards left, when you can be asked to play the game again without restarting the code.

And finally, the rules that the AI operates by:
    - Will choose the first possible card it has in its deck.
    - Will draw if there are no possible cards in the deck.
    - Wild Card colours are decided based on the amount of cards of a specific colour the AI has. E.g. If the AI mostly has Yellow cards, it will choose Yellow.

That's all! Be sure to grab a friend to try this with, or challenge yourself against the AI, in the end, the code's purpose is just for you to have fun!
