package prointern.ProinternApplication.PlacementAssistance.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PlacementQuestions")
public class PlacementQuestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String text;

    // store 4 options as separate fields for simplicity
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    // 0 for A, 1 for B, 2 for C, 3 for D
    private int correctOptionIndex;

    public PlacementQuestion() {}

    public PlacementQuestion(String text, String optionA, String optionB, String optionC, String optionD, int correctOptionIndex) {
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOptionIndex = correctOptionIndex;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public int getCorrectOptionIndex() { return correctOptionIndex; }
    public void setCorrectOptionIndex(int correctOptionIndex) { this.correctOptionIndex = correctOptionIndex; }
}
