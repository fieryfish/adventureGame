import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room dungeonB;
    private Room dungeonC;
    private Room dungeonD;
    private Room dungeonE;
    private Room dungeonF;
    private Room dungeonG;
    private Room dungeonH;
    private Room dungeonI;
    private Room dungeonJ;
    private Room dungeonK;
    private Room dungeonL;

    private Person skeletonGuardA;
    private Person skeletonGuardB;
    private Person lichKing;
    private Person queen;
    private Person knight;
    private Gear sword;
    private Gear flashLight;
    private Gear bird;
    boolean finished = false;
      private  ArrayList<Room> birdRoom= new ArrayList<Room>();
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
      // create the Characters
      //
      knight = new Person("knight","Knight: I am going to save you, my queen!",15);
      skeletonGuardA = new Person("skeletonGuard","SkeletonGuardA: I will kill you Knight",10);
      skeletonGuardB = new Person("skeletonGuard","SkeletonGuardB: Darkness is coming,",10);
      lichKing = new Person("lichKing","LichKing: This is your last day",20);
      queen = new Person("queen","Queen: Dear knight, save me please!",0);
    }

    /**
     * Create all the gears
     */
    private void createGear()
    {
      // create the Gears
      sword = new Gear("sword", "Be with the sun! Aggressivity + 10");
      sword.setAggressivity(10);
      flashLight = new Gear("flashLight", "Use me to find Queen and LichKing");
      flashLight.setDuration(10);
      bird  = new Gear("bird", "I will take you to fight LichKing to save the queen");
    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, dungeonB, dungeonC, dungeonD, dungeonE, dungeonF, dungeonG, dungeonH, dungeonI, dungeonJ, dungeonK, dungeonL;
        // create the rooms
        outside = new Room("outside the main entrance of the medieval castle");
        dungeonB = new Room("in dungeonB");
        dungeonC = new Room("in dungeonC");
        dungeonD = new Room("in dungeonD");
        dungeonE = new Room("in dungeonE");
        dungeonF = new Room("in dungeonF");
        this.birdRoom.add(outside);
        dungeonG = new Room("in dungeonG");
        dungeonH = new Room("in dungeonH");
        dungeonI = new Room("in dungeonI");
        dungeonJ = new Room("in dungeonJ");
        dungeonK = new Room("in dungeonK");
        dungeonL = new Room("in dungeonL");
        // initialise room exits
        outside.setExit("north", dungeonB);
        dungeonB.setExit("south", outside);
        outside.setExit("south", dungeonC);
        dungeonC.setExit("north", outside);
        dungeonC.setExit("south", dungeonD);
        dungeonD.setExit("north", dungeonC);
        dungeonC.setExit("east", dungeonE);
        dungeonE.setExit("west", dungeonC);
        dungeonE.setExit("east", dungeonF);
        dungeonF.setExit("west", dungeonE);
        dungeonF.setExit("east", dungeonG);
        dungeonG.setExit("west", dungeonF);
        dungeonF.setExit("north", dungeonH);
        dungeonH.setExit("south", dungeonF);

        dungeonC.setExit("west", dungeonI);
        dungeonI.setExit("east", dungeonC);

        dungeonI.setExit("north", dungeonK);
        dungeonK.setExit("south", dungeonI);

        dungeonI.setExit("west", dungeonJ);
        dungeonJ.setExit("east", dungeonI);

        dungeonL.setExit("south", dungeonB);
        dungeonB.setExit("north", dungeonL);

        currentRoom = outside;  // start game outside

        setRoomWithCharater(dungeonB, skeletonGuardA);
        setRoomWithCharater(dungeonE, skeletonGuardB);
        setRoomWithCharater(dungeonF, lichKing);
        setRoomWithCharater(dungeonG, queen);
        setRoomWithGear(dungeonL, sword);
        setRoomWithGear(dungeonD, flashLight);
        setRoomWithGear(dungeonK, bird);
System.out.println("construct:FFFFROOM:"+dungeonF);
    }

    private void setRoomWithCharater(Room room,Person character)
    {
      room.setCharater(character);
    }

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
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
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

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
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


            minusFlashLight(currentRoom, knight);
            this.finished = hasBird(knight);
            hasSword(currentRoom, knight);
            hasFlashLight(currentRoom, knight);
            this.finished = fightMonster(currentRoom, knight);
        }
        return this.finished;
    }

    public boolean hasBird(Person knight)
    {
      boolean finish = false;
      if (!(currentRoom.gear==null))
      {
        if (currentRoom.gear.getName().equals("bird"))
        {
          System.out.println("You meet a bird, which drives you to outside again!");
          currentRoom = this.birdRoom.get(0);
            System.out.println(currentRoom.getLongDescription());
        }
      }
      return finish;
    }

    public void minusFlashLight(Room currentRoom, Person knight)
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

    public void hasFlashLight(Room currentRoom, Person knight)
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

    public void hasSword(Room currentRoom, Person knight)
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

    public boolean fightMonster(Room currentRoom, Person knight)
    {
      boolean finish = false;
      if (currentRoom.character != null){
        System.out.println("Accidently,you meet a "+ currentRoom.character.name);
        if (currentRoom.character.equals(queen))
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
