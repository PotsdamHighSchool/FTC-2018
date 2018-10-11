package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.lang.Math;

@TeleOp(name = "Tri-Omnibot")
public class OmniDrive {

    private DcMotor wheel1, wheel2, wheel3;
    private HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private Telemetry telemetry;

    @Override
    public void init(){
        wheel1 = (DcMotor) hardwareMap.get("m1");
        wheel2 = (DcMotor) hardwareMap.get("m2");
        wheel3 = (DcMotor) hardwareMap.get("m3");
    }
    
    @Override
    public void loop(){
        driveCode(gamepad1.left_stick_x, gamepad1.left_stick_y, (gamepad1.right_stick_x + gamepad1.right_trigger - gamepad1.left_trigger));
        telemetry();
    }           

    public void driveCode(double xRaw, double yRaw, double rRaw, int speedAug = 1, boolean Logic = true){
        if (this.Logic && gamepad1.left_bumper){
            if(this.speedAug >= 1) { //I changed it, happy?
                this.speedAug -= 1;
            }
            this.Logic = false;
        }
        else if(this.Logic && gamepad1.right_bumper) {
            this.speedAug += 1;
            this.Logic = false;
        }
        else if(!gamepad1.left_bumper && !gamepad1.right_bumper){
            this.Logic = true;
        }

        x = xRaw / this.speedAug;
        y = yRaw / this.speedAug;
        r = rRaw / this.speedAug;
       
        //Trig motor stuff
        wheel1.setPower(x*Math.cos(-Math.PI)-y*Math.sin(-Math.PI) + r);
        wheel2.setPower(x*Math.cos(Math.PI/3)-y*Math.sin(Math.PI/3) + r);
        wheel3.setPower(x*Math.cos(-Math.PI/3)-y*Math.sin(-Math.PI/3) + r);

    }

    public void telemetry(){
        //Motors
        telemetry.addData("Motor 1:", " %.2f", (wheel1.getPower());
        telemetry.addData("Motor 2:", " %.2f", (wheel2.getPower());
        telemetry.addData("Motor 3:", " %.2f\n", (wheel3.getPower());
        //Speed Modifier
        telemetry.addData("Mod:", " %d", (speedAug));
        //Sticks
        telemetry.addData("Left Stick:", "(%.2f, %.2f) ", (gamepad1.left_stick_x, gamepad1.right_stick_x));
        telemetry.addData("Right Stick:", "(%.2f, %.2f) ", (gamepad1.left_stick_x, gamepad1.left_stick_x));
    }
}
