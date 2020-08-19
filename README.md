# shadow-block-game

Shadow Blocks is a puzzle game where the player must solve block-based puzzles to advance deeper into the caves, while avoiding monsters that guard its depths. There are six levels in the game, each introducing new elements for the player to interact with. Broadly speaking, the game is divided into blocks that the player can interact with by pushing, tiles that have no active component yet add rules to the game, and units such as the player or the monsters that can wander each level.

This is a real-time game; objects may move or change state even without the player pressing any keys. The game takes place in frames, at least sixty per second. On each frame:
1. All game objects have a chance to move. Units can move freely, whereas blocks can only move in a way dictated by their interaction with units.
2. If all blocks have reached their targets, the level is over and the game loads the next level.
3. The entire screen is “rendered”, so that all previous drawing is cleared and the display reflects the current state of the game.
The player also has the opportunity to restart the current level by pressing R, and to undo recent moves by pressing Z. When the player undoes a move, all blocks should move to the position they were before the player last moved. The player should then return to the position they were before they last moved.
In addition, the number of moves the player has made during this level should be recorded and displayed in the top left corner. This counter should decrease when the player undoes a move.
