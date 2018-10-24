package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
        left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop(){
        driveCode();
        telemetry();
    }

    public void driveCode() {
        telemetry.addData("Mode", "%s", driveMode);
        if (driveLogic && gamepad1.left_bumper) {
            if (speedAug >= 1) { //I changed it, happy?
                speedAug -= 1;
            }
            driveLogic = false;
        } else if (driveLogic && gamepad1.right_bumper) {
            speedAug += 1;
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
            left.setPower(gamepad1.left_stick_y);
            right.setPower(gamepad1.right_stick_y);
        }
        else{
            left.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
            right.setPower(gamepad1.left_stick_y +
                    -gamepad1.left_stick_x);

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
