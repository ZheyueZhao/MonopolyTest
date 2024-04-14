package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import org.bihe.model.Data;
import org.bihe.model.Estate;
import org.bihe.model.Person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcreteEstate extends Estate {
    public ConcreteEstate(String name, int panelNo, int price, int rent, int mortgage, int unMortgage) {
        super(name, panelNo, price, rent, mortgage, unMortgage);
    }
    // Implement constructors and methods of ConcreteEstate
}
public class DataTest {

    private HashMap<String, Person> players;
    private HashMap<Integer, Estate> estates;
    private Data testData;


    @BeforeEach
    public void setUp() {
        // Create sample players
        players = new HashMap<>();
        players.put("Player1", new Person("john","980428",2,29));
        players.put("Player2", new Person("josh","980428",3,40));


        estates = new HashMap<>();
        estates.put(1, new ConcreteEstate("estate1", 2, 5,2,3,8));
        estates.put(2, new ConcreteEstate("estate2", 3, 6,2,4,8));

        // Create test data
        testData = new Data(players, estates, 1, 0, 5);
    }

    @Test
    public void testConstructorWithMove() {
        assert testData.isPlayerMove() == true;
        assertEquals(players, testData.getPlayers());
        assertEquals(estates, testData.getEstates());
        assertEquals(1, testData.getPieceNumber());
        assertEquals(0, testData.getLocation());
        assertEquals(5, testData.getNewLocation());
    }

    @Test
    public void testConstructorWithoutMove() {
        Data testDataWithoutMove = new Data(players, estates);
        assert testDataWithoutMove.isPlayerMove() == false;
        assertEquals(players, testDataWithoutMove.getPlayers());
        assertEquals(estates, testDataWithoutMove.getEstates());
    }

    // Add more tests as needed
}