import java.util.ArrayList;

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    private String description;
    public String name;
    public int aggressivity;
    Gear myGears = null;
    /**
     * Constructor for objects of class Person
     */
    public Person(String name, String description,int aggressivity)
    {
        this.name = name;
        this.description = description;
        this.aggressivity = aggressivity;
        System.out.println("Successfully initialize the character => "+description);
    }

    public void setGear(Gear flashlight)
    {
      myGears = flashlight;
    }

    public Gear getGear()
    {
      return myGears;
    }

}
