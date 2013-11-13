import java.util.ArrayList;

/**
 * This class is the main class of the "Queen saving" Game`
 *
 * We can create the characters of the game like knight, queen in this class.
 * Some rooms have the characters for player to deal with.
 *
 * @author  121880
 * @version 1.0 (Nov 2013)
 */
public class Person
{
    // instance variables
    private String description;
    public String name;
    public int aggressivity;
    Gear myGears = null;
    /**
     * Create a room named "name", described "description" and have aggressivity.
     * @param all the parameters have to be supplied,
     * first param is name, second is description and third one is aggressivity.
     *
     */
    public Person(String name, String description,int aggressivity)
    {
        this.name = name;
        this.description = description;
        this.aggressivity = aggressivity;
        System.out.println("Successfully initialize the character => "+description);
    }

    /**
     * setter method of the Person object
     * @param parameter is the gear of the characters
     */
    public void setGear(Gear flashlight)
    {
        myGears = flashlight;
    }


    /**
     * getter method of the Person object
     * @return the gear object of the characters'
     */
    public Gear getGear()
    {
        return myGears;
    }

}
