package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@TeleOp(name = "Better Tank, but there's 4 wheels")
public class BetterTankBot4 extends OpMode {

    private  BetterTankHardware hardware;

    private boolean driveLogic;
    private boolean driveModeLogic;
    private boolean armLogic;
    private boolean driveMode;
    private double speedAug;
    private double armAug;
    private final double speedStep = 0.1;
    private MusicPlayer music;
    float clawPos = 0;

    @Override
    public void init(){

        hardware = new BetterTankHardware(hardwareMap);

        driveLogic = true;
        driveModeLogic = true;
        armLogic = true;
        driveMode = true;
        speedAug = .5;
        armAug = .75;
        music = new MusicPlayer(hardwareMap, gamepad2, telemetry);

    }

    @Override
    public void loop(){
        driveCode();
        claw();
        telemetry();
        music.run(); //ENABLE THIS FOR MUSIC - Julian      REALLY? - Morgan
       /* if (gamepad1.b){
            EmStop();
        }*/

        //Stupid bug fix that may not work (or may, i dunno) - Julian
        hardware.armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardware.armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void driveCode() {
        telemetry.addData("Mode", driveMode); // might be the problem
        if (driveLogic && gamepad1.left_bumper) {
            if (speedAug - speedStep >= 0) {
                speedAug -= speedStep;
            }
            driveLogic = false;
        } else if (driveLogic && gamepad1.right_bumper) {
            if (speedAug + speedStep <= 1) {
                speedAug += speedStep;
            }
            driveLogic = false;
        } else if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
            driveLogic = true;
        }

        telemetry.addData("Is the left stick down: ", gamepad1.left_stick_button);
        if (driveModeLogic && gamepad1.left_stick_button && !driveMode) {
            driveMode = true;
            driveModeLogic = false;
        } else if (driveModeLogic && gamepad1.left_stick_button && driveMode) {
            driveMode = false;
            driveModeLogic = false;
        } else if (!gamepad1.left_stick_button) {
            driveModeLogic = true;
        }


        if (driveMode) { //true = tank, false = joy
            hardware.setDriveMotors(gamepad1.left_stick_y * speedAug, gamepad1.right_stick_y * speedAug);
        } else {
            hardware.setDriveMotors((gamepad1.left_stick_y - gamepad1.left_stick_x) * speedAug, (gamepad1.left_stick_y + gamepad1.left_stick_x) * speedAug);
        }

        //Servo Code

    }

    public void telemetry(){
        //Sensors
        telemetry.addData("Distance:", String.format(Locale.US, "%.02f",hardware.colorSensor.getDistance(DistanceUnit.CM)));
        telemetry.addData("Alpha: ", (hardware.colorSensor.alpha()));
        telemetry.addData("Red: ", (hardware.colorSensor.red()));
        telemetry.addData("Green: ", (hardware.colorSensor.green()));
        telemetry.addData("Blue: ", (hardware.colorSensor.blue()));


        //Motors
        telemetry.addData("front left:", " %.2f", (hardware.frontLeft.getPower()));
        telemetry.addData("front right:", " %.2f", (hardware.frontRight.getPower()));
        telemetry.addData("back left:", " %.2f", (hardware.backLeft.getPower()));
        telemetry.addData("back right.:", " %.2f", (hardware.backRight.getPower()));

        //Speed Modifier
        telemetry.addData("Mod:", " %.2f\n", (speedAug));

        //Sticks
        telemetry.addData("Left Stick:", "(%.2f, %.2f) ", gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("Right Stick:", "(%.2f, %.2f) ", gamepad1.left_stick_x, gamepad1.left_stick_x);
    }
    public void claw(){
        if(gamepad2.a){
            clawPos = 0.5f;
        }
        if(gamepad2.b){
            clawPos = 0;
        }
        hardware.claw.setPosition(clawPos);

        if (armLogic && gamepad2.left_bumper) {
            if (armAug-speedStep >= 0) {
                armAug -= speedStep;
            }
            armLogic = false;
        } else if (armLogic && gamepad2.right_bumper) {
            if (armAug+speedStep <= 1) {
                armAug += speedStep;
            }
            armLogic = false;
        } else if (!gamepad2.left_bumper && !gamepad2.right_bumper) {
            armLogic = true;
        }

        hardware.armSeg1.setPower(gamepad2.left_stick_y * armAug);
        hardware.armSeg2.setPower(gamepad2.right_stick_y * armAug);
    }
    public void EmStop(){
        hardware.armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.armSeg1.setPower(0);
        hardware.armSeg2.setPower(0);
        hardware.armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardware.armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
