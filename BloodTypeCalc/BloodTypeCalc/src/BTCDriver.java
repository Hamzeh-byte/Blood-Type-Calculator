import java.util.*;

/**
 * This class is responsible for all IO operations.
 * Has a main method.
 * @author Hamzeh Kangaroo
 */
public final class BTCDriver {

    /**
     * Scanner object, needed for IO
     */
    private static Scanner in;

    /**
     * This method is responsible for predicting persons's blood group based of parental genes.
     * First person's ABO and Ph genes are computed,
     * This data is later used to predict person's blood group and ph factor
     * @param fABOGenes father's ABO genes.
     * @param fPhFactor father's Ph genes.
     * @param mABOGenes mother's ABO genes.
     * @param mPhFactor mother's Ph genes.
     * @return a string of data containing person's ABO and Ph genetic prediction as well as blood type and Ph factor possibilities.
     */
    private static String startBTC(String fABOGenes, String fPhFactor, String mABOGenes, String mPhFactor ) {

        List<String> possibleABOGenes = Arrays.asList("AA", "AB", "AO", "BB", "BO", "OO");

        List<String> possiblePhGenes = Arrays.asList("PP", "NN", "NP");

        if (!possibleABOGenes.contains(fABOGenes) || !possibleABOGenes.contains(mABOGenes))
            throw new BTCException("Impossible ABO gene format, possible ABO gene format is: " + possibleABOGenes.toString());

        if (!possiblePhGenes.contains(fPhFactor) || !possiblePhGenes.contains(mPhFactor))
            throw new BTCException("Impossible Ph gene format, possible Ph gene format is: " + possiblePhGenes.toString());


        ABOGenes aboPrediction[] = BloodTypeCalculator.computeABOGenes( ABOGenes.valueOf(fABOGenes), ABOGenes.valueOf(mABOGenes) );
        PhGenes phGenesPrediction[] = BloodTypeCalculator.computePhGenes( PhGenes.valueOf(fPhFactor), PhGenes.valueOf(mPhFactor) );

        BloodType bloodTypePrediction[] = new BloodType[aboPrediction.length];
        Ph phPrediction[] = new Ph[phGenesPrediction.length];

        for (int i = 0; i < phPrediction.length; i++)
            phPrediction[i] = BloodTypeCalculator.convertToPhGroup(phGenesPrediction[i]);

        for (int i = 0; i < aboPrediction.length; i++)
            bloodTypePrediction[i] = BloodTypeCalculator.convertToBloodGroup(aboPrediction[i]);


        return "PREDICTED ABO GENES: " + Arrays.toString(aboPrediction) + "\n" + "PREDICTED PH GENES: " + Arrays.toString(phGenesPrediction) + "\n\n" +
                "POSSIBLE BLOOD GROUP(S): " + Arrays.toString(bloodTypePrediction) + "\n" + "POSSIBLE PH FACTOR(S): " + Arrays.toString(phPrediction);
    }


    /**
     * Prints out the menu,
     * Handles IO
     */
    private static void menu() {

        System.out.println("************************************************************************************************************************************************");
        System.out.println("THIS APPLICATION LETS YOU DETERMINE ALL POSSIBLE BLOOD TYPE VARIATIONS OF A PERSON BASED OF GENES/BLOOD TYPE CHARACTERISTICS OF THEIR PARENTS ");
        System.out.println("************************************************************************************************************************************************");

        boolean correctInputs = false;
        do {
            try {
            System.out.println("************************************************************************************************************************************************ \n" +
                    "CHOOSE APPLICATION MODE         \n" +
                    "1. ENTER PARENT'S GENES         \n" +
                    "9. EXIT                         \n" +
                    "************************************************************************************************************************************************");

            in = new Scanner(System.in);
            int selection = in.nextInt();


            in = new Scanner(System.in);
            switch (selection) {
                case 1:
                    System.out.print("Enter father's ABO genes: ");
                    String fABOGenes = in.nextLine();
                    System.out.print("Enter father's Ph genes");
                    String fPhFactor = in.nextLine();
                    System.out.print("Enter mother's ABO genes: ");
                    String mABOGenes = in.nextLine();
                    System.out.print("Enter mother's Ph genes: ");
                    String mPhFacto = in.nextLine();

                    String data = startBTC(fABOGenes, fPhFactor, mABOGenes, mPhFacto);
                    System.out.println(data);
                    break;
                case 9:
                    System.out.println("Thanks, goodbye!");
                    correctInputs = true;
                default:
                    throw new InputMismatchException("Invalid input");
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!correctInputs);
    }

    /**
     * Program's main method
     * @param args console arguments
     */
    public static void main(String... args) {

        // Starts application.
        menu();
    }

}
