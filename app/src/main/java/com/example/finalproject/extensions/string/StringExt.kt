package com.example.finalproject.extensions.string

/*
Extension function
Extension functions allow adding functionality to other types, even those defined outside of
your own project.
In this case, we are adding an additional function for `String`.
This function can be called on any string.
As long as the function is imported and its accessibility modifiers allow,
it can be used on strings anywhere in the code.

Extension functions are declared by prepending the name of the type being extended to the
function name. (In this case, `String`).

You can call other functions from the originating type in the extension function.
You do not need to prepend the type name.
Here, we are directly calling `replaceFirstChar`, a function of `String`.

In addition, inside extension functions, the value that the function is being called on is
accessible via `this`.
For instance, if we called...
"some word".upperCaseFirstChar()
...the value of this `this` would be a String containing "some word".
https://kotlinlang.org/docs/extensions.html#extension-functions
*/
/*
Single-expression function
The equals sign indicates that this is a single-expression function.
This syntax allows for more concise function declarations.
For such functions, the return type can be inferred from the result of the expression,
as is the case here.
https://kotlinlang.org/docs/functions.html#single-expression-functions
*/
fun String.upperFirstChar() = replaceFirstChar(Char::titlecase)
