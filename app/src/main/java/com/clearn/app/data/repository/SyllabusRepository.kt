package com.clearn.app.data.repository

import com.clearn.app.data.model.DiplomaUnit
import com.clearn.app.data.model.MicroLesson
import com.clearn.app.data.model.Topic

class SyllabusRepository {

    fun getDiplomaUnits(): List<DiplomaUnit> {
        return listOf(
            // UNIT 1
            DiplomaUnit(
                id = 1,
                unitNumber = "Unit 1",
                title = "Introduction to C Programming",
                description = "Fundamentals of C language, program structure, compilation flow, variables, data types & I/O.",
                topics = listOf(
                    Topic(
                        id = "u1_t0",
                        unitId = 1,
                        title = "Pre-Topic: What is a Programming Language?",
                        estimatedMinutes = 3,
                        microLesson = MicroLesson(
                            topicId = "u1_t0",
                            title = "Pre-Topic: What is a Programming Language?",
                            explanation = "Think of a computer as a super-fast worker that only understands binary signals (0s and 1s). Since writing 0s and 1s is very difficult for humans, we use a 'Programming Language' like C. It lets us write instructions in clear English-like words, which get translated so the computer can execute them!",
                            hinglishExplanation = "Computer ek super-fast worker jaisa hai jo sirf Binary (0s aur 1s) samajhta hai. Kyuki 0s aur 1s me code likhna bohot mushkil hai, hum C jaise 'Programming Language' ka use karte hain. Isse hum simple English words me instructions likhte hain jo baad me machine code me translate ho jate hain!",
                            codeSnippet = null,
                            keyTakeaways = listOf(
                                "Computers understand binary (0 and 1).",
                                "Programming languages bridge human thinking and computer execution.",
                                "C is a foundational language used for systems, compilers, and microcontrollers."
                            )
                        )
                    ),
                    Topic(
                        id = "u1_t1",
                        unitId = 1,
                        title = "History and Features of C",
                        estimatedMinutes = 4,
                        microLesson = MicroLesson(
                            topicId = "u1_t1",
                            title = "History and Features of C",
                            explanation = "C was created by Dennis Ritchie in 1972 at AT&T Bell Laboratories. It was developed to build the UNIX Operating System. C is known as a procedural, middle-level language because it combines high-level ease of writing with low-level direct memory manipulation.",
                            hinglishExplanation = "C ko Dennis Ritchie ne 1972 me AT&T Bell Labs me banaya tha UNIX Operating System likhne ke liye. C ko Middle-Level language bolte hain kyunki ye High-Level logic aur Low-Level RAM memory control dono ko combine karta hai!",
                            codeSnippet = null,
                            keyTakeaways = listOf(
                                "Created by Dennis Ritchie at Bell Labs in 1972.",
                                "Middle-level language (combines high-level logic with memory access).",
                                "Fast, portable, and extremely popular in systems engineering."
                            )
                        )
                    ),
                    Topic(
                        id = "u1_t2",
                        unitId = 1,
                        title = "Structure of a C Program",
                        estimatedMinutes = 4,
                        microLesson = MicroLesson(
                            topicId = "u1_t2",
                            title = "Structure of a C Program",
                            explanation = "Every C program follows a standard template:\n1. Header Files (#include <stdio.h>): Imports built-in functions like printf.\n2. main() Function: The starting point where execution begins.\n3. Statements & Semicolons: Instructions ending with ';'.\n4. return 0;: Indicates success to the operating system.",
                            hinglishExplanation = "Har C program ka ek fixed format hota hai:\n1. #include <stdio.h>: printf aur scanf jaise tools ko import karta hai.\n2. main() function: Ye C program ka entry gate hai jaha se code chalna start hota hai.\n3. Semicolon (;): Har statement ke end me ';' lagana compulsory hai.\n4. return 0;: Program successfully khatam ho gaya ye OS ko batata hai.",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    // Print welcome message\n    printf(\"Welcome to C Programming!\\n\");\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Execution ALWAYS starts inside main().",
                                "Every statement MUST end with a semicolon (;).",
                                "#include <stdio.h> provides standard input/output functions."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Structure of a C Program visual blueprint"
                        )
                    ),
                    Topic(
                        id = "u1_t3",
                        unitId = 1,
                        title = "Compilation & Execution Process",
                        estimatedMinutes = 4,
                        microLesson = MicroLesson(
                            topicId = "u1_t3",
                            title = "Compilation & Execution Process",
                            explanation = "How does your source code (.c) become a running app (.exe)? It goes through 4 phases:\n1. Preprocessing: Expands macros and #include files.\n2. Compilation: Converts C code into Assembly code.\n3. Assembly: Converts Assembly code into Machine Code (.obj).\n4. Linking: Combines object files with standard libraries into an Executable (.exe).",
                            hinglishExplanation = "Aapka C code (.c) running app (.exe) kaise banta hai? Ye 4 steps me hota hai:\n1. Preprocessor: #include files ko expand karta hai.\n2. Compiler: C code ko Assembly code me convert karta hai.\n3. Assembler: Assembly ko Machine Code (.obj) me badalta hai.\n4. Linker: Sare object files aur standard libraries ko jod kar final .exe file banata hai!",
                            codeSnippet = "// Command line compilation using GCC:\n// gcc hello.c -o hello.exe\n// ./hello.exe",
                            keyTakeaways = listOf(
                                "Source Code (.c) -> Preprocessor -> Compiler -> Assembler -> Linker -> Executable (.exe).",
                                "Compilers catch syntax errors before the program runs."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Step-by-step Compilation Flow"
                        )
                    ),
                    Topic(
                        id = "u1_t4",
                        unitId = 1,
                        title = "Variables, Constants & Data Types",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u1_t4",
                            title = "Variables, Constants & Data Types",
                            explanation = "A Variable is a named storage container in computer memory. A Data Type tells the compiler what kind of data the container holds:\n- int: Whole numbers (e.g. 25)\n- float: Decimals (e.g. 85.5f)\n- double: Precise decimals\n- char: Single character inside single quotes (e.g. 'A')\nConstants are fixed values that cannot be changed during program execution (using const or #define).",
                            hinglishExplanation = "Variable ek dabba (container) hai computer ki RAM memory me jaha data store hota hai. Data Type ye batata hai ki dabbe me kis type ka data rakha hai:\n- int: Normal whole numbers (jaise 25)\n- float: Point vale numbers (jaise 85.5)\n- char: Single character ('A', 'B')\nConst values kabhi change nahi hoti!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int age = 18;\n    float percentage = 88.5;\n    char grade = 'A';\n    const float PI = 3.14159;\n\n    printf(\"Age: %d, Grade: %c\\n\", age, grade);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "int for integers, float/double for decimals, char for characters.",
                                "Use 'const' keyword to create unchangeable variables."
                            )
                        )
                    ),
                    Topic(
                        id = "u1_t5",
                        unitId = 1,
                        title = "Input/Output Functions (printf, scanf)",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u1_t5",
                            title = "Input/Output Functions (printf, scanf)",
                            explanation = "To display output to the screen, we use printf(). To accept input from the student keyboard, we use scanf().\nFormat Specifiers:\n- %d : Integer\n- %f : Float\n- %c : Character\n- %s : String\nIMPORTANT: scanf requires the address-of operator '&' before variable names so it knows where to store the input in memory!",
                            hinglishExplanation = "Screen par message dikhane ke liye printf() use hota hai. Student se keyboard input lene ke liye scanf() use hota hai.\nFormat Specifiers:\n- %d : Int value\n- %f : Float value\n- %c : Char value\nIMP: scanf me variable ke aage '&' (address) lagana zaruri hai taaki computer ko pata chale ki data RAM me kaha store karna hai!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int rollNo;\n    printf(\"Enter your Roll Number: \");\n    scanf(\"%d\", &rollNo);\n    printf(\"Your Roll Number is: %d\\n\", rollNo);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "printf() displays output; scanf() reads user input.",
                                "Always use & (address-of operator) inside scanf() for non-string variables."
                            )
                        )
                    )
                )
            ),

            // UNIT 2
            DiplomaUnit(
                id = 2,
                unitNumber = "Unit 2",
                title = "Operators and Expressions",
                description = "Arithmetic, relational, logical, bitwise, assignment & ternary operators, precedence & type casting.",
                topics = listOf(
                    Topic(
                        id = "u2_t1",
                        unitId = 2,
                        title = "Arithmetic, Relational & Logical Operators",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u2_t1",
                            title = "Arithmetic, Relational & Logical Operators",
                            explanation = "Operators are symbols that perform operations on values (operands):\n- Arithmetic: +, -, *, /, % (Modulo returns remainder: 10 % 3 = 1)\n- Relational: ==, !=, >, <, >=, <= (Returns 1 for true, 0 for false)\n- Logical: && (AND - both true), || (OR - at least one true), ! (NOT - reverses truth).",
                            hinglishExplanation = "Operators woh symbols hain jo variables par calculations karte hain:\n- Arithmetic: +, -, *, /, % (Modulo % bacha hua remainder deta hai: 10 % 3 = 1)\n- Relational: ==, !=, >, < (Sahi hone par 1 aur galat hone par 0 deta hai)\n- Logical: && (Dono sahi to true), || (Koi ek sahi to true).",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int a = 10, b = 3;\n    printf(\"Remainder: %d\\n\", a % b);\n    printf(\"Is a greater than b? %d\\n\", a > b);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "% (Modulo) gives remainder after division.",
                                "Relational and logical expressions evaluate to 1 (true) or 0 (false)."
                            )
                        )
                    ),
                    Topic(
                        id = "u2_t2",
                        unitId = 2,
                        title = "Bitwise, Assignment & Conditional Operators",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u2_t2",
                            title = "Bitwise, Assignment & Conditional Operators",
                            explanation = "Bitwise operators perform operations directly on binary bits (& AND, | OR, ^ XOR, ~ NOT, << Left Shift, >> Right Shift).\nAssignment shortcuts: +=, -=, *=, /=.\nTernary/Conditional Operator (?:): A compact one-line if-else!\nSyntax: (condition) ? value_if_true : value_if_false;",
                            hinglishExplanation = "Bitwise operators directly binary bits (0s aur 1s) par kaam karte hain.\nTernary Operator (?:) ek short 1-line if-else statement hai:\n(Condition) ? sahi_hone_par : galat_hone_par;",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int num = 7;\n    // Check even/odd using ternary operator\n    (num % 2 == 0) ? printf(\"Even\\n\") : printf(\"Odd\\n\");\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Bitwise operators operate directly on binary 0s and 1s.",
                                "(condition) ? true_val : false_val is a shorthand for simple if-else."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Bitwise shift and AND/OR bit visualization"
                        )
                    ),
                    Topic(
                        id = "u2_t3",
                        unitId = 2,
                        title = "Operator Precedence & Type Casting",
                        estimatedMinutes = 4,
                        microLesson = MicroLesson(
                            topicId = "u2_t3",
                            title = "Operator Precedence & Type Casting",
                            explanation = "Operator Precedence determines which operator runs first in an expression (e.g. * and / run before + and -).\nType Casting is converting one data type to another:\n- Implicit Casting: Done automatically by compiler (e.g. int + float -> float)\n- Explicit Casting: Done manually by coder: (float) total / count.",
                            hinglishExplanation = "Precedence batata hai ki kaunsa operator pehle solve hoga (jaise * aur / pehle hote hain, + aur - baad me).\nType Casting ka matlab ek data type ko dusre me convert karna hai: (float) total / count lagane se decimal values point me aati hain!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int total = 15, count = 2;\n    float average = (float) total / count;\n    printf(\"Average: %.2f\\n\", average);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Parentheses () have the highest priority in mathematical expressions.",
                                "Explicit type casting (type) prevents integer division loss (15/2 -> 7.5 instead of 7)."
                            )
                        )
                    )
                )
            ),

            // UNIT 3
            DiplomaUnit(
                id = 3,
                unitNumber = "Unit 3",
                title = "Decision Making and Branching",
                description = "if, if-else, nested if-else, switch-case, and conditional control flow.",
                topics = listOf(
                    Topic(
                        id = "u3_t1",
                        unitId = 3,
                        title = "if, if-else & Nested if-else",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u3_t1",
                            title = "if, if-else & Nested if-else",
                            explanation = "Decision making allows your C program to choose different paths based on conditions.\n- if statement: Executes a code block only if condition is true.\n- if-else statement: Chooses Path A if true, Path B if false.\n- nested if-else: An if statement placed inside another if statement.",
                            hinglishExplanation = "Decision making se aapka C program condition dekh kar rasta chunta hai:\n- if: Agar condition true hai tabhi code chalega.\n- if-else: Sahi hone par Option A, galat hone par Option B chalega.",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int marks = 75;\n    if (marks >= 40) {\n        printf(\"Pass!\\n\");\n        if (marks >= 70) {\n            printf(\"Distinction!\\n\");\n        }\n    } else {\n        printf(\"Fail\\n\");\n    }\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "if block runs when condition evaluates to true (non-zero).",
                                "else block runs when condition evaluates to false (zero)."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Branching Flowchart for if-else"
                        )
                    ),
                    Topic(
                        id = "u3_t2",
                        unitId = 3,
                        title = "switch-case Statement",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u3_t2",
                            title = "switch-case Statement",
                            explanation = "When testing a variable against many possible integer or character values, switch-case is cleaner than multiple else-if statements.\nIMPORTANT:\n- Each case MUST end with a 'break;' statement to prevent falling through into the next case.\n- 'default:' runs if no case matches.",
                            hinglishExplanation = "Jab 1 variable ko bohot sari integer/character values se check karna ho, tab switch-case use karte hain.\nIMP: Har case ke end me 'break;' zaroor lagaye taaki agla case bina matlab ke na chale!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int day = 2;\n    switch(day) {\n        case 1: printf(\"Monday\\n\"); break;\n        case 2: printf(\"Tuesday\\n\"); break;\n        case 3: printf(\"Wednesday\\n\"); break;\n        default: printf(\"Other day\\n\");\n    }\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "switch works with integer and character constants.",
                                "Always use 'break' after each case to avoid fall-through."
                            )
                        )
                    )
                )
            ),

            // UNIT 4
            DiplomaUnit(
                id = 4,
                unitNumber = "Unit 4",
                title = "Looping Constructs",
                description = "for, while, do-while loops, nested loops, break, continue & goto.",
                topics = listOf(
                    Topic(
                        id = "u4_t1",
                        unitId = 4,
                        title = "for, while & do-while Loops",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u4_t1",
                            title = "for, while & do-while Loops",
                            explanation = "Loops repeat a block of code multiple times until a condition becomes false.\n1. for loop: Best when exact repetition count is known. (initialization; condition; update)\n2. while loop: Entry-controlled loop. Checks condition before running body.\n3. do-while loop: Exit-controlled loop. Executes body AT LEAST ONCE before checking condition.",
                            hinglishExplanation = "Loops ek hi code block ko bar-bar repeat karne ke liye use hote hain:\n1. for loop: Jab pata ho ki loop kitni baar chalana hai.\n2. while loop: Pehle condition check karta hai, fir andar jata hai.\n3. do-while loop: Kam se kam 1 baar zaroor chalega, chahe condition galat hi kyu na ho!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    printf(\"for loop:\\n\");\n    for (int i = 1; i <= 3; i++) {\n        printf(\"%d \", i);\n    }\n    \n    printf(\"\\ndo-while loop:\\n\");\n    int j = 1;\n    do {\n        printf(\"%d \", j);\n        j++;\n    } while (j <= 3);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "for loop: (init; condition; update).",
                                "while checks before executing; do-while executes at least once."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Loop execution cycle visualizer"
                        )
                    ),
                    Topic(
                        id = "u4_t2",
                        unitId = 4,
                        title = "Nested Loops, break, continue & goto",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u4_t2",
                            title = "Nested Loops, break, continue & goto",
                            explanation = "- Nested Loops: A loop inside another loop (commonly used for 2D grids & matrices).\n- break: Exits the loop immediately.\n- continue: Skips the rest of the current iteration and jumps to the next loop cycle.\n- goto: Unconditional jump to a labeled statement (use sparingly!).",
                            hinglishExplanation = "- break: Pura loop turant band kar deta hai.\n- continue: Sirf current cycle skip karta hai aur agli cycle par jump karta hai.",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    for (int i = 1; i <= 5; i++) {\n        if (i == 3) continue; // Skip 3\n        if (i == 5) break;    // Stop at 5\n        printf(\"%d \", i);\n    }\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "break terminates the entire loop.",
                                "continue skips only the current iteration."
                            )
                        )
                    )
                )
            ),

            // UNIT 5
            DiplomaUnit(
                id = 5,
                unitNumber = "Unit 5",
                title = "Functions",
                description = "Function definition, parameter passing (by-value vs by-reference), recursion & scope.",
                topics = listOf(
                    Topic(
                        id = "u5_t1",
                        unitId = 5,
                        title = "Function Definition, Declaration & Calling",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u5_t1",
                            title = "Function Definition, Declaration & Calling",
                            explanation = "A Function is a reusable block of code designed to perform a specific task.\n1. Declaration (Prototype): Tells compiler function name and parameter types.\n2. Definition: Contains the actual code body.\n3. Call: Executes the function from main().",
                            hinglishExplanation = "Function ek reusable code block hota hai jo ek specific kaam karta hai. Isse code clean aur modular ho jata hai!",
                            codeSnippet = "#include <stdio.h>\n\n// Function Prototype\nint multiply(int a, int b);\n\nint main() {\n    int result = multiply(4, 5); // Call\n    printf(\"Product: %d\\n\", result);\n    return 0;\n}\n\n// Definition\nint multiply(int a, int b) {\n    return a * b;\n}",
                            keyTakeaways = listOf(
                                "Functions make code modular and easy to debug.",
                                "return statement sends data back to the caller."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Function Call Stack & Memory Frame"
                        )
                    ),
                    Topic(
                        id = "u5_t2",
                        unitId = 5,
                        title = "Call by Value vs Call by Reference & Recursion",
                        estimatedMinutes = 6,
                        microLesson = MicroLesson(
                            topicId = "u5_t2",
                            title = "Call by Value vs Call by Reference & Recursion",
                            explanation = "- Call by Value: Passes a copy of data. Original variable remains unchanged.\n- Call by Reference: Passes the memory address using pointers (*). Original variable gets modified!\n- Recursion: A function that calls ITSELF. Must have a base condition to avoid infinite call stacks!",
                            hinglishExplanation = "- Call by Value: Data ki duplicate copy bhejta hai, main variable me koi change nahi hota.\n- Call by Reference: Pointers ke dwara RAM address bhejta hai, jisse original variable modify ho jata hai!\n- Recursion: Jab function khud ko hi bar-bar call kare.",
                            codeSnippet = "#include <stdio.h>\n\n// Recursive Factorial\nint factorial(int n) {\n    if (n <= 1) return 1; // Base condition\n    return n * factorial(n - 1);\n}\n\nint main() {\n    printf(\"Factorial of 5: %d\\n\", factorial(5));\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Call by value passes copies; Call by reference passes memory addresses.",
                                "Recursion MUST have a base condition to terminate."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Parameter passing & Recursion Stack visualizer"
                        )
                    )
                )
            ),

            // UNIT 6
            DiplomaUnit(
                id = 6,
                unitNumber = "Unit 6",
                title = "Arrays and Strings",
                description = "1D & 2D arrays, string handling (strlen, strcpy, strcmp, strcat, etc.).",
                topics = listOf(
                    Topic(
                        id = "u6_t1",
                        unitId = 6,
                        title = "1D & 2D Arrays",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u6_t1",
                            title = "1D & 2D Arrays",
                            explanation = "An Array is a collection of elements of the same data type stored in contiguous memory locations.\n- 1D Array: Single row of values indexed from 0 to N-1.\n- 2D Array: Grid of rows and columns (e.g. matrix[row][col]).",
                            hinglishExplanation = "Array ek hi type ke bohot sare values ka collection hota hai jo RAM memory me line se (contiguous) store hote hain. Array hamesha [0] index se start hota hai!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int marks[3] = {85, 90, 78};\n    printf(\"First Mark: %d\\n\", marks[0]);\n\n    int matrix[2][2] = {{1, 2}, {3, 4}};\n    printf(\"Matrix [1][1]: %d\\n\", matrix[1][1]);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Array index starts at [0].",
                                "Elements are stored sequentially in computer memory."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Array Memory Locker Cells"
                        )
                    ),
                    Topic(
                        id = "u6_t2",
                        unitId = 6,
                        title = "String Handling Functions (string.h)",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u6_t2",
                            title = "String Handling Functions (string.h)",
                            explanation = "In C, a String is an array of characters ending with a null character '\\0'.\nStandard Functions in <string.h>:\n- strlen(str): Returns string length.\n- strcpy(dest, src): Copies src into dest.\n- strcat(dest, src): Appends src to dest.\n- strcmp(s1, s2): Compares two strings (0 if equal).",
                            hinglishExplanation = "C language me String ek character array hoti hai jo '\\0' (null character) par end hoti hai.\n<string.h> ke main functions:\n- strlen(): Length nikalta hai\n- strcpy(): Copy karta hai\n- strcat(): Jodta (combine) hai\n- strcmp(): Compare karta hai",
                            codeSnippet = "#include <stdio.h>\n#include <string.h>\n\nint main() {\n    char s1[20] = \"Hello \";\n    char s2[] = \"Diploma\";\n    strcat(s1, s2);\n    printf(\"Combined: %s, Length: %lu\\n\", s1, strlen(s1));\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Strings end with '\\0' null character.",
                                "#include <string.h> provides string operations."
                            )
                        )
                    )
                )
            ),

            // UNIT 7
            DiplomaUnit(
                id = 7,
                unitNumber = "Unit 7",
                title = "Pointers and Memory Allocation",
                description = "Pointer basics & arithmetic, array/string pointers, dynamic memory (malloc, calloc, free).",
                topics = listOf(
                    Topic(
                        id = "u7_t1",
                        unitId = 7,
                        title = "Pointer Basics & Pointer Arithmetic",
                        estimatedMinutes = 6,
                        microLesson = MicroLesson(
                            topicId = "u7_t1",
                            title = "Pointer Basics & Pointer Arithmetic",
                            explanation = "A Pointer is a variable that stores the memory address of another variable.\n- '&' (Address-of): Gets the memory address.\n- '*' (Dereference): Gets the value stored at the address.\nPointer Arithmetic: Incrementing a pointer (ptr++) moves it to the NEXT data memory address!",
                            hinglishExplanation = "Pointer ek special variable hai jo dusre variable ka RAM Address store karta hai.\n- '&' address batata hai.\n- '*' address par rakhi value batata hai.",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    int num = 42;\n    int *ptr = &num;\n    printf(\"Value: %d, Address: %p\\n\", *ptr, (void*)ptr);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "& returns address; * returns value at address.",
                                "Pointers store RAM memory addresses."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Pointer Address vs Value visualizer"
                        )
                    ),
                    Topic(
                        id = "u7_t2",
                        unitId = 7,
                        title = "Dynamic Memory Allocation (malloc, calloc, free)",
                        estimatedMinutes = 6,
                        microLesson = MicroLesson(
                            topicId = "u7_t2",
                            title = "Dynamic Memory Allocation (malloc, calloc, free)",
                            explanation = "Dynamic memory allocation allocates RAM during program runtime using functions in <stdlib.h>:\n- malloc(size): Allocates uninitialized memory bytes.\n- calloc(n, size): Allocates memory initialized to zero.\n- free(ptr): Deallocates memory to prevent MEMORY LEAKS!",
                            hinglishExplanation = "Program chalte vakt RAM allocate karne ke liye malloc/calloc use hote hain.\nIMP: Kaam hone ke baad free(ptr) lagana zaroori hai taaki RAM memory leak na ho!",
                            codeSnippet = "#include <stdio.h>\n#include <stdlib.h>\n\nint main() {\n    int *arr = (int*) malloc(3 * sizeof(int));\n    if (arr != NULL) {\n        arr[0] = 10; arr[1] = 20; arr[2] = 30;\n        printf(\"Element 1: %d\\n\", arr[1]);\n        free(arr); // Always free dynamic memory!\n    }\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "malloc and calloc allocate RAM dynamically at runtime.",
                                "Always call free() to avoid memory leaks."
                            )
                        )
                    )
                )
            ),

            // UNIT 8
            DiplomaUnit(
                id = 8,
                unitNumber = "Unit 8",
                title = "Structures and Unions",
                description = "Defining & using structures, nested structures, unions & memory applications.",
                topics = listOf(
                    Topic(
                        id = "u8_t1",
                        unitId = 8,
                        title = "Defining & Using Structures (struct)",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u8_t1",
                            title = "Defining & Using Structures (struct)",
                            explanation = "A Structure (struct) is a user-defined data type that groups variables of different types (int, float, char) into a single record.\nAccess members using the dot (.) operator.",
                            hinglishExplanation = "struct alag-alag data types (int, float, char) ko ek saath group karne ke liye use hota hai (jaise ek Student Record).",
                            codeSnippet = "#include <stdio.h>\n\nstruct Student {\n    int rollNo;\n    float marks;\n};\n\nint main() {\n    struct Student s1 = {101, 88.5};\n    printf(\"Roll: %d, Marks: %.1f\\n\", s1.rollNo, s1.marks);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "struct groups different data types under one name.",
                                "Access struct members using dot (.) operator."
                            )
                        )
                    ),
                    Topic(
                        id = "u8_t2",
                        unitId = 8,
                        title = "Unions vs Structures",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u8_t2",
                            title = "Unions vs Structures",
                            explanation = "- Structure: Allocates separate memory for EVERY member. Total size = sum of all member sizes.\n- Union: Shares ONE SINGLE memory location across all members. Total size = size of the LARGEST member! Only one member can be used at a time.",
                            hinglishExplanation = "- Structure sabhi members ke liye alag memory deta hai.\n- Union sabhi members ke bich ek hi memory share karta hai jisse RAM bachti hai!",
                            codeSnippet = "#include <stdio.h>\n\nunion Data {\n    int i;\n    float f;\n};\n\nint main() {\n    union Data d;\n    d.i = 10;\n    printf(\"Integer: %d\\n\", d.i);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "struct allocates separate memory for all members.",
                                "union shares one memory space across all members to save RAM."
                            ),
                            animationFlagged = true,
                            animationNote = "Phase 2 Animation: Struct vs Union Memory Layout comparison"
                        )
                    )
                )
            ),

            // UNIT 9
            DiplomaUnit(
                id = 9,
                unitNumber = "Unit 9",
                title = "File Handling",
                description = "File operations (fopen, fclose, fprintf, fscanf, fread, fwrite), file modes & error handling.",
                topics = listOf(
                    Topic(
                        id = "u9_t1",
                        unitId = 9,
                        title = "File Operations & File Modes",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u9_t1",
                            title = "File Operations & File Modes",
                            explanation = "File handling stores data permanently on hard disk files using FILE pointers.\nFile Modes:\n- 'r' : Read mode (file must exist).\n- 'w' : Write mode (creates new or overwrites existing file).\n- 'a' : Append mode (adds data at the end of file).",
                            hinglishExplanation = "File handling se data computer ki hard disk me permanently save ho jata hai.\n- 'r': File padhne ke liye\n- 'w': Nayi file likhne ke liye\n- 'a': Purani file ke aage content add karne ke liye",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    FILE *fp = fopen(\"notes.txt\", \"w\");\n    if (fp != NULL) {\n        fprintf(fp, \"MSBTE Diploma C Programming\\n\");\n        fclose(fp);\n        printf(\"File created and written successfully!\\n\");\n    }\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "fopen() opens file; fclose() closes file.",
                                "'w' overwrites, 'a' appends to existing file content."
                            )
                        )
                    ),
                    Topic(
                        id = "u9_t2",
                        unitId = 9,
                        title = "Reading, Writing & Error Handling",
                        estimatedMinutes = 5,
                        microLesson = MicroLesson(
                            topicId = "u9_t2",
                            title = "Reading, Writing & Error Handling",
                            explanation = "- fprintf() / fscanf(): Write & read formatted text.\n- fgetc() / fputc(): Read & write single characters.\n- Error Handling: Always check if FILE *fp is NULL before performing operations!",
                            hinglishExplanation = "File read/write karne se pehle hamesha check kare ki `fp == NULL` to nahi hai!",
                            codeSnippet = "#include <stdio.h>\n\nint main() {\n    FILE *fp = fopen(\"notes.txt\", \"r\");\n    char buffer[50];\n    if (fp == NULL) {\n        printf(\"Error opening file!\\n\");\n        return 1;\n    }\n    fgets(buffer, 50, fp);\n    printf(\"File Content: %s\\n\", buffer);\n    fclose(fp);\n    return 0;\n}",
                            keyTakeaways = listOf(
                                "Always verify if FILE pointer is NULL before reading/writing.",
                                "fgets() reads a full line of text safely."
                            )
                        )
                    )
                )
            )
        )
    }

    fun getUnitById(unitId: Int): DiplomaUnit? {
        return getDiplomaUnits().find { it.id == unitId }
    }

    fun getTopicById(unitId: Int, topicId: String): Topic? {
        return getDiplomaUnits()
            .find { it.id == unitId }
            ?.topics
            ?.find { it.id == topicId }
    }
}
