package com.wakachamo.kclmoduleweighter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Create a new instance, set some modules using the <code>setModule</code>
 * method, then calculate the <code>getWeightedAverage</code>.
 * Created by Joaquim on 17/03/2014.
 */
public class WeightedAverageCalculator {

    /**
     * Levels
     */
    private static final int LEVEL_MASTERS = 7;
    private static final int LEVEL_BSC_3_YEAR = 6;
    private static final int LEVEL_BSC_2_YEAR = 5;
    private static final int LEVEL_BSC_1_YEAR = 4;

    /**
     * Minimum credits required for accreditation
     */
    private static final int MASTERS_CREDITS_MIN = 120;
    private static final int BSC_CREDITS_MIN = 90;
    
    private static final int ATOMIC_CREDIT = 15;

    private final boolean masters;
    private int highestModuleLevel;
    private int lowestModuleLevel;
    private final HashMap<Integer, ArrayList<Module>> moduleLevels;

    /**
     * Creates a new instance of the <code>WeightedAverageCalculator</code>.
     * @param masters Whether or not the student is in the MSci programme
     */
    public WeightedAverageCalculator(boolean masters) {
        this.masters = masters;
        this.highestModuleLevel = Integer.MIN_VALUE;
        this.lowestModuleLevel = Integer.MAX_VALUE;
        this.moduleLevels = new HashMap<>();
    }

    /**
     *
     * @return Whether or not the student is in the MSci programme
     */
    public boolean isMasters() {
        return this.masters;
    }

    /**
     * Register a module and its mark.
     * @param name The module code, e.g. 4CCS1PRP
     * @param credits Number of credits in the module
     * @param grade Mark given
     */
    public void setModule(String name, int credits, int grade) {
        Module module = new Module(name, credits, grade);
        ArrayList<Module> modulesForLevel = this.modulesForLevel(module.getLevel());
        int times = credits / ATOMIC_CREDIT;
        for (int i = 0; i < times; i++) {
            //We're splitting any modules whose credit levels are higher than 15
            //so that that correct number of credits are counted for the 
            //highest scored sums
            Module replacementModule = new Module(name, ATOMIC_CREDIT, grade);
            modulesForLevel.add(replacementModule);
        }
        this.highestModuleLevel = Math.max(this.highestModuleLevel, module.getLevel());
        this.lowestModuleLevel = Math.min(this.lowestModuleLevel, module.getLevel());
    }


    private ArrayList<Module> modulesForLevel(int level) {
        Integer levelInteger = new Integer(level);
        ArrayList<Module> modulesForLevel = this.moduleLevels.get(levelInteger);
        if (modulesForLevel == null) {
            modulesForLevel = new ArrayList<>();
            this.moduleLevels.put(levelInteger, modulesForLevel);
        }
        return modulesForLevel;
    }

    /**
     *
     * @return The weighted average mark for the entire course.
     */
    public double getWeightedAverage() {
        int weightedMarkSum = 0;
        int weightedCreditSum = 0;
        for (int i = this.highestModuleLevel; i >= this.lowestModuleLevel; i--) {
            ArrayList<Module> modulesForLevel = this.modulesForLevel(i);
            if (i == this.highestModuleLevel) {
                //We have to sort the marks if we're dealing with the highest level
                Collections.sort(modulesForLevel, new Comparator<Module>() {

                    @Override
                    public int compare(Module o1, Module o2) {
                        return Integer.signum(o2.getGrade() - o1.getGrade());
                    }
                });
            }
            //System.out.println(modulesForLevel);

            int creditsTotal = 0;

            for (int j = 0; j < modulesForLevel.size(); j++) {
                Module module = modulesForLevel.get(j);
                int level = module.getLevel();
                if (i == this.highestModuleLevel) {
                    if (this.isMasters()) {
                        if (creditsTotal >= MASTERS_CREDITS_MIN) {
                            level--;
                        }
                    } else {
                        if (creditsTotal >= BSC_CREDITS_MIN) {
                            level--;
                        }
                    }
                }

                int weight = this.weightForLevel(level);

                int weightedMark = module.getGrade() * module.getCredits() * weight;
                weightedMarkSum += weightedMark;

                int weightedCredit = module.getCredits() * weight;
                weightedCreditSum += weightedCredit;

                creditsTotal += module.getCredits();
            }
        }

        double c;
        c = (double)weightedMarkSum / (double)weightedCreditSum;

        return c;
    }

    private int weightForLevel(int level) {
        int weight = 0;
        switch (level) {
            case LEVEL_MASTERS:
                weight = 7;
                break;
            case LEVEL_BSC_3_YEAR:
                weight = 5;
                break;
            case LEVEL_BSC_2_YEAR:
                weight = 3;
                break;
            case LEVEL_BSC_1_YEAR:
                weight = 1;
                break;
        }
        return weight;
    }
}
