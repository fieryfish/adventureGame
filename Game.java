import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *  This class is the main class of the "queen saving" Game.
 *  "Queen saving" is a very simple, text based adventure game.  Player
 *  acts as a knight who can walk to other rooms by typing direction.
 *  Initially, the knight stands outside.
 *  Player's goal is to find and save the queen in the back room. However,
 *  except you, there are two kind of monsters named skeletonGuard and lichKing.
 *  You may meet them in some rooms. At first, you can kill skeletonGuard without
 *  any weapon, but you have to get sword to kill lichKing, or you will be killed.
 *  The queen's room is near the lichKing's room. Be cautious!
 *  Finding sword is not easy, you must get a flashLight to light the room.
 *  Unfortunately flashLight can be exhausted in 10 steps,
 *  so you should make your step more efficiently when you have the flashLight.
 *  What is more, if you meet a bird, it will bring you to the outside of the room
 *  
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes and 121880(Long Yu)
 * @version 1.0 (Nov 2013)
 *
 */

class Game 
{
    private Parser parser;
    private Room currentRoom;
    private HashMap gears;
    private HashMap characters;
    private HashMap rooms;
    boolean finished = false;
    private ArrayList<Room> birdNextRoom= new ArrayList<Room>();
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createGear();
        createCharacters();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the Characters
     */

    private void createCharacters()
    {
        characters = new HashMap();
        Person knight, skeletonGuardA, skeletonGuardB, lichKing, queen;
        knight = new Person("knight","Knight: I am going to save you, my queen!",15);
        characters.put("knight", knight);
        skeletonGuardA = new Person("skeletonGuard","SkeletonGuardA: I will kill you Knight",10);
        characters.put("skeletonGuardA", skeletonGuardA);
        skeletonGuardB = new Person("skeletonGuard","SkeletonGuardB: Darkness is coming,",10);
        characters.put("skeletonGuardB", skeletonGuardB);
        lichKing = new Person("lichKing","LichKing: This is your last day",20);
        characters.put("lichKing", lichKing);
        queen = new Person("queen","Queen: Dear knight, save me please!",0);
        characters.put("queen", queen);
    }

    /**
     * Create all the gears
     */
    private void createGear()
    {
        gears = new HashMap();
        Gear sword, flashLight, bird;
        sword = new Gear("sword", "Be with the sun! Aggressivity + 10");
        sword.setAggressivity(10);
        gears.put("sword", sword);
        flashLight = new Gear("flashLight", "Use me to find Queen and LichKing");
        flashLight.setDuration(10);
        gears.put("flashLight", flashLight);
        bird  = new Gear("bird", "I will take you to fight LichKing to save the queen");
        gears.put("bird", bird);
    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, dungeonB, dungeonC, dungeonD, dungeonE, dungeonF, dungeonG, dungeonH, dungeonI, dungeonJ, dungeonK, dungeonL;
        rooms = new HashMap();
        // create the rooms
        outside = new Room("outside the main entrance of the medieval castle");
        rooms.put("outside", outside);
        birdNextRoom.add((Room)rooms.get("outside"));
        dungeonB = new Room("in dungeonB");
        rooms.put("dungeonB", dungeonB);
        dungeonC = new Room("in dungeonC");
        rooms.put("dungeonC", dungeonC);
        dungeonD = new Room("in dungeonD");
        rooms.put("dungeonD", dungeonD);
        dungeonE = new Room("in dungeonE");
        rooms.put("dungeonE", dungeonE);
        dungeonF = new Room("in dungeonF");
        rooms.put("dungeonF", dungeonF);
        dungeonG = new Room("in dungeonG");
        rooms.put("dungeonG", dungeonG);
        dungeonH = new Room("in dungeonH");
        rooms.put("dungeonH", dungeonH);
        dungeonI = new Room("in dungeonI");
        rooms.put("dungeonI", dungeonI);
        dungeonJ = new Room("in dungeonJ");
        rooms.put("dungeonJ", dungeonJ);
        dungeonK = new Room("in dungeonK");
        rooms.put("dungeonK", dungeonK);
        dungeonL = new Room("in dungeonL");
        rooms.put("dungeonL", dungeonL);
        // initialise room exits
        setRoomExits();
        currentRoom = (Room)rooms.get("outside");  // start game outside
        // set the charaters in rooms
        setCharaters();
    }

    /**
     * Set the Charaters in some rooms
     */
    private void setCharaters()
    {
        setRoomWithCharater((Room) rooms.get("dungeonB"), (Person)characters.get("skeletonGuardA"));
        setRoomWithCharater((Room) rooms.get("dungeonE"), (Person)characters.get("skeletonGuardB"));
        setRoomWithCharater((Room) rooms.get("dungeonF"), (Person)characters.get("lichKing"));
        setRoomWithCharater((Room) rooms.get("dungeonG"), (Person)characters.get("queen"));
        setRoomWithGear((Room) rooms.get("dungeonL"), (Gear)gears.get("sword"));
        setRoomWithGear((Room) rooms.get("dungeonD"), (Gear)gears.get("flashLight"));
        setRoomWithGear((Room) rooms.get("dungeonK"), (Gear)gears.get("bird"));
    }

    /**
     * Set the interaction of thr rooms
     */
    private void setRoomExits()
    {
        ((Room)rooms.get("outside")).setExit("north", (Room) rooms.get("dungeonB"));
        ((Room)rooms.get("dungeonB")).setExit("south",(Room) rooms.get("outside"));
        ((Room)rooms.get("outside")).setExit("south", (Room) rooms.get("dungeonC"));
        ((Room)rooms.get("dungeonC")).setExit("north",(Room) rooms.get("outside"));
        ((Room)rooms.get("dungeonC")).setExit("south",(Room) rooms.get("dungeonD"));
        ((Room)rooms.get("dungeonD")).setExit("north",(Room) rooms.get("dungeonC"));
        ((Room)rooms.get("dungeonC")).setExit("east", (Room) rooms.get("dungeonE"));
        ((Room)rooms.get("dungeonE")).setExit("west", (Room) rooms.get("dungeonC"));
        ((Room)rooms.get("dungeonE")).setExit("east", (Room) rooms.get("dungeonF"));
        ((Room)rooms.get("dungeonF")).setExit("west", (Room) rooms.get("dungeonE"));
        ((Room)rooms.get("dungeonF")).setExit("east", (Room) rooms.get("dungeonG"));
        ((Room)rooms.get("dungeonG")).setExit("west", (Room) rooms.get("dungeonF"));
        ((Room)rooms.get("dungeonF")).setExit("north",(Room) rooms.get("dungeonH"));
        ((Room)rooms.get("dungeonH")).setExit("south",(Room) rooms.get("dungeonF"));
        ((Room)rooms.get("dungeonC")).setExit("west", (Room) rooms.get("dungeonI"));
        ((Room)rooms.get("dungeonI")).setExit("east", (Room) rooms.get("dungeonC"));
        ((Room)rooms.get("dungeonI")).setExit("north",(Room) rooms.get("dungeonK"));
        ((Room)rooms.get("dungeonK")).setExit("south",(Room) rooms.get("dungeonI"));
        ((Room)rooms.get("dungeonI")).setExit("west", (Room) rooms.get("dungeonJ"));
        ((Room)rooms.get("dungeonJ")).setExit("east", (Room) rooms.get("dungeonI"));
        ((Room)rooms.get("dungeonL")).setExit("south",(Room) rooms.get("dungeonB"));
        ((Room)rooms.get("dungeonB")).setExit("north",(Room) rooms.get("dungeonL"));

    }

    /**
     * Method of set charater in room
     * @param first param is the name of room
     * second param is the name of character
     */
    private void setRoomWithCharater(Room room, Person character)
    {
        room.setCharater(character);
    }

    /**
     * Method of set gear in room
     * @param first param is the name of room
     * second param is the name of gear
     */
    private void setRoomWithGear(Room room, Gear gear)
    {
        room.setGear(gear);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {
        printWelcome();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.


        while (!this.finished) {
            Command command = parser.getCommand();
            this.finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("You are the Knight whose goal is to save the Queen guarded by 2 SkeletonGuards and a LichKing.");
        System.out.println("Your initial aggressivity is 15, and you can add your aggressivity by finding a sword");
        System.out.println("As a knight, you can kill the SkeletonGuard without a sword,but to kill LichKing, you have to get your sword");
        System.out.println("Now, make your way to save your Queen!");
        System.out.println();
        System.out.println("Your valid command words are:");
        parser.showCommands();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param the command you want to give
     * @return whether the command lead to end of the game
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            wantToQuit = goRoom(command);
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You goal is to find the queen, Good luck");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command of the go direction command
     * @return whether the game is finished by the command of the command
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            //return ;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else 
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());

            minusFlashLight(currentRoom, (Person)characters.get("knight"));
            this.finished = hasBird();
            hasSword(currentRoom, (Person)characters.get("knight"));
            hasFlashLight(currentRoom, (Person)characters.get("knight"));
            this.finished = fightMonster(currentRoom, (Person)characters.get("knight"));
        }
        return this.finished;
    }

    /**
     * To see whether the romm has bird
     * @return whether the room has bird
     */
    private boolean hasBird()
    {
        boolean finish = false;
        if (!(currentRoom.gear==null))
        {
            if (currentRoom.gear.getName().equals("bird"))
            {
                System.out.println("You meet a bird, which drives you to outside again!");
                currentRoom = this.birdNextRoom.get(0);
                System.out.println(currentRoom.getLongDescription());
            }
        }
        return finish;
    }

    /**
     * When taking a step, the FlashLight would minus one time
     * @param first param is the room you currently in,
     * second param is the knight
     */
    private void minusFlashLight(Room currentRoom, Person knight)
    {
        if (!(knight.getGear()==null))
        {
            if (knight.getGear().getName().equals("flashLight"))
            {
                if (knight.getGear().getDuration()>=1)
                {
                    knight.getGear().setDuration(knight.getGear().getDuration()-1);
                    System.out.println("flashLight has "+knight.getGear().getDuration()+" times!");
                }
                else
                {
                    knight.setGear(null);
                    System.out.println("Your flashLight has used up");
                }
            }
        }
    }

    /**
     * You should hold the flash light to get the gear,
     * this method is judge whether you have the flash light
     * and if you have you can get the gear
     * @param first param is the room you currently in,
     * second param is the knight
     */
    private void hasFlashLight(Room currentRoom, Person knight)
    {
        if  (!(currentRoom.gear==null))
        {
            if (currentRoom.gear.getName().equals("flashLight"))
            {
                System.out.println("Good Luck, Now you have "+ currentRoom.gear.getName());
                knight.setGear(currentRoom.gear);
                currentRoom.destroyGear(currentRoom.gear);
            }
        }
    }

    /**
     * To see whether the room has sword
     * if yes, the knight can add Aggressivity
     * @param first param is the room you currently in,
     * second param is the knight
     */
    private void hasSword(Room currentRoom, Person knight)
    {
        if (!(currentRoom.gear==null) && !(knight.getGear()==null))
        {
            if (currentRoom.gear.getName().equals("sword") && knight.getGear().getName().equals("flashLight"))
            {
                System.out.println("Good Luck, Now you have "+ currentRoom.gear.getName());
                knight.aggressivity += currentRoom.gear.getAggressivity();
                currentRoom.destroyGear(currentRoom.gear);
            }
        }
    }

    /**
     * This method is to judge whether you can fight the Monster
     * if yes, game go on
     * if no , game over
     * @param first param is the room you currently in,
     * second param is the knight
     * @return whether you can beat the monster
     */ 
    private boolean fightMonster(Room currentRoom, Person knight)
    {
        boolean finish = false;
        if (currentRoom.character != null){
            System.out.println("Accidently,you meet a "+ currentRoom.character.name);
            if (currentRoom.character.equals((Person)characters.get("queen")))
            {
                System.out.println("Congratulations! You have saved "+ currentRoom.character.name);
                finish = true;
            }
            else if (currentRoom.character.aggressivity >= knight.aggressivity)
            {
                System.out.println("Oh,no!You haven't got sword, you were killed by "+ currentRoom.character.name);
                finish = true;
            }
            else
            {
                System.out.println("you killed a "+ currentRoom.character.name+". Good Job!");
                currentRoom.destroyCharater(currentRoom.character);
            }
        }
        return finish;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     * @param the command you type
     * @return wheter quit successfully
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
