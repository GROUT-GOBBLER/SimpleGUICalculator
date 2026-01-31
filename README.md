# SimpleGUICalculator

A basic Java swing application that allows users to add, subtract, divide, and multiply integers.

Base features: 
 - Buttons for each # (1-9).
 - Buttons for each operation (+,-,*,/).
 - Equals button (=).
 - Clear button (C).
 - Label that shows the operation as user builds it.

Challenges:
 - Need to allow for long operations (ex "5+5/5-5*5") and accurately parse via PEMDAS .
 - I want to use a custom "pretty" Swing theme.

Calculator layout:

    ~BASE~
    [___________]
    1   2   3   +
    4   5   6   -
    7   8   9   *      
    C   0   =   /

Limitations:
 - No negative # entry.
 - No decimal # entry.
 - No single backspace.
 - Only 4 basic operations.
 - Max expression length of 29 characters.