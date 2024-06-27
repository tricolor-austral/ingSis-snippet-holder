package ingsis.tricolor.operations.entity

import ingsis.tricolor.operations.dto.testCase.TestCaseCreateDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.CollectionTable
import jakarta.persistence.JoinColumn
import org.jetbrains.annotations.NotNull

@Entity
class TestCase {
    @Id()
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column
    var snippetId: Long = 0

    @Column
    var name: String = ""

    @ElementCollection
    @CollectionTable(name = "test_case_inputs", joinColumns = [JoinColumn(name = "test_case_id")])
    @Column(name = "input")
    var input: List<String> = mutableListOf()

    @ElementCollection
    @CollectionTable(name = "test_case_outputs", joinColumns = [JoinColumn(name = "test_case_id")])
    @Column(name = "input")
    var expectedOutput: List<String> = mutableListOf()

    @Column
    var envVars: String = ""

    companion object {
        fun from(testCaseDto: TestCaseCreateDto): TestCase {
            val testCase = TestCase()
            testCase.input = testCaseDto.input
            testCase.expectedOutput = testCaseDto.output
            testCase.name = testCaseDto.name
            testCase.snippetId = testCaseDto.id
            testCase.envVars = testCaseDto.envVars
            return testCase
        }
    }
}
