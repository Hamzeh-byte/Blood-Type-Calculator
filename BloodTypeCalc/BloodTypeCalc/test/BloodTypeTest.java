import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This class is responsible for testing.
 * @author Hamzeh Kangaroo
 */
public class BloodTypeTest {

    /**
     * Indicates start, runs before all tests.
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting tests...");
    }

    /**
     * Indicates finish, runs after all tests.
     */
    @AfterAll
    static void afterAll() {
        System.out.println("Finished testing");
    }

    /**
     * Checks correctness of convertToPhGroup method
     * in BloodTypeCalculator utility class.
     * Results are being checked against online table data.
     */
    @Test
    void testConvertToPhGroup() {
        Assertions.assertEquals(Ph.POS, BloodTypeCalculator.convertToPhGroup(PhGenes.NP));
        Assertions.assertEquals(Ph.NEG, BloodTypeCalculator.convertToPhGroup(PhGenes.NN));
        Assertions.assertEquals(Ph.POS, BloodTypeCalculator.convertToPhGroup(PhGenes.NP));
    }

    /**
     * Checks correctness of convertToBloodGroup method
     * in BloodTypeCalculator utility class.
     * Results are being checked against online table data.
     */
    @Test
    void testConvertToBloodGroup() {
        Assertions.assertEquals(BloodType.A, BloodTypeCalculator.convertToBloodGroup(ABOGenes.AA));
        Assertions.assertEquals(BloodType.A, BloodTypeCalculator.convertToBloodGroup(ABOGenes.AO));
        Assertions.assertEquals(BloodType.B, BloodTypeCalculator.convertToBloodGroup(ABOGenes.BB));
        Assertions.assertEquals(BloodType.B, BloodTypeCalculator.convertToBloodGroup(ABOGenes.BO));
        Assertions.assertEquals(BloodType.O, BloodTypeCalculator.convertToBloodGroup(ABOGenes.OO));
        Assertions.assertEquals(BloodType.AB,BloodTypeCalculator.convertToBloodGroup(ABOGenes.AB));
    }

    /**
     * Checks correctness of computeABOGenes method
     * in BloodTypeCalculator utility class.
     * Results are being checked against online table data.
     */
    @Test
    void testComputeABOGenes() {
        Assertions.assertArrayEquals(new ABOGenes[]{ABOGenes.AA, ABOGenes.AO,  ABOGenes.OO}, BloodTypeCalculator.computeABOGenes(ABOGenes.AO, ABOGenes.AO));
        Assertions.assertArrayEquals(new ABOGenes[]{ABOGenes.AB, ABOGenes.BB}, BloodTypeCalculator.computeABOGenes(ABOGenes.AB, ABOGenes.BB));
    }

    /**
     * Checks correctness of computePhGenes method
     * in BloodTypeCalculator utility class.
     * Results are being checked against online table data.
     */
    @Test
    void testComputePhGenes() {
        Assertions.assertArrayEquals(new PhGenes[] {PhGenes.NP}, BloodTypeCalculator.computePhGenes(PhGenes.NN, PhGenes.PP));
        Assertions.assertArrayEquals(new PhGenes[] {PhGenes.NN, PhGenes.NP}, BloodTypeCalculator.computePhGenes(PhGenes.NP, PhGenes.NN));
    }

}
