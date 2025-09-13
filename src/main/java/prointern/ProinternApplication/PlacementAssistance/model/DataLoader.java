package prointern.ProinternApplication.PlacementAssistance.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import prointern.ProinternApplication.Model.User;
import prointern.ProinternApplication.PlacementAssistance.repo.PlacmentQuestionRepository;
import prointern.ProinternApplication.PlacementAssistance.entities.PlacementQuestion;
import prointern.ProinternApplication.Repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlacmentQuestionRepository placmentQuestionRepository;
    private final UserRepository userRepository;

    public DataLoader(PlacmentQuestionRepository placmentQuestionRepository, UserRepository userRepository) {
        this.placmentQuestionRepository = placmentQuestionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create demo user: username: test, password: test123
        if (!userRepository.existsByUsername("test")) {
            User u = new User();
            u.setUsername("test");
            u.setPassword("Testing@1234");
            u.setEmail("test@gmail.com");
            u.setPasswordHash(BCrypt.hashpw("Testing@1234", BCrypt.gensalt()));
            userRepository.save(u);
            System.out.println("Demo user created: test / test123");
        }

        if (placmentQuestionRepository.count() == 0) {
            List<PlacementQuestion> questions = Arrays.asList(
                new PlacementQuestion("If 3 workers can build 3 machines in 3 days, how many days will it take 1 worker to build 1 machine?", "3", "1", "9", "None of these", 1),
                new PlacementQuestion("Find the next number in sequence: 2, 6, 12, 20, ?", "30", "26", "24", "28", 2),
                new PlacementQuestion("If A = 1, B = 2, then what is the value of 'CAB' as a number (C=3,A=1,B=2)?", "312", "132", "231", "213", 1),
                new PlacementQuestion("Which shape has 4 equal sides and 4 right angles?", "Rectangle", "Square", "Rhombus", "Parallelogram", 1),
                new PlacementQuestion("If all Bloops are Razzies and all Razzies are Lazzies, then:", "All Bloops are Lazzies", "All Lazzies are Bloops", "Some Bloops are not Lazzies", "None of these", 0),
                new PlacementQuestion("A cube has how many edges?", "8", "12", "6", "24", 1),
                new PlacementQuestion("Complete the analogy: Bird is to Fly as Fish is to ?", "Swim", "Walk", "Jump", "Run", 0),
                new PlacementQuestion("Which of these words is the odd one out: Apple, Banana, Carrot, Mango?", "Apple", "Banana", "Carrot", "Mango", 2),
                new PlacementQuestion("If today is Monday, what day will it be in 100 days?", "Thursday", "Friday", "Saturday", "Wednesday", 0),
                new PlacementQuestion("If 7 + 3 = 10, 9 + 6 = 21, then 5 + 4 = ?", "9", "20", "15", "10", 2),
                new PlacementQuestion("What comes next in: 1, 1, 2, 3, 5, 8, ?", "13", "11", "10", "12", 0),
                new PlacementQuestion("Find the missing number: 14, 28, 20, 40, 32, ?", "64", "48", "34", "40", 0),
                new PlacementQuestion("If some cats are dogs and some dogs are mice, which is true?", "All cats are mice", "Some cats may be mice", "No dogs are cats", "All dogs are cats", 1),
                new PlacementQuestion("A train 100m long passes a platform in 20s. Speed of train is?", "18 kmph", "30 kmph", "36 kmph", "45 kmph", 2),
                new PlacementQuestion("If x = 3, evaluate 2x^2 + x - 5", "16", "10", "15", "20", 0),
                new PlacementQuestion("Which number is prime?", "21", "29", "27", "33", 1),
                new PlacementQuestion("If you rearrange the letters 'CIFAIPC' you get the name of a:", "Country", "City", "Ocean", "Animal", 1),
                new PlacementQuestion("Select the pair that has a relationship similar to: Night : Moon", "Day : Sun", "Evening : Clock", "Noon : Stars", "Dawn : Birds", 0),
                new PlacementQuestion("What is 15% of 200?", "20", "30", "25", "15", 1),
                new PlacementQuestion("If 2 pencils cost 8 rupees, how much for 5 pencils?", "20", "10", "18", "15", 0),
                new PlacementQuestion("Which one doesn't belong: Red, Blue, Yellow, Circle", "Red", "Blue", "Yellow", "Circle", 3),
                new PlacementQuestion("If you have only one match and you enter a dark room with a candle, a wood stove, and a gas lamp, what would you light first?", "Candle", "Match", "Wood stove", "Gas lamp", 1),
                new PlacementQuestion("Which weighs more? 1 kg cotton or 1 kg iron?", "Cotton", "Iron", "They weigh the same", "Cannot say", 2),
                new PlacementQuestion("If every pair has 2, how many pairs in 10 items?", "5", "8", "2", "10", 0),
                new PlacementQuestion("Which of the following completes the sequence: J, F, M, A, M, J, ?", "J", "A", "S", "O", 0),
                new PlacementQuestion("If 4x = 20, x = ?", "3", "4", "5", "6", 2),
                new PlacementQuestion("Which number is largest: 1/2, 2/3, 3/4, 4/5?", "1/2", "2/3", "3/4", "4/5", 3),
                new PlacementQuestion("Find missing: 5*3=20, 6*4=30, 7*5=?", "35", "40", "45", "50", 1),
                new PlacementQuestion("A shopkeeper gives 10% discount on Rs.500, how much to pay?", "450", "490", "400", "475", 0),
                new PlacementQuestion("What is 9 * 9?", "72", "81", "90", "99", 1),
                new PlacementQuestion("If A is older than B and B is older than C, who is the youngest?", "A", "B", "C", "Cannot say", 2),
                new PlacementQuestion("Which of these is a vowel?", "B", "C", "A", "D", 2),
                new PlacementQuestion("How many months have 31 days?", "6", "7", "5", "4", 1),
                new PlacementQuestion("What is half of 0.2?", "0.1", "0.02", "0.2", "0.12", 0),
                new PlacementQuestion("If 10 workers build 10 walls in 10 days, how many days for 5 workers to build 5 walls?", "10", "5", "2", "20", 0),
                new PlacementQuestion("Which is not a prime number?", "2", "3", "4", "5", 2),
                new PlacementQuestion("Find next: 3, 6, 12, 24, ?", "48", "36", "30", "60", 0),
                new PlacementQuestion("If water boils at 100°C, what is freezing point of water?", "0°C", "100°C", "50°C", "10°C", 0),
                new PlacementQuestion("Which of these is an even number?", "7", "13", "22", "19", 2)
            );
            placmentQuestionRepository.saveAll(questions);
            System.out.println("Loaded " + questions.size() + " questions.");
        }
    }
}
