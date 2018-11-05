package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Better Tank, but there's 4 wheels")
public class BetterTankBot4 extends OpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;


    private boolean driveLogic;
    private boolean driveModeLogic;
    private boolean driveMode;
    private double speedAug;
    private final double speedStep = 0.1;
    private MusicPlayer music;

    @Override
    public void init(){
        frontLeft = (DcMotor) hardwareMap.get("FLeft");
        frontRight = (DcMotor) hardwareMap.get("FRight");
        backLeft = (DcMotor) hardwareMap.get("BLeft");
        backRight = (DcMotor) hardwareMap.get("BRight");
        driveLogic = true;
        driveModeLogic = true;
        driveMode = true;
        speedAug = .5;
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        music = new MusicPlayer(hardwareMap, gamepad2, telemetry);
    }

    @Override
    public void loop(){
        driveCode();
        telemetry();
        music.run();
    }

    public void driveCode() {
        telemetry.addData("Mode", "%s", driveMode);
        if (driveLogic && gamepad1.left_bumper) {
            if (speedAug-speedStep >= 0) {
                speedAug -= speedStep;
            }
            driveLogic = false;
        } else if (driveLogic && gamepad1.right_bumper) {
            if (speedAug+speedStep <= 1) {
                speedAug += speedStep;
            }
            driveLogic = false;
        } else if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
            driveLogic = true;
        }

        telemetry.addData("Is the left stick down: ", gamepad1.left_stick_button);
        if (driveModeLogic &&  gamepad1.left_stick_button && !driveMode){
            driveMode = true;
            driveModeLogic = false;
        }
        else if(driveModeLogic && gamepad1.left_stick_button && driveMode){
            driveMode = false;
            driveModeLogic = false;
        }
        else if(!gamepad1.left_stick_button){
            driveModeLogic = true;
        }


        if (driveMode) { //true = tank, false = joy
            frontLeft.setPower(gamepad1.left_stick_y * speedAug);
            frontRight.setPower(gamepad1.right_stick_y * speedAug);
            backLeft.setPower(gamepad1.left_stick_y * speedAug);
            backRight.setPower(gamepad1.right_stick_y * speedAug);
        }
        else{
            frontLeft.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x)*speedAug);
            frontRight.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x)*speedAug);
            backLeft.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x)*speedAug);
            backRight.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x)*speedAug);

        }
    }

    public void telemetry(){
        //Motors
        telemetry.addData("front left:", " %.2f", (frontLeft.getPower()));
        telemetry.addData("front right:", " %.2f", (frontRight.getPower()));
        telemetry.addData("back left:", " %.2f", (backLeft.getPower()));
        telemetry.addData("back right.:", " %.2f", (backRight.getPower()));

        //Speed Modifier
        telemetry.addData("Mod:", " %.2f\n", (speedAug));

        //Sticks
        telemetry.addData("Left Stick:", "(%.2f, %.2f) ", gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("Right Stick:", "(%.2f, %.2f) ", gamepad1.left_stick_x, gamepad1.left_stick_x);
    }
}
