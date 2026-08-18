package com.clearn.app.data.repository

import com.clearn.app.data.model.QuizQuestion
import com.clearn.app.data.model.QuizUnit

class QuizRepository {

    fun getQuizUnits(): List<QuizUnit> {
        return listOf(
            // UNIT 1 QUIZ (10 Questions)
            QuizUnit(
                unitId = 1,
                unitNumber = "Unit 1",
                unitTitle = "Introduction to C Programming",
                questions = listOf(
                    QuizQuestion(
                        id = "q1_1", unitId = 1,
                        questionText = "Who is known as the creator of the C Programming language?",
                        codeSnippet = null,
                        options = listOf("Bjarne Stroustrup", "Dennis Ritchie", "James Gosling", "Guido van Rossum"),
                        correctOptionIndex = 1,
                        explanation = "Dennis Ritchie developed the C language at AT&T Bell Laboratories in 1972."
                    ),
                    QuizQuestion(
                        id = "q1_2", unitId = 1,
                        questionText = "Every standard C program statement must end with which character?",
                        codeSnippet = null,
                        options = listOf("Period (.)", "Colon (:)", "Semicolon (;)", "Comma (,)"),
                        correctOptionIndex = 2,
                        explanation = "In C syntax, a semicolon (;) terminates a statement."
                    ),
                    QuizQuestion(
                        id = "q1_3", unitId = 1,
                        questionText = "What will be the output of the following code?",
                        codeSnippet = "#include <stdio.h>\n\nint main() {\n    int x = 10;\n    printf(\"%d\", x);\n    return 0;\n}",
                        options = listOf("%d", "x", "10", "Error"),
                        correctOptionIndex = 2,
                        explanation = "The format specifier %d prints the integer value stored in variable x, which is 10."
                    ),
                    QuizQuestion(
                        id = "q1_4", unitId = 1,
                        questionText = "Which symbol is required before non-string variable names in scanf()?",
                        codeSnippet = "int age;\nscanf(\"%d\", ___age);",
                        options = listOf("* (Asterisk)", "& (Address-of)", "# (Hash)", "$ (Dollar)"),
                        correctOptionIndex = 1,
                        explanation = "The & (address-of) operator tells scanf() the memory location where it should store the user input."
                    ),
                    QuizQuestion(
                        id = "q1_5", unitId = 1,
                        questionText = "Which header file is required to use printf() and scanf()?",
                        codeSnippet = null,
                        options = listOf("#include <conio.h>", "#include <stdio.h>", "#include <stdlib.h>", "#include <math.h>"),
                        correctOptionIndex = 1,
                        explanation = "<stdio.h> stands for Standard Input Output library header file."
                    ),
                    QuizQuestion(
                        id = "q1_6", unitId = 1,
                        questionText = "What is the size of an 'int' data type in 32-bit GCC compiler?",
                        codeSnippet = null,
                        options = listOf("1 Byte", "2 Bytes", "4 Bytes", "8 Bytes"),
                        correctOptionIndex = 2,
                        explanation = "In standard 32-bit GCC C compilers, an integer occupies 4 bytes (32 bits)."
                    ),
                    QuizQuestion(
                        id = "q1_7", unitId = 1,
                        questionText = "Which format specifier is used to print a floating-point number in C?",
                        codeSnippet = null,
                        options = listOf("%d", "%c", "%f", "%s"),
                        correctOptionIndex = 2,
                        explanation = "%f is used for float numbers, %d for integers, %c for characters, and %s for strings."
                    ),
                    QuizQuestion(
                        id = "q1_8", unitId = 1,
                        questionText = "Which of the following is a valid variable name in C?",
                        codeSnippet = null,
                        options = listOf("int 123num;", "int _total;", "int float;", "int total-marks;"),
                        correctOptionIndex = 1,
                        explanation = "Variable names in C can start with a letter or an underscore (_), but cannot start with digits, contain hyphens, or use reserved keywords."
                    ),
                    QuizQuestion(
                        id = "q1_9", unitId = 1,
                        questionText = "What keyword is used to declare a constant whose value cannot be changed?",
                        codeSnippet = null,
                        options = listOf("constant", "fixed", "const", "static"),
                        correctOptionIndex = 2,
                        explanation = "The 'const' keyword creates read-only variables in C."
                    ),
                    QuizQuestion(
                        id = "q1_10", unitId = 1,
                        questionText = "Where does the execution of every C program begin?",
                        codeSnippet = null,
                        options = listOf("Header files", "start() function", "main() function", "First line of code"),
                        correctOptionIndex = 2,
                        explanation = "Execution ALWAYS starts at the entry point int main()."
                    )
                )
            ),

            // UNIT 2 QUIZ (10 Questions)
            QuizUnit(
                unitId = 2,
                unitNumber = "Unit 2",
                unitTitle = "Operators and Expressions",
                questions = listOf(
                    QuizQuestion(
                        id = "q2_1", unitId = 2,
                        questionText = "What is the result of the modulo expression: 14 % 4 ?",
                        codeSnippet = null,
                        options = listOf("3", "2", "3.5", "0"),
                        correctOptionIndex = 1,
                        explanation = "Modulo (%) calculates remainder after integer division. 14 divided by 4 gives quotient 3 with remainder 2."
                    ),
                    QuizQuestion(
                        id = "q2_2", unitId = 2,
                        questionText = "Which operator has the highest precedence in C arithmetic expressions?",
                        codeSnippet = null,
                        options = listOf("+ and -", "* and /", "Parentheses ()", "= (Assignment)"),
                        correctOptionIndex = 2,
                        explanation = "Parentheses () always take the highest priority, forcing expressions inside them to be evaluated first."
                    ),
                    QuizQuestion(
                        id = "q2_3", unitId = 2,
                        questionText = "What is the output of the ternary operator in this code?",
                        codeSnippet = "int a = 5, b = 10;\nint max = (a > b) ? a : b;\nprintf(\"%d\", max);",
                        options = listOf("5", "10", "1", "0"),
                        correctOptionIndex = 1,
                        explanation = "Since (a > b) i.e. (5 > 10) is false, the ternary operator selects the second value 'b' (10)."
                    ),
                    QuizQuestion(
                        id = "q2_4", unitId = 2,
                        questionText = "What will be the value of x after executing this code?",
                        codeSnippet = "int a = 5;\nint x = a++;",
                        options = listOf("a = 5, x = 5", "a = 6, x = 5", "a = 6, x = 6", "a = 5, x = 6"),
                        correctOptionIndex = 1,
                        explanation = "Post-increment (a++) assigns the current value of 'a' (5) to x first, then increments 'a' to 6."
                    ),
                    QuizQuestion(
                        id = "q2_5", unitId = 2,
                        questionText = "What does the Logical AND operator (&&) evaluate to if one operand is 0 (false)?",
                        codeSnippet = null,
                        options = listOf("1 (True)", "0 (False)", "-1", "Error"),
                        correctOptionIndex = 1,
                        explanation = "Logical AND (&&) requires BOTH operands to be true. If either operand is false (0), the result is 0."
                    ),
                    QuizQuestion(
                        id = "q2_6", unitId = 2,
                        questionText = "What is the result of the bitwise left shift expression: 5 << 1 ?",
                        codeSnippet = null,
                        options = listOf("2.5", "5", "10", "20"),
                        correctOptionIndex = 2,
                        explanation = "Left shift (<< 1) multiplies an integer by 2. Binary 5 (0101) shifted left becomes 10 (1010)."
                    ),
                    QuizQuestion(
                        id = "q2_7", unitId = 2,
                        questionText = "Which shorthand assignment operator adds 5 to variable 'x'?",
                        codeSnippet = null,
                        options = listOf("x =+ 5;", "x += 5;", "x ++ 5;", "x = 5+;"),
                        correctOptionIndex = 1,
                        explanation = "x += 5 is equivalent to x = x + 5."
                    ),
                    QuizQuestion(
                        id = "q2_8", unitId = 2,
                        questionText = "What will integer division 7 / 2 evaluate to in C?",
                        codeSnippet = null,
                        options = listOf("3.5", "3", "4", "3.0"),
                        correctOptionIndex = 1,
                        explanation = "In C, dividing two integers truncates decimal points, returning integer 3."
                    ),
                    QuizQuestion(
                        id = "q2_9", unitId = 2,
                        questionText = "How do you explicitly typecast integer 15 to float in (15 / 2)?",
                        codeSnippet = null,
                        options = listOf("(float) 15 / 2", "float(15) / 2", "15 / float(2)", "cast(float, 15)"),
                        correctOptionIndex = 0,
                        explanation = "(float) 15 / 2 explicitly converts 15 to 15.0f, giving result 7.5."
                    ),
                    QuizQuestion(
                        id = "q2_10", unitId = 2,
                        questionText = "What is the value of expression: 5 + 3 * 2 ?",
                        codeSnippet = null,
                        options = listOf("16", "11", "13", "10"),
                        correctOptionIndex = 1,
                        explanation = "Multiplication (*) has higher precedence than addition (+). 3 * 2 = 6, then 5 + 6 = 11."
                    )
                )
            ),

            // UNIT 3 QUIZ (10 Questions)
            QuizUnit(
                unitId = 3,
                unitNumber = "Unit 3",
                unitTitle = "Decision Making and Branching",
                questions = listOf(
                    QuizQuestion(
                        id = "q3_1", unitId = 3,
                        questionText = "Which statement is used inside switch-case to prevent falling into subsequent cases?",
                        codeSnippet = null,
                        options = listOf("exit;", "stop;", "break;", "continue;"),
                        correctOptionIndex = 2,
                        explanation = "'break;' exits the switch statement immediately after executing a matching case."
                    ),
                    QuizQuestion(
                        id = "q3_2", unitId = 3,
                        questionText = "What will be printed by this code?",
                        codeSnippet = "#include <stdio.h>\n\nint main() {\n    int score = 45;\n    if (score >= 50) {\n        printf(\"Pass\");\n    } else {\n        printf(\"Fail\");\n    }\n    return 0;\n}",
                        options = listOf("Pass", "Fail", "PassFail", "Nothing"),
                        correctOptionIndex = 1,
                        explanation = "Since 45 >= 50 is false, execution enters the else block and prints 'Fail'."
                    ),
                    QuizQuestion(
                        id = "q3_3", unitId = 3,
                        questionText = "What case block in a switch statement runs if no case matches?",
                        codeSnippet = null,
                        options = listOf("else:", "other:", "default:", "final:"),
                        correctOptionIndex = 2,
                        explanation = "The 'default:' case handles any unmatched values in a switch statement."
                    ),
                    QuizQuestion(
                        id = "q3_4", unitId = 3,
                        questionText = "What will be the output of this nested if code?",
                        codeSnippet = "int x = 10, y = 20;\nif (x > 5) {\n    if (y < 15) printf(\"A\");\n    else printf(\"B\");\n}",
                        options = listOf("A", "B", "AB", "No Output"),
                        correctOptionIndex = 1,
                        explanation = "x > 5 is true (10 > 5), entering inner if. y < 15 is false (20 < 15), printing 'B'."
                    ),
                    QuizQuestion(
                        id = "q3_5", unitId = 3,
                        questionText = "Which expression checks if 'marks' is between 40 and 100 inclusive?",
                        codeSnippet = null,
                        options = listOf("marks >= 40 || marks <= 100", "40 <= marks <= 100", "marks >= 40 && marks <= 100", "marks (40..100)"),
                        correctOptionIndex = 2,
                        explanation = "Logical AND (&&) verifies both boundary conditions: marks >= 40 AND marks <= 100."
                    ),
                    QuizQuestion(
                        id = "q3_6", unitId = 3,
                        questionText = "What condition evaluates to True in C?",
                        codeSnippet = null,
                        options = listOf("Any non-zero value", "Only number 1", "Only number 100", "Negative numbers are false"),
                        correctOptionIndex = 0,
                        explanation = "In C, 0 represents False, and ANY non-zero value (1, -5, 42) represents True."
                    ),
                    QuizQuestion(
                        id = "q3_7", unitId = 3,
                        questionText = "What will happen if you omit 'break' statements in a switch-case block?",
                        codeSnippet = null,
                        options = listOf("Syntax Error", "Fall-through into next cases", "Program crashes", "Only default runs"),
                        correctOptionIndex = 1,
                        explanation = "Without break, C continues executing subsequent case statements (fall-through behavior)."
                    ),
                    QuizQuestion(
                        id = "q3_8", unitId = 3,
                        questionText = "What will be printed?",
                        codeSnippet = "int num = 0;\nif (num = 5) {\n    printf(\"True\");\n} else {\n    printf(\"False\");\n}",
                        options = listOf("False", "True", "Compilation Error", "0"),
                        correctOptionIndex = 1,
                        explanation = "num = 5 is an assignment returning non-zero 5 (True), so it prints 'True'! (Always use == for equality)."
                    ),
                    QuizQuestion(
                        id = "q3_9", unitId = 3,
                        questionText = "What data types are supported as switch-case test expressions?",
                        codeSnippet = null,
                        options = listOf("int and char only", "float and double only", "strings only", "all data types"),
                        correctOptionIndex = 0,
                        explanation = "switch-case expressions in C must evaluate to integer or character constant values."
                    ),
                    QuizQuestion(
                        id = "q3_10", unitId = 3,
                        questionText = "What statement unconditionally jumps execution to a labeled line in C?",
                        codeSnippet = null,
                        options = listOf("jump", "goto", "skip", "pass"),
                        correctOptionIndex = 1,
                        explanation = "The 'goto label;' statement performs an unconditional jump."
                    )
                )
            ),

            // UNIT 4 QUIZ (10 Questions)
            QuizUnit(
                unitId = 4,
                unitNumber = "Unit 4",
                unitTitle = "Looping Constructs",
                questions = listOf(
                    QuizQuestion(
                        id = "q4_1", unitId = 4,
                        questionText = "Which loop in C is guaranteed to execute its body at least once?",
                        codeSnippet = null,
                        options = listOf("for loop", "while loop", "do-while loop", "nested loop"),
                        correctOptionIndex = 2,
                        explanation = "The do-while loop evaluates its condition at the end of the loop, ensuring at least one execution."
                    ),
                    QuizQuestion(
                        id = "q4_2", unitId = 4,
                        questionText = "How many times will this for loop execute?",
                        codeSnippet = "for (int i = 0; i < 5; i++) { }",
                        options = listOf("4 times", "5 times", "6 times", "Infinite"),
                        correctOptionIndex = 1,
                        explanation = "i starts at 0 and runs for i=0, 1, 2, 3, 4 (5 times total)."
                    ),
                    QuizQuestion(
                        id = "q4_3", unitId = 4,
                        questionText = "Which statement skips the rest of the current iteration and jumps to next loop cycle?",
                        codeSnippet = null,
                        options = listOf("break;", "continue;", "exit;", "skip;"),
                        correctOptionIndex = 1,
                        explanation = "'continue;' skips remaining code in the current iteration and proceeds to the next cycle."
                    ),
                    QuizQuestion(
                        id = "q4_4", unitId = 4,
                        questionText = "What happens if a loop condition is never false (e.g. while(1))?",
                        codeSnippet = null,
                        options = listOf("Syntax error", "Infinite loop", "Loop runs 100 times", "Program closes"),
                        correctOptionIndex = 1,
                        explanation = "while(1) creates an infinite loop because condition 1 (True) never becomes 0."
                    ),
                    QuizQuestion(
                        id = "q4_5", unitId = 4,
                        questionText = "What will be the output of this loop?",
                        codeSnippet = "int i = 1;\ndo {\n    printf(\"%d \", i);\n    i++;\n} while (i <= 3);",
                        options = listOf("1 2 3", "1 2", "0 1 2", "3 2 1"),
                        correctOptionIndex = 0,
                        explanation = "The do-while loop prints i=1, 2, 3 before i becomes 4 and condition i<=3 becomes false."
                    ),
                    QuizQuestion(
                        id = "q4_6", unitId = 4,
                        questionText = "In a for loop (init; condition; update), which part executes first?",
                        codeSnippet = null,
                        options = listOf("condition", "init", "update", "loop body"),
                        correctOptionIndex = 1,
                        explanation = "Initialization (init) runs once at the very beginning of the for loop."
                    ),
                    QuizQuestion(
                        id = "q4_7", unitId = 4,
                        questionText = "What will be printed?",
                        codeSnippet = "for(int i=1; i<=5; i++) {\n    if(i == 3) break;\n    printf(\"%d \", i);\n}",
                        options = listOf("1 2 3 4 5", "1 2", "1 2 4 5", "3"),
                        correctOptionIndex = 1,
                        explanation = "When i becomes 3, break terminates the loop immediately, so only 1 and 2 are printed."
                    ),
                    QuizQuestion(
                        id = "q4_8", unitId = 4,
                        questionText = "What is a loop placed inside another loop called?",
                        codeSnippet = null,
                        options = listOf("Double loop", "Nested loop", "Inner loop", "Sub-loop"),
                        correctOptionIndex = 1,
                        explanation = "A loop written inside the body of another loop is called a Nested Loop."
                    ),
                    QuizQuestion(
                        id = "q4_9", unitId = 4,
                        questionText = "If an outer loop runs 3 times and inner loop runs 4 times, how many total inner iterations occur?",
                        codeSnippet = null,
                        options = listOf("7 times", "12 times", "4 times", "3 times"),
                        correctOptionIndex = 1,
                        explanation = "Total inner iterations = 3 * 4 = 12 times."
                    ),
                    QuizQuestion(
                        id = "q4_10", unitId = 4,
                        questionText = "Which loop control expression increments 'i' by 2 in each step?",
                        codeSnippet = null,
                        options = listOf("i++", "i + 2", "i += 2", "i = 2"),
                        correctOptionIndex = 2,
                        explanation = "i += 2 adds 2 to 'i' in each iteration."
                    )
                )
            )
        )
    }

    fun getQuizUnitById(unitId: Int): QuizUnit? {
        return getQuizUnits().find { it.unitId == unitId }
    }
}
