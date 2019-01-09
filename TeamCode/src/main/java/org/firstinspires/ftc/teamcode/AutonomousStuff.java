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
        //synchronized (this) {
        hardware.armSeg2.setPower(.75);
        sleep(1000);
        hardware.armSeg1.setPower(0);
        sleep(500);
        hardware.setDriveMotors(.5,-.5);
        sleep(1000);
        hardware.setDriveMotors(50,50);
        sleep(1000);
        hardware.setDriveMotors(-.5,.5);
        sleep(1000);
        hardware.setDriveMotors(0,0);
        //}
    }
}
