package com.wakachamo.kclmoduleweighter;

/**
 * Created by Joaquim on 17/03/2014.
 */
public class Module {
    private final String code; //eg 4CCS1PRP
    private final int level; //4, 5 or 6
    private final int credits;
    private final int grade;

    /**
     * Create a new model object given its name, year and whether or not it's a year-long module.
     * @param code The code name of the module, e.g. 4CCS1PRP
     * @param credits Number of credits attributed to the module
     * @param grade Grade received
     */
    public Module(String code, int credits, int grade) {
        this.code = code;

        //Get the level from the code name
        this.level = Character.getNumericValue(code.charAt(0));
        this.credits = credits;
        this.grade = grade;
    }

    /**
     *
     * @return The code name of the module
     */
    public String getCode() {
        return this.code;
    }

    /**
     *
     * @return The level of the module
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return The number of credits attributed to the module
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     *
     * @return The grade received
     */
    public int getGrade() {
        return this.grade;
    }
}
