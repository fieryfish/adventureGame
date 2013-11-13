
/**
 * This class is the main class of the "Queen saving" Game`
 *
 * This class can create gear in some room,
 * as well as set and get the attributions of the gears like name.
 *
 * @author  121880
 * @version 1.0 (Nov 2013)
 */
public class Gear
{
    // instance variables
    private String name;
    private String description;
    private int aggressivity;
    private int duration;

    /**
     * Constructor for objects of class Gear
     * @param first param is the name of the gear.
     * second param is the description of the gear
     */
    public Gear(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    /**
     * setter method of the Gear object
     * @param the parameter is the number of the aggressivity of the Gear
     */

    public void setAggressivity(int val)
    {
        this.aggressivity = val;
    }

    /**
     * getter method of the Gear object
     * @return the number of the aggressivity of the Gear
     */
    public int getAggressivity()
    {
        return this.aggressivity;
    }

    /**
     * setter method of the Gear object
     * @param parameter is the name of the Gear
     */

    public void setName(String val)
    {
        this.name = val;
    }

    /**
     * getter method of the Gear object
     * @return the string of the name of the Gear
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * setter method of the Gear object
     * @param parameter is the duration of the Gear
     */
    public void setDuration(int val)
    {
        this.duration=val;
    }

    /**
     * getter method of the Gear object
     * @return the number of the duration of the Gear
     */
    public int getDuration()
    {
        return this.duration;
    }
}
