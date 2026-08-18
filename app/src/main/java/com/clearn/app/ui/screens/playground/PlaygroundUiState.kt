package com.clearn.app.ui.screens.playground

data class PlaygroundUiState(
    val code: String = DEFAULT_HELLO_WORLD,
    val input: String = "",
    val isRunning: Boolean = false,
    val outputText: String? = null,
    val isError: Boolean = false,
    val executionTime: String? = null,
    val memoryUsage: Int? = null
)

val DEFAULT_HELLO_WORLD = """
#include <stdio.h>

int main() {
    printf("Hello, MSBTE Diploma Students!\n");
    return 0;
}
""".trimIndent()

val TEMPLATE_SCANF = """
#include <stdio.h>

int main() {
    int age;
    printf("Enter your age: ");
    scanf("%d", &age);
    printf("Next year, you will be %d years old!\n", age + 1);
    return 0;
}
""".trimIndent()

val TEMPLATE_IF_ELSE = """
#include <stdio.h>

int main() {
    int number = 15;
    if (number % 2 == 0) {
        printf("%d is Even\n", number);
    } else {
        printf("%d is Odd\n", number);
    }
    return 0;
}
""".trimIndent()

val TEMPLATE_FOR_LOOP = """
#include <stdio.h>

int main() {
    printf("Counting from 1 to 5:\n");
    for (int i = 1; i <= 5; i++) {
        printf("Count: %d\n", i);
    }
    return 0;
}
""".trimIndent()

val TEMPLATE_POINTER = """
#include <stdio.h>

int main() {
    int val = 100;
    int *ptr = &val;
    printf("Value: %d\n", *ptr);
    printf("Memory Address: %p\n", (void*)ptr);
    return 0;
}
""".trimIndent()
