package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by bpatterson on 10/23/2017.
 */
@TeleOp(name = "Motor Test")
public class MotorTest extends OpMode {


        DcMotor frontLeft, frontRight, backLeft, backRight;




        @Override
        public void init() {

            frontLeft = (DcMotor) hardwareMap.get("FLeft");
            frontRight = (DcMotor) hardwareMap.get("FRight");
            backLeft = (DcMotor) hardwareMap.get("BLeft");
            backRight = (DcMotor) hardwareMap.get("BRight");
        }

        @Override
        public void loop() {

            if(gamepad1.a){
                frontLeft.setPower(.5);
            }
            else{
                frontLeft.setPower(0);
            }
            if(gamepad1.b){
                frontRight.setPower(.5);
            }
            else{
                frontRight.setPower(0);
            }
            if(gamepad1.x){
                backLeft.setPower(.5);
            }
            else{
                backLeft.setPower(0);
            }
            if(gamepad1.y){
                backRight.setPower(.5);
            }
            else{
                backRight.setPower(0);
            }


        }
    }
