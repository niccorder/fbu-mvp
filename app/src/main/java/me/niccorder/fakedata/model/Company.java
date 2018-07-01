package me.niccorder.fakedata.model;

/**
 * Data model which represents a company whom writes, or creates articles.
 */
public class Company {

    /**
     * A unique id used to identify this company.
     */
    public final long id;

    /**
     * The name of the company.
     */
    public final String name;

    /**
     * A number between [0 - 1] which is a general measurement of how biased the company is toward
     * liberal policies.
     *
     * 0 means the company completely conservative.
     * 1 means the company is completely liberal.
     */
    public final float liberalBias;

    public Company(long id, String name, float liberalBias) {
        this.id = id;
        this.name = name;
        this.liberalBias = liberalBias;
    }
}
