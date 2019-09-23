/**
 * This enum is used to store BloodType constants
 */
enum BloodType { A, B, O, AB }

/**
 * This enum is used to store Ph factor constants.
 * toString was overridden accordingly.
 */
enum Ph {
    POS {
        @Override
        public String toString() { return "+"; }
    },
    NEG {
        @Override
        public String toString() { return "-"; }
    }
}

/**
 * This enum is used to store ABOGene constants.
 * ABOGene constants are used in computation of blood type.
 */
enum ABOGenes { AA, BB, AB, OO, AO, BO}

/**
 * This enum is used to store PhGene factor constants.
 */
enum PhGenes { NN, PP, NP }


/**
 * This class is responsible for blood type and gene calculations.
 * BloodTypeCalculator is a utility class so it only has static methods.
 * @author Hamzeh Kangaroo
 *
 */
public final class BloodTypeCalculator {

    /**
     * This method computes child's possible Ph genes.
     *
     * An array of child's possible Ph factor genes is being retrieved
     * from a 3d array of all possible parent's PhGene factor combinations.
     * @param parent1 Father's Ph factor genes.
     * @param parent2 Mother's Ph factor genes.
     * @return an array of child's possible Ph genes.
     */
    static PhGenes[] computePhGenes(PhGenes parent1, PhGenes parent2) {
        //                                       NEG               |        POS              |          NEG/POS
        PhGenes phGenesMatrix[][][] = { { {PhGenes.NN            }, {PhGenes.NP            }, {PhGenes.NN, PhGenes.NP             }},  // NEG
                                        { {PhGenes.NP            }, {PhGenes.PP            }, {PhGenes.NP, PhGenes.PP             }},  // POS
                                        { {PhGenes.NN, PhGenes.NP}, {PhGenes.NP, PhGenes.PP}, {PhGenes.NN, PhGenes.NP, PhGenes.PP}}};  // NEG/POS
        // Returns a 1d array form a 3d array
        return phGenesMatrix[parent1.ordinal()][parent2.ordinal()];
    }

    /**
     * This method computes child's possible ABO genes.
     *
     * An array of child's possible ABO genes is being retrieved
     * from a 3d array of all possible parent's ABO combinations.
     * @param parent1 Father's ABO genes.
     * @param parent2 Mother's ABO genes.
     * @return an array of child's possible ABO genes.
     */
    static ABOGenes[] computeABOGenes(ABOGenes parent1, ABOGenes parent2) {

        ABOGenes ABOGenesMatrix[][][] = {
                {{ABOGenes.AA             }, {ABOGenes.AB             }, {ABOGenes.AA, ABOGenes.AB                          }, {ABOGenes.AO             }, {ABOGenes.AA, ABOGenes.AO                          }, {ABOGenes.AB,ABOGenes.AO                           }}, // AA
                {{ABOGenes.AB             }, {ABOGenes.BB             }, {ABOGenes.AB, ABOGenes.BB                          }, {ABOGenes.BO             }, {ABOGenes.AB, ABOGenes.BO                          }, {ABOGenes.BB, ABOGenes.BO                          }}, // BB
                {{ABOGenes.AA, ABOGenes.AB}, {ABOGenes.AB, ABOGenes.BB}, {ABOGenes.AA, ABOGenes.BB, ABOGenes.AB             }, {ABOGenes.AO,ABOGenes.BO }, {ABOGenes.AA, ABOGenes.AO, ABOGenes.BO, ABOGenes.AB}, {ABOGenes.BB, ABOGenes.BO, ABOGenes.AB, ABOGenes.AO}}, // AB
                {{ABOGenes.AO             }, {ABOGenes.BO             }, {ABOGenes.AO, ABOGenes.BO                          }, {ABOGenes.OO             }, {ABOGenes.AO, ABOGenes.OO                          }, {ABOGenes.BO, ABOGenes.OO                          }}, // OO
                {{ABOGenes.AA, ABOGenes.AO}, {ABOGenes.AB, ABOGenes.BO}, {ABOGenes.AA, ABOGenes.AB, ABOGenes.AO, ABOGenes.BO}, {ABOGenes.AO, ABOGenes.OO}, {ABOGenes.AA, ABOGenes.AO, ABOGenes.OO             }, {ABOGenes.AO, ABOGenes.BO, ABOGenes.AB, ABOGenes.OO}}, // AO
                {{ABOGenes.AO, ABOGenes.AB}, {ABOGenes.BB, ABOGenes.BO}, {ABOGenes.AB, ABOGenes.AO, ABOGenes.BB, ABOGenes.BO}, {ABOGenes.BO, ABOGenes.OO}, {ABOGenes.AB, ABOGenes.AO, ABOGenes.BO, ABOGenes.OO}, {ABOGenes.BB, ABOGenes.BO, ABOGenes.OO             }}  // BO
// Parent Gens                 AA           |           BB              |           AB                                        |          OO               |           AO                                        |               BO
        };
        // Returns a 1d array of genes from a 3d array
        return ABOGenesMatrix[parent1.ordinal()][parent2.ordinal()];
    }

    /**
     * Converts ABO genes to an actual blood type.
     * @param aboGenes person's ABO genes
     * @return person's blood type
     */
    static BloodType convertToBloodGroup(ABOGenes aboGenes) {

        if (aboGenes == ABOGenes.AA)
            return BloodType.A;
        if (aboGenes == ABOGenes.AO)
            return BloodType.A;
        if (aboGenes == ABOGenes.BB)
            return BloodType.B;
        if (aboGenes == ABOGenes.BO)
            return BloodType.B;
        if (aboGenes == ABOGenes.OO)
            return BloodType.O;
        else
            return BloodType.AB;
    }

    /**
     * Converts Ph factor genes to an actual Ph factor
     * @param phGenes person's Ph genes
     * @return person's Ph factor
     */
    static Ph convertToPhGroup(PhGenes phGenes) {
        if (phGenes == PhGenes.NN)
            return Ph.NEG;
        if (phGenes == PhGenes.NP)
            return Ph.POS;
        else
            return Ph.POS;
    }


}
