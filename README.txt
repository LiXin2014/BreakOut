=======================================================================
=                                                                     =
=                                                                     =
=   BreakOut Game                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=======================================================================


What is it?
-----------
This is a project built as a part of course work for Object Oriented Software Development. It allows naive users to play BreakOut game.
Start, pause, undo and reply buttons are implemented. Observer and Command design patterns are used.


Documentation
-------------

Brief of Different calsses and interface
++++++++++++++++++++++++++++
Command
The Command interface is generic for all the commands. It has the execute() and undo() methods for each command. 
CommandManager
This class maintains the command history. It also adds and deletes the commands from the command history linked list.
Invoker
This class invokes the execute() and undo() methods for a command.
UpdateBallNE
This is the command class to store that the ball has moved in the North-East direction or Right-Up direction.
UpdateBallNW
This is the command class to store that the ball has moved in the North-West direction or Left-Up direction.
UpdateBallSE
This is the command class to store that the ball has moved in the South-East direction or Right-Down direction.
UpdateBallSW
This is the command class to store that the ball has moved in the South-West direction or Left-Down direction.
UpdatePaddleLeft
This is the command class to store that the paddle has moved left.
UpdatePaddleRight
This is the command class to store that the paddle has moved right.
UpdateGameTimer
This is the command class to store that there is an update in GameTimer.
Replay
Replay class implements the methods to replay the game since the time Start Button is pressed.
ComponentObserver
Component Observer is the base interface for all the components on the board. It has the getCommand() method. This interface inherits the generic Observer interface.
Observer
Observer is the base interface for all kinds of observers registered in the life of the game. It has the update() method.
