This program is run with a file path for an input file as the only argument and will output to consol.

Assumptions
1. Multiple Rovers cannot occupy the same space if one would move into an occupied space.
2. All rovers are in their original positions at the beginning rather than being placed just before they move.
3. I've assumed fairly exactly the file formatting. If this were a real project I would base how strict I was being and what possible variations to expect and correct for on how the file in generated. Since I don’t have that information I’m going to assume consistency.
The first line of the input file will be two integers separated by a space to give the dimensions of the grid.
Then there will be any amount of rovers in sequence each represented by two lines the first being two integers for starting position then either the letter N E S or W for facing separated by spaces.
The second line for each rover will be a strings made up of the letters MLR with no spaces.

e.g.
5 5
1 2 N 
LMLMLMLMM 
3 3 E 
MMRMMRMRRM
