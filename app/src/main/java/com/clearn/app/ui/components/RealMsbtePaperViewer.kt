package com.clearn.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.clearn.app.ui.theme.CodeBackground
import com.clearn.app.ui.theme.CodeMonospaceStyle

data class MsbteQuestion(
    val qNo: String,
    val title: String,
    val questions: List<String>,
    val modelAnswerText: String
)

@Composable
fun RealMsbtePaperViewer(
    examSession: String,
    isModelAnswer: Boolean
) {
    val paperData = getPaperDataForSession(examSession)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // MSBTE Official Exam Header Box
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = if (isModelAnswer) Color(0xFF065F46) else Color(0xFF1E3A8A)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MAHARASHTRA STATE BOARD OF TECHNICAL EDUCATION",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isModelAnswer) "OFFICIAL MODEL ANSWER KEY - $examSession" else "EXAMINATION QUESTION PAPER - $examSession",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFDE047),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Subject: Programming in C (Subject Code: 312303 • K-Scheme • Sem II)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Time: 3 Hours", style = MaterialTheme.typography.labelSmall, color = Color.White)
                    Text("Total Marks: 70 Marks", style = MaterialTheme.typography.labelSmall, color = Color.White)
                }
            }
        }

        // Instructions
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Instructions for Candidates:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                Text("1. All questions are compulsory unless specified.", style = MaterialTheme.typography.bodySmall)
                Text("2. Figures to the right indicate full marks.", style = MaterialTheme.typography.bodySmall)
            }
        }

        // Render Session-Specific Questions
        paperData.forEach { block ->
            QuestionBlock(
                qNo = block.qNo,
                title = block.title,
                questions = block.questions,
                isModelAnswer = isModelAnswer,
                modelAnswerContent = block.modelAnswerText
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun QuestionBlock(
    qNo: String,
    title: String,
    questions: List<String>,
    isModelAnswer: Boolean,
    modelAnswerContent: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$qNo $title",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(8.dp))

            questions.forEach { q ->
                Text(
                    text = q,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }

            if (isModelAnswer) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    color = CodeBackground
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "📝 OFFICIAL MODEL ANSWER SOLUTION & MARKING SCHEME",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF10B981)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = modelAnswerContent,
                            style = CodeMonospaceStyle,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

fun getPaperDataForSession(session: String): List<MsbteQuestion> {
    return when (session) {
        "Summer 2024" -> listOf(
            MsbteQuestion(
                qNo = "Q.1",
                title = "Attempt any FIVE of the following: (10 Marks)",
                questions = listOf(
                    "a) Enlist four features of C language. (2M)",
                    "b) Define variable and constant with example. (2M)",
                    "c) State the use of printf() and scanf() with syntax. (2M)",
                    "d) Write the syntax and example of ternary conditional operator. (2M)",
                    "e) Give two differences between while loop and do-while loop. (2M)",
                    "f) Define array. How is 1D array initialized? (2M)",
                    "g) State the purpose of #include preprocessor directive. (2M)"
                ),
                modelAnswerText = """
                    Ans 1(a) Features: Procedural language, Fast execution, Rich library functions, Portable. (2 Marks)
                    Ans 1(b) Variable: Named RAM memory container whose value changes during execution (e.g., int age = 18;).
                    Constant: Fixed value (e.g., const float PI = 3.14;). (2 Marks)
                    Ans 1(c) printf(): Displays output on screen. Syntax: printf("Format", vars);
                    scanf(): Takes user keyboard input. Syntax: scanf("%d", &var); (2 Marks)
                """.trimIndent()
            ),
            MsbteQuestion(
                qNo = "Q.2",
                title = "Attempt any THREE of the following: (12 Marks)",
                questions = listOf(
                    "a) Write a C program to find the largest of three numbers using nested if-else. (4M)",
                    "b) Explain switch-case statement with syntax and suitable example. (4M)",
                    "c) Write a C program to print multiplication table of a given number using for loop. (4M)",
                    "d) Differentiate between call by value and call by reference. (4M)"
                ),
                modelAnswerText = """
                    Ans 2(a) C Program for Largest of 3 Numbers:
                    #include <stdio.h>
                    int main() {
                        int a=10, b=25, c=15;
                        if(a>=b && a>=c) printf("A is largest");
                        else if(b>=a && b>=c) printf("B is largest");
                        else printf("C is largest");
                        return 0;
                    }
                    Marking Scheme: Algorithm 2M, C Code 2M. Total: 4 Marks.
                """.trimIndent()
            )
        )

        "Winter 2024" -> listOf(
            MsbteQuestion(
                qNo = "Q.1",
                title = "Attempt any FIVE of the following: (10 Marks)",
                questions = listOf(
                    "a) List basic data types used in C with their size in bytes. (2M)",
                    "b) Define increment and decrement operators (++ and --). (2M)",
                    "c) What is an entry-controlled loop? Give one example. (2M)",
                    "d) State the purpose of return statement in functions. (2M)",
                    "e) Define string in C language. (2M)",
                    "f) Give syntax of declaring a structure. (2M)",
                    "g) What is the role of NULL pointer in C? (2M)"
                ),
                modelAnswerText = """
                    Ans 1(a) Data Types: int (2/4 bytes), float (4 bytes), char (1 byte), double (8 bytes). (2 Marks)
                    Ans 1(b) ++ (Increment): Adds 1 to operand (e.g. i++). -- (Decrement): Subtracts 1 (e.g. i--). (2 Marks)
                    Ans 1(c) Entry-controlled loop checks condition BEFORE executing loop body (e.g., for loop, while loop). (2 Marks)
                """.trimIndent()
            ),
            MsbteQuestion(
                qNo = "Q.2",
                title = "Attempt any THREE of the following: (12 Marks)",
                questions = listOf(
                    "a) Write a C program to check whether a given year is a Leap Year. (4M)",
                    "b) Explain break and continue statements with code examples. (4M)",
                    "c) Write a C program to reverse a given integer number using while loop. (4M)",
                    "d) Explain function declaration, function call, and function definition. (4M)"
                ),
                modelAnswerText = """
                    Ans 2(a) Leap Year C Program:
                    #include <stdio.h>
                    int main() {
                        int year = 2024;
                        if((year%4==0 && year%100!=0) || (year%400==0))
                            printf("Leap Year");
                        else printf("Not Leap Year");
                        return 0;
                    }
                    Marking Scheme: Condition logic 2M, Syntax 2M. Total: 4 Marks.
                """.trimIndent()
            )
        )

        "Summer 2023" -> listOf(
            MsbteQuestion(
                qNo = "Q.1",
                title = "Attempt any FIVE of the following: (10 Marks)",
                questions = listOf(
                    "a) Explain modulo operator (%) with an example. (2M)",
                    "b) What is nested loop? Give one example syntax. (2M)",
                    "c) Define recursion in C. (2M)",
                    "d) State the use of strlen() and strcmp() functions. (2M)",
                    "e) Define pointer variable. (2M)",
                    "f) Differentiate between structure and union memory size. (2M)",
                    "g) What is the use of fopen() and fclose() functions? (2M)"
                ),
                modelAnswerText = """
                    Ans 1(a) Modulo (%) returns remainder of division (e.g. 10 % 3 = 1). (2 Marks)
                    Ans 1(c) Recursion: A function calling ITSELF until a base condition is satisfied. (2 Marks)
                    Ans 1(e) Pointer: A variable that stores the RAM memory address of another variable. (2 Marks)
                """.trimIndent()
            ),
            MsbteQuestion(
                qNo = "Q.2",
                title = "Attempt any THREE of the following: (12 Marks)",
                questions = listOf(
                    "a) Write a C program to search an element in a 1D array using linear search. (4M)",
                    "b) Write a C program to calculate sum of digits of a number. (4M)",
                    "c) Explain call by value and call by reference with parameter swapping code. (4M)",
                    "d) Write a C program to copy contents of one file to another. (4M)"
                ),
                modelAnswerText = """
                    Ans 2(a) Linear Search Code:
                    #include <stdio.h>
                    int main() {
                        int arr[5] = {10, 20, 30, 40, 50}, key=30, found=0;
                        for(int i=0; i<5; i++) {
                            if(arr[i] == key) { found=1; break; }
                        }
                        if(found) printf("Found!");
                        return 0;
                    }
                    Marking Scheme: Loop 2M, Match check 2M. Total: 4 Marks.
                """.trimIndent()
            )
        )

        else -> listOf( // Winter 2023
            MsbteQuestion(
                qNo = "Q.1",
                title = "Attempt any FIVE of the following: (10 Marks)",
                questions = listOf(
                    "a) What is keywords in C? Give two examples. (2M)",
                    "b) Differentiate between = (assignment) and == (relational) operators. (2M)",
                    "c) Write syntax of do-while loop. (2M)",
                    "d) What is global variable vs local variable? (2M)",
                    "e) State purpose of malloc() and free() functions. (2M)",
                    "f) Define array of structures with example. (2M)",
                    "g) List any two file opening modes in C. (2M)"
                ),
                modelAnswerText = """
                    Ans 1(a) Keywords: Reserved words in C with fixed meaning (e.g. int, return, if, else). (2 Marks)
                    Ans 1(e) malloc(): Allocates RAM dynamically. free(): Releases RAM memory to prevent memory leaks. (2 Marks)
                    Ans 1(g) Modes: 'r' (Read mode), 'w' (Write mode), 'a' (Append mode). (2 Marks)
                """.trimIndent()
            ),
            MsbteQuestion(
                qNo = "Q.2",
                title = "Attempt any THREE of the following: (12 Marks)",
                questions = listOf(
                    "a) Write a C program to swap two numbers using pointers. (4M)",
                    "b) Create a structure 'Student' (rollNo, name, marks) and read details for 3 students. (4M)",
                    "c) Write a C program to check palindrome string without using strrev(). (4M)",
                    "d) Explain file handling operations fopen(), fprintf(), fscanf(), and fclose(). (4M)"
                ),
                modelAnswerText = """
                    Ans 2(a) Pointer Swap Code:
                    void swap(int *x, int *y) {
                        int temp = *x;
                        *x = *y;
                        *y = temp;
                    }
                    Marking Scheme: Pointer function declaration 2M, Logic 2M. Total: 4 Marks.
                """.trimIndent()
            )
        )
    }
}
