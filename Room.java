import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "Queen saving" Game`
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes and 121880
 * @version 1.0 (Nov 2013)
 */

class Room 
{
    private String description;
    private HashMap exits;        // stores exits of this room.
    public Person character;
    public Gear gear=null;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     * @param the description
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap();
    }

    /**
     * setter method of the room object
     * @param parameter is the character of the room
     */
    public void setCharater(Person character)
    {
        this.character = character;
    }

    /**
     * setter method of the room object
     * @param parameter is the gear of the room
     */
    public void setGear(Gear gear)
    {
        this.gear = gear;
    }

    /**
     * destroy the gear of the room
     * @param parameter is the gear you want to destroy of the room
     */
    public void destroyGear(Gear gear)
    {
        this.gear = null;
    }

    /**
     * destroy the character of the room
     * @param parameter is the character you want to destroy of the room
     */
    public void destroyCharater(Person character)
    {
        this.character = null;
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return a long description
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return the string with exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set keys = exits.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    /**
     * @param paramter is the direction of the exit.
     * @return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return (Room)exits.get(direction);
    }
}

