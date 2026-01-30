# SimpleGUICalculator

A basic Java swing application that allows users to add, subtract, divide, and multiply numbers.

Base features: 
 - Buttons for each # (1-9).
 - Buttons for each operation (+,-,*,/).
 - Equals button (=).
 - Clear button (C).
 - Label that shows the operation as user builds it.

Optional feature: 
 - Backspace button.
 - Decimal point button.
 - Positive or negative selection button.   (!!! Not sure about this one. !!!)
 - Allow user to type in operation manually without using buttons.
 - Light-mode, dark-mode theme switcher button.


Challenges:
 - Need to allow for long operations (ex "5+5+5/5-5+5+5*5").
 - I want to use a custom "pretty" theme.
 - Learn how to use the GridBag layout in Java swing for more precise app layout.


Calculator layout:

    ~BASE~
    [___________]
    1   2   3   +
    4   5   6   -
    7   8   9   *      
    C   0   =   /

    ~OPTIONAL~
    [_______________________]
    <X]     C       (L/D)   /
    1       2       3       +
    4       5       6       -
    7       8       9       *      
    (+/-)   0       .       =
