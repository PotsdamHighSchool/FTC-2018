package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Better Tank")
public class BetterTankBot extends OpMode {

    private DcMotor left;
    private DcMotor right;
    private boolean driveLogic;
    private boolean driveModeLogic;
    private boolean driveMode;
    private int speedAug;

    @Override
    public void init(){
        left = (DcMotor) hardwareMap.get("left");
        right = (DcMotor) hardwareMap.get("right");
        driveLogic = true;
        driveModeLogic = true;
        driveMode = true;
    }

    @Override
    public void loop(){
        driveCode("hello", driveLogic, driveModeLogic, driveMode);
        telemetry();
    }

    public void driveCode(String hi, Boolean driveLogic, Boolean driveModeLogic, Boolean driveMode) {
        if (driveLogic && gamepad1.left_bumper) {
            if (speedAug >= 1) { //I changed it, happy?
                speedAug -= 1;
            }
            this.driveLogic = false;
        } else if (driveLogic && gamepad1.right_bumper) {
            speedAug += 1;
            this.driveLogic = false;
        } else if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
            this.driveLogic = true;
        }

        if (this.driveModeLogic && gamepad1.left_stick_button && !driveMode){
            driveMode = true;
            this.driveModeLogic = false;
        }
        else if(this.driveModeLogic && gamepad1.left_stick_button && driveMode){
            driveMode = false;
            this.driveModeLogic = false;
        }
        else{
            this.driveModeLogic = true;
        }


        if (driveMode) { //true = tank, false = joy
            left.setPower(gamepad1.left_stick_y);
            right.setPower(gamepad1.right_stick_y);
        }
        else{
            left.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            right.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);

        }
    }

    public void telemetry(){
        //Motors
        telemetry.addData("left side:", " %.2f", (left.getPower()));
        telemetry.addData("right side:", " %.2f", (right.getPower()));
        //Speed Modifier
        //telemetry.addData("Mod:", " %d\n", (speedAug));
        //Sticks
        telemetry.addData("Left Stick:", "(%.2f, %.2f) ", gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("Right Stick:", "(%.2f, %.2f) ", gamepad1.left_stick_x, gamepad1.left_stick_x);
    }
}
