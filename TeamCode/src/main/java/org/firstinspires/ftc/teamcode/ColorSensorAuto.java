package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by bpatterson on 11/27/2017.
 */

@Autonomous(name="ColorSensor")
public class ColorSensorAuto extends LinearOpMode {

    private VRobot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new VRobot(hardwareMap, gamepad1, gamepad2, telemetry);

        int red = robot.getColorSensor().red();
        int blue = robot.getColorSensor().blue();

        if (red > blue && red > 80 && blue > 80){
            robot.driveCode(300, 0, 0);
        } else if (blue > red && red > 80 && blue > 80){
            robot.driveCode(-300, 0, 0);
        } else robot.driveCode(0, 0, 0);

    }
}
