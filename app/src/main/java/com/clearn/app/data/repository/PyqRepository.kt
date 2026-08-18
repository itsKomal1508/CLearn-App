package com.clearn.app.data.repository

import com.clearn.app.data.model.PyqPaper

class PyqRepository {

    fun getPyqPapers(): List<PyqPaper> {
        return listOf(
            PyqPaper(
                id = "pyq_s24",
                title = "Programming in C (K-Scheme)",
                examSession = "Summer 2024",
                year = "2024",
                questionPaperPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                modelAnswerPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                fileSize = "1.4 MB"
            ),
            PyqPaper(
                id = "pyq_w24",
                title = "Programming in C (K-Scheme)",
                examSession = "Winter 2024",
                year = "2024",
                questionPaperPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                modelAnswerPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                fileSize = "1.3 MB"
            ),
            PyqPaper(
                id = "pyq_s23",
                title = "Programming in C (I/K-Scheme)",
                examSession = "Summer 2023",
                year = "2023",
                questionPaperPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                modelAnswerPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                fileSize = "1.1 MB"
            ),
            PyqPaper(
                id = "pyq_w23",
                title = "Programming in C (I/K-Scheme)",
                examSession = "Winter 2023",
                year = "2023",
                questionPaperPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                modelAnswerPdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                fileSize = "1.2 MB"
            )
        )
    }
}
