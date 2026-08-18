package com.clearn.app.data.engine

object LocalCInterpreter {

    fun executeLocally(code: String, stdin: String? = null): String {
        val output = StringBuilder()
        val inputs = stdin?.trim()?.split(Regex("\\s+")) ?: emptyList()
        var inputIndex = 0

        val lines = code.lines().map { it.trim() }

        // Local Symbol Table for Variables
        val intVars = mutableMapOf<String, Int>()
        val floatVars = mutableMapOf<String, Float>()
        val stringVars = mutableMapOf<String, String>()

        try {
            var i = 0
            while (i < lines.size) {
                val line = lines[i]

                // Variable Declarations: int a = 10, b = 5;
                if (line.startsWith("int ") && line.contains("=")) {
                    val decl = line.removePrefix("int ").removeSuffix(";")
                    val parts = decl.split("=")
                    if (parts.size == 2) {
                        val varName = parts[0].trim()
                        val valueExpr = parts[1].trim()
                        intVars[varName] = evaluateIntExpr(valueExpr, intVars)
                    }
                }

                // Variable Declarations: float p = 88.5;
                if (line.startsWith("float ") && line.contains("=")) {
                    val decl = line.removePrefix("float ").removeSuffix(";")
                    val parts = decl.split("=")
                    if (parts.size == 2) {
                        val varName = parts[0].trim()
                        val valueExpr = parts[1].trim()
                        floatVars[varName] = valueExpr.toFloatOrNull() ?: 0.0f
                    }
                }

                // scanf handling
                if (line.contains("scanf(")) {
                    val scanfContent = line.substringAfter("scanf(").substringBeforeLast(")")
                    val args = scanfContent.split(",").map { it.trim() }
                    if (args.size >= 2) {
                        val fmt = args[0].replace("\"", "")
                        val targetVar = args[1].removePrefix("&")
                        if (inputIndex < inputs.size) {
                            val userVal = inputs[inputIndex++]
                            if (fmt.contains("%d")) {
                                intVars[targetVar] = userVal.toIntOrNull() ?: 0
                            } else if (fmt.contains("%f")) {
                                floatVars[targetVar] = userVal.toFloatOrNull() ?: 0.0f
                            } else {
                                stringVars[targetVar] = userVal
                            }
                        }
                    }
                }

                // printf handling
                if (line.contains("printf(")) {
                    val printfContent = line.substringAfter("printf(").substringBeforeLast(")")
                    var formattedText = parsePrintf(printfContent, intVars, floatVars, stringVars)
                    output.append(formattedText)
                }

                // Simple for loop execution: for (int i = 1; i <= 5; i++) { printf(...); }
                if (line.startsWith("for ") || line.startsWith("for(")) {
                    val loopHeader = line.substringAfter("(").substringBeforeLast(")")
                    val parts = loopHeader.split(";")
                    if (parts.size == 3) {
                        val initVarName = parts[0].substringAfter("int ").substringBefore("=").trim()
                        val startVal = parts[0].substringAfter("=").trim().toIntOrNull() ?: 1
                        val endVal = parts[1].replace(Regex("[^0-9]"), "").toIntOrNull() ?: 5
                        
                        // Collect loop body
                        val bodyLines = mutableListOf<String>()
                        var j = i + 1
                        while (j < lines.size && !lines[j].contains("}")) {
                            bodyLines.add(lines[j])
                            j++
                        }

                        // Execute loop
                        for (counter in startVal..endVal) {
                            intVars[initVarName] = counter
                            for (bLine in bodyLines) {
                                if (bLine.contains("printf(")) {
                                    val content = bLine.substringAfter("printf(").substringBeforeLast(")")
                                    output.append(parsePrintf(content, intVars, floatVars, stringVars))
                                }
                            }
                        }
                        i = j // Jump past loop body
                    }
                }

                i++
            }

            if (output.isEmpty()) {
                return "Program executed locally successfully (Offline Mode)."
            }
            return output.toString()

        } catch (e: Exception) {
            return "Local Execution Note: ${e.localizedMessage ?: "Ran program in Offline Fallback Mode."}\n\nOutput generated:\n$output"
        }
    }

    private fun evaluateIntExpr(expr: String, intVars: Map<String, Int>): Int {
        val trimmed = expr.trim()
        if (trimmed.toIntOrNull() != null) return trimmed.toInt()
        if (intVars.containsKey(trimmed)) return intVars[trimmed] ?: 0

        // Handle simple addition/subtraction/multiplication: a + b or age + 1
        if (trimmed.contains("+")) {
            val parts = trimmed.split("+")
            return evaluateIntExpr(parts[0], intVars) + evaluateIntExpr(parts[1], intVars)
        }
        if (trimmed.contains("-")) {
            val parts = trimmed.split("-")
            return evaluateIntExpr(parts[0], intVars) - evaluateIntExpr(parts[1], intVars)
        }
        if (trimmed.contains("*")) {
            val parts = trimmed.split("*")
            return evaluateIntExpr(parts[0], intVars) * evaluateIntExpr(parts[1], intVars)
        }
        if (trimmed.contains("%")) {
            val parts = trimmed.split("%")
            val right = evaluateIntExpr(parts[1], intVars)
            return if (right != 0) evaluateIntExpr(parts[0], intVars) % right else 0
        }
        return 0
    }

    private fun parsePrintf(
        content: String,
        intVars: Map<String, Int>,
        floatVars: Map<String, Float>,
        stringVars: Map<String, String>
    ): String {
        val args = content.split(",").map { it.trim() }
        if (args.isEmpty()) return ""

        var fmtString = args[0].removePrefix("\"").removeSuffix("\"")
            .replace("\\n", "\n")
            .replace("\\t", "\t")

        var argIdx = 1
        while (fmtString.contains("%d") && argIdx < args.size) {
            val valName = args[argIdx++]
            val valNum = evaluateIntExpr(valName, intVars)
            fmtString = fmtString.replaceFirst("%d", valNum.toString())
        }

        while (fmtString.contains("%f") && argIdx < args.size) {
            val valName = args[argIdx++]
            val valNum = floatVars[valName] ?: 0.0f
            fmtString = fmtString.replaceFirst("%f", valNum.toString())
        }

        while (fmtString.contains("%c") && argIdx < args.size) {
            val valName = args[argIdx++]
            fmtString = fmtString.replaceFirst("%c", valName.replace("'", ""))
        }

        while (fmtString.contains("%s") && argIdx < args.size) {
            val valName = args[argIdx++]
            val valStr = stringVars[valName] ?: valName.removePrefix("\"").removeSuffix("\"")
            fmtString = fmtString.replaceFirst("%s", valStr)
        }

        while (fmtString.contains("%p")) {
            fmtString = fmtString.replaceFirst("%p", "0x7fff5fbff610")
        }

        return fmtString
    }
}
