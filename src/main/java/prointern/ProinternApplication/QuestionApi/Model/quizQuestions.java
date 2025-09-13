package prointern.ProinternApplication.QuestionApi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="quizQuestions")
public class quizQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String topic;
	private String questiontext;
	
	@Column(length = 2000)
    private String text;

    // store 4 options as separate fields for simplicity
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    // 0 for A, 1 for B, 2 for C, 3 for D
    private int correctOptionIndex;
    
    public quizQuestions(String text, String optionA, String optionB, String optionC, String optionD, int correctOptionIndex) {
      this.text = text;
      this.optionA = optionA;
      this.optionB = optionB;
      this.optionC = optionC;
      this.optionD = optionD;
      this.correctOptionIndex = correctOptionIndex;
  }
}
