package com.clearn.app.data.remote.translator

object CErrorTranslator {

    fun translateCompilerError(rawError: String): String {
        val translatedRules = mutableListOf<String>()

        if (rawError.contains("expected ';'", ignoreCase = true)) {
            translatedRules.add("💡 Check your Semicolons: In C, every statement must end with a semicolon (;). Looks like a ';' is missing on or right before line.")
        }

        if (rawError.contains("undeclared", ignoreCase = true)) {
            translatedRules.add("💡 Variable Not Declared: You are using a variable before creating it. Make sure to declare its data type first (e.g. 'int num;').")
        }

        if (rawError.contains("implicit declaration of function 'printf'", ignoreCase = true) ||
            rawError.contains("implicit declaration of function 'scanf'", ignoreCase = true)) {
            translatedRules.add("💡 Missing Header File: Include '#include <stdio.h>' at the top of your code so C knows what printf() and scanf() mean.")
        }

        if (rawError.contains("expected '=', ',', ';', 'asm' or '__attribute__' before '{' token", ignoreCase = true)) {
            translatedRules.add("💡 Function Syntax Error: Check your main() function declaration. Make sure it is written as 'int main() {'")
        }

        if (rawError.contains("format '%d' expects argument of type", ignoreCase = true)) {
            translatedRules.add("💡 scanf() Pointer Warning: Did you forget the '&' symbol before your variable name inside scanf()? (e.g. use &age)")
        }

        if (rawError.contains("control reaches end of non-void function", ignoreCase = true)) {
            translatedRules.add("💡 Missing Return Statement: Write 'return 0;' at the end of your main() function.")
        }

        if (rawError.contains("expected ')' before", ignoreCase = true)) {
            translatedRules.add("💡 Missing Closing Parenthesis: Make sure every opening parenthesis '(' has a matching closing parenthesis ')'.")
        }

        if (rawError.contains("expected '}' at end of input", ignoreCase = true)) {
            translatedRules.add("💡 Missing Closing Curly Brace: You forgot to close a block with a '}' brace at the end of your code.")
        }

        return if (translatedRules.isNotEmpty()) {
            "--- 🎓 CLearn Helpful Error Explanation ---\n" +
                    translatedRules.joinToString("\n\n") +
                    "\n\n--- Raw GCC Compiler Log ---\n$rawError"
        } else {
            "--- Raw GCC Compiler Output ---\n$rawError"
        }
    }
}
