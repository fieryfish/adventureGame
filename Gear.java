
/**
 * Write a description of class Gear here.
 * 
 * @author (your description) 
 * @version (a version number or a date)
 */
public class Gear
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private int aggressivity;
    private int duration;

    /**
     * Constructor for objects of class Gear
     */
    public Gear(String name, String description)
    {
      this.name = name;
      this.description = description;
    }

    public void setAggressivity(int val)
    {
        this.aggressivity = val;
    }

    public int getAggressivity()
    {
        return this.aggressivity;
    }

    public void setName(String val)
    {
        this.name = val;
    }

    public String getName()
    {
        return this.name;
    }

    public void setDuration(int val)
    {
        this.duration=val;
    }

    public int getDuration()
    {
        return this.duration;
    }
}
