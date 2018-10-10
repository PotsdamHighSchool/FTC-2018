package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by bpatterson on 11/13/2017.
 */

public class VRobot {

    private ColorSensor colorSensor;

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private Servo gripperLeft, gripperRight, gripperTest, motorPower, Test2S;
    public Servo gripperRod;

    private double forwardVector, strafeVector, rotate;
    private double gamepadLeftB, gamepadRightB;
    private double rawFL, rawFR, rawBL, rawBR;
    private HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private Telemetry telemetry;

    private boolean x;
    private double testgrip;
    public VRobot(HardwareMap hMap, Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        hardwareMap = hMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.telemetry = telemetry;
        frontLeft = (DcMotor) hardwareMap.get("mapFrontLeft");
        frontRight = (DcMotor) hardwareMap.get("mapFrontRight");
        backLeft = (DcMotor) hardwareMap.get("mapBackLeft");
        backRight = (DcMotor) hardwareMap.get("mapBackRight");

        x = false;
        testgrip = .5;
        motorPower = hardwareMap.servo.get("motorPower");
        gripperLeft = hardwareMap.servo.get("mapGripperLeft");
        gripperRight = hardwareMap.servo.get("mapGripperRight");
        gripperRod = hardwareMap.servo.get("MattsRod");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        Test2S = hardwareMap.servo.get("testS");
        gripperTest = hardwareMap.servo.get("GripperTest");
    }
    public void normalDrive() {
        forwardVector = -gamepad1.left_stick_y/3;
        strafeVector = gamepad1.left_stick_x/3;
        rotate = -(gamepadLeftB) + (gamepadRightB) +
                (gamepad1.right_trigger - 33) -
                (gamepad1.left_trigger - 33) +
                (gamepad1.right_stick_x/3);

        driveCode(forwardVector, strafeVector, rotate);

    }

    public void driveCode(double forward, double strafe, double rotate){
        rawFL = forward + strafe + rotate;
        rawFR = forward - strafe - rotate;
        rawBL = forward - strafe + rotate;
        rawBR = forward + strafe - rotate;


        frontLeft.setPower(Range.clip(-1, rawFL, 1));
        frontRight.setPower(Range.clip(-1, rawFR, 1));
        backLeft.setPower(Range.clip(-1, rawBL, 1));
        backRight.setPower(Range.clip(-1, rawBR, 1));
    }


    public void jakesBadIdea() {  //wtf does this do morgan
        if(gamepad1.left_bumper) {
            motorPower.setPosition(0);
        } else if(gamepad1.right_bumper){
            motorPower.setPosition(1);
        } else{
            motorPower.setPosition(.5);
        }
    }

    public void servos() {
        //Gripper Servos
        if(gamepad1.dpad_up){
            Test2S.setPosition(1);
        }
        if(gamepad1.dpad_down){
            Test2S.setPosition(0);
        }
        toggleGripper(gamepad1.a);

        if (gamepad1.x) {
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
        }
        if (gamepad1.b) {
            gripperLeft.setPosition(.5);
            gripperRight.setPosition(.5);
        }
        if (gamepad1.right_bumper) {
            testgrip += .1;
            testgrip = Range.clip(testgrip, 0, 1);
        }
        if (gamepad1.left_bumper) {
            testgrip -= .1;
            testgrip = Range.clip(testgrip, 0, 1);
        }

        //controller2
        toggleGripper(gamepad2.a);

        if (gamepad2.x) {
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
        }
        if (gamepad2.b) {
            gripperLeft.setPosition(.5);
            gripperRight.setPosition(.5);
        }
        if (gamepad2.right_bumper) {
            testgrip += .1;
            testgrip = Range.clip(testgrip, 0, 1);
        }
        if (gamepad2.left_bumper) {
            testgrip -= .1;
            testgrip = Range.clip(testgrip, 0, 1);
        }
    }

    private void toggleGripper(boolean Pressed) {
        boolean x = true;
        double GL = gripperLeft.getPosition();
        double GR = gripperRight.getPosition();

        if (Pressed && x) {

            if (GL == .5) {
                gripperLeft.setPosition(1);
                gripperRight.setPosition(0);
            } else if (GL == 1) {
                gripperLeft.setPosition(.5);
                gripperRight.setPosition(.5);
            }
        }
        if (!Pressed) {
            x = true;
        }
    }
    public void totallyRealGUI(){

        telemetry.addData("motors", "\n|%s |%s\n|%s |%s", valFormat(rawFL),valFormat(rawFR),valFormat(rawBL),valFormat(rawBR));

    }
    private String valFormat(double x){
        if(x<=0){
            return(String.format("%.2f",x));
        }
        else{
            return(String.format(" %f.2",x));
        }

    }

    public ColorSensor getColorSensor() {
        return colorSensor;
    }

    public Servo getGripperRod() {
        return gripperRod;
    }
}
