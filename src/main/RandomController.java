import uk.ac.warwick.dcs.maze.logic.*;
import java.awt.Point;

public class RandomController implements IRobotController {
    // the robot in the maze
    private IRobot robot;
    // a flag to indicate whether we are looking for a path
    private boolean active = false;
    // a value (in ms) indicating how long we should wait
    // between moves
    private int delay;

    // this method is called when the "start" button is clicked
    // in the user interface
    public void start() {
        this.active = true;

        // loop while we haven't found the exit and the agent
        // has not been interrupted
        while(!robot.getLocation().equals(robot.getTargetLocation()) && active) {
        //robot generates a random number till it is not facing a wall
        if (Math.random() <= 0.125||robot.look(IRobot.AHEAD) == IRobot.WALL) {
         do {  // generate a random number between 0-3 (inclusive)
            int rand = (int)Math.floor(Math.random()*4);
            //math floor to generate equal probability
            // math floor rounds to lower integer value

            // turn into one of the four directions, as determined
            // by the random number that was generated:
            // 0: ahead
            // 1: left
            // 2: right
            // 3: behind
            switch (rand) {
            case 0:
                robot.face(IRobot.AHEAD);
                robot.getLogger().log(IRobot.AHEAD);
                break;
            case 1:
                robot.face(IRobot.LEFT);
                robot.getLogger().log(IRobot.LEFT);
                break;
            case 2:
                robot.face(IRobot.RIGHT);
                robot.getLogger().log(IRobot.RIGHT);
                break;
            case 3:
                robot.face(IRobot.BEHIND);
                robot.getLogger().log(IRobot.BEHIND);
                break;
            }
          } while (robot.look(IRobot.AHEAD) == IRobot.WALL);
           // move one step into the direction the robot is facing
            robot.advance();;
          }
        else robot.advance();
            // robot advances if there is no wall ahead
            // wait for a while if we are supposed to
            if (delay > 0)
                robot.sleep(delay);

       }
    }

    // this method returns a description of this controller
    public String getDescription() {
       return "A controller which randomly chooses where to go";
    }

    // sets the delay
    public void setDelay(int millis) {
       delay = millis;
    }

    // gets the current delay
    public int getDelay() {
       return delay;
    }

    // stops the controller
    public void reset() {
       active = false;
    }

    // sets the reference to the robot
    public void setRobot(IRobot robot) {
       this.robot = robot;
    }
}
