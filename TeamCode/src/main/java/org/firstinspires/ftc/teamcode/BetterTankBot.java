package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Better Tank")
public class BetterTankBot extends OpMode {

    private DcMotor left;
    private DcMotor right;
    private boolean logic;
    private int speedAug;

    @Override
    public void init(){
        left = (DcMotor) hardwareMap.get("left");
        right = (DcMotor) hardwareMap.get("right");
        logic = true;
    }

    @Override
    public void loop(){
        driveCode("hello", logic);
        telemetry();
    }

    public void driveCode(String hi, Boolean logic){
        if (logic&& gamepad1.left_bumper){
            if(speedAug >= 1) { //I changed it, happy?
                speedAug -= 1;
            }
            this.logic= false;
        }
        else if(logic&& gamepad1.right_bumper) {
            speedAug += 1;
            this.logic= false;
        }
        else if(!gamepad1.left_bumper && !gamepad1.right_bumper){
            this.logic= true;
        }

        left.setPower(gamepad1.left_stick_y);
        right.setPower(gamepad1.right_stick_y);
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
