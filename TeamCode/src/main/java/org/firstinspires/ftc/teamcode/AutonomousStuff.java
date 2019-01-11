package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


/**
 * Created by bpatterson on 11/27/2017.
 */

@Autonomous(name="Autonomous Stuff")
public class AutonomousStuff extends LinearOpMode {

    private BetterTankHardware hardware;


    @Override
    public void runOpMode() throws InterruptedException {
        hardware = new BetterTankHardware(hardwareMap);
        //int red = robot.getColorSensor().red();

        //Set arm to get robot down
        hardware.armSeg2.setPower(.375);
        sleep(2000);
        hardware.armSeg1.setPower(0);
        sleep(500);

        //Turn Away to dislodge hook
        hardware.setDriveMotors(-.2,.2);
        hardware.armSeg2.setPower(-.375);
        sleep(750);
        hardware.setDriveMotors(0,0);
        sleep(2000);
        hardware.armSeg1.setPower(0);

        //Go forward Away from center
        hardware.setDriveMotors(.5,.5);
        sleep(500);
        hardware.setDriveMotors(0,0);
        sleep(500);

        //Turn Back
        hardware.setDriveMotors(.5,-.5);
        sleep(1000);
        hardware.setDriveMotors(0,0);

        int highestColor = 0;
        if(hardware.colorSensor.green() > hardware.colorSensor.blue()){
            highestColor = hardware.colorSensor.green();
        }
        else{
            highestColor = hardware.colorSensor.blue();
        }

        if(hardware.colorSensor.red() > highestColor + 150){
            //This object is Yellow
            hardware.setDriveMotors(.25,.25);
            sleep(1000);
            hardware.setDriveMotors(0,0);
        }
        else {
            //This object is white(?)
            hardware.setDriveMotors(.2,-.2);
            sleep(750);
            hardware.setDriveMotors(0,0);

            if(hardware.colorSensor.green() > hardware.colorSensor.blue()){
                highestColor = hardware.colorSensor.green();
            }
            else{
                highestColor = hardware.colorSensor.blue();
            }

            //Try the other one, maybe it's yellow?
            if(hardware.colorSensor.red() > highestColor + 150){
                hardware.setDriveMotors(.25,.25);
                sleep(1000);
                hardware.setDriveMotors(0,0);
            }
            else{
                //Out of objects, just go to the last one

                hardware.setDriveMotors(-.2,.2);
                sleep(1500);
                hardware.setDriveMotors(0,0);
                hardware.setDriveMotors(.25,.25);
                sleep(1000);
                hardware.setDriveMotors(0,0);
            }
        }
    }
}
