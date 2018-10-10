package org.firstinspires.ftc.teamcode;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.code.Type;

@TeleOp(name = "TankBot")
public class TankTeleop extends OpMode {

    private DcMotor leftWheels, rightWheels, arm;
    private Servo gripperRod, gripperLeft, gripperRight;
    private boolean gripperLogic;
    private double stickModLeft;
    private double stickModRight;
    private MediaPlayer player;
    private boolean selectLogic;
    private int musicSelect;
    private String[] tracklist;
    @Override
    public void init() {

      leftWheels = hardwareMap.dcMotor.get("mapLeft");
      rightWheels = hardwareMap.dcMotor.get("mapRight");
        rightWheels.setDirection(DcMotorSimple.Direction.REVERSE);

      arm = hardwareMap.dcMotor.get("Arm");

      gripperRod = hardwareMap.servo.get("MattsRod");
      gripperLeft = hardwareMap.servo.get("mapGripperLeft");
      gripperRight = hardwareMap.servo.get("mapGripperRight");

      gripperLogic = true;

      selectLogic = true;
      musicSelect = 1;
      String[] tracklist = {"Nyan Cat","Smash Mouth"};
    }

    @Override
    public void loop() {
        gripperRod.setPosition(1);
        if(stickModLeft == 0 || stickModRight == 0) {
            leftWheels.setPower(gamepad1.left_stick_y / 3);
            rightWheels.setPower(gamepad1.right_stick_y / 3);
        }
        else {
            leftWheels.setPower(stickModLeft);
            rightWheels.setPower(stickModRight);
        }

        if (gamepad2.x) {
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
        }
        if (gamepad2.b) {
            gripperLeft.setPosition(.5);
            gripperRight.setPosition(.5);
        }

        if (gamepad1.dpad_up){
            stickModLeft = -.3;
            stickModRight = -.3;
        }
        else if (gamepad1.dpad_down) {
            stickModLeft = .3;
            stickModRight = .3;
        }
        else if (gamepad1.dpad_left) {
            stickModLeft = .3;
            stickModRight = -.3;
        }
        else if (gamepad1.dpad_right) {
            stickModLeft = -.3;
            stickModRight = .3;
        }
        else {
            stickModRight = 0;
            stickModLeft = 0;
        }

        if (gamepad2.y && gripperLogic && gripperLeft.getPosition() == .5){
            gripperLeft.setPosition(1);
            gripperRight.setPosition(0);
            gripperLogic = false;
        }
        else if (gamepad2.y && gripperLogic && gripperLeft.getPosition() == 1){
            gripperLeft.setPosition(.5);
            gripperRight.setPosition(.5);
            gripperLogic = false;
        }
        if (!gamepad2.y){
            gripperLogic = true;
        }
        arm.setPower(gamepad2.left_stick_y);
        if(gamepad1.a){

            player.start();
        }
        if(gamepad1.b){
            player.stop();
        }
        telemetry.addData("Currently Playing", player.getTrackInfo());
        telemetry.addData("Selected", tracklist[musicSelect]);


        if(gamepad1.right_bumper && selectLogic){
            musicSelect += 1;
        }
        if (gamepad1.left_bumper && selectLogic){
            musicSelect -= 0;
        }
        if(!gamepad1.right_bumper && !gamepad1.left_bumper){
            selectLogic = true;
        }
        //player.selectTrack(R.raw.cat);
        //telemetry.addData("Current Track", player.getTrackInfo());
    }
}
