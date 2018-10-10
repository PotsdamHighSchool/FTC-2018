package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by bpatterson on 11/13/2017.
 */

public class MotorFunctions {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Servo gripperLeft, gripperRight;

    private double forwardVector, strafeVector, rotate;
    private double gamepadLeftB, gamepadRightB;
    private double rawFL, rawFR, rawBL, rawBR;

    private boolean gripper;
    private boolean x=true;
}

/*
ForwardVector = -gamepad1.left_stick_y/3;
         strafeVector = gamepad1.left_stick_x/3;
         rotate = -(gamepadLeftB) + (gamepadRightB) +
                 (gamepad1.right_trigger - 33) -
                 (gamepad1.left_trigger - 33) +
                 (gamepad1.right_stick_x/3);
         rawFL = forwardVector + strafeVector + rotate;
         rawFR = forwardVector - strafeVector - rotate;
         rawBL = forwardVector - strafeVector + rotate;
         rawBR = forwardVector + strafeVector - rotate;
 */