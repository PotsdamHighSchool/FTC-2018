package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxI2cColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorColor;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;

/**
 * Created by mbusch356 on 12/18/2018.
 */

public class BetterTankHardware {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    private HardwareMap hardwareMap;

    public DcMotor armSeg1;
    public DcMotor armSeg2;

    public Servo claw;

    public LynxI2cColorRangeSensor colorSensor;

    public BetterTankHardware(HardwareMap _hardwareMap){
        hardwareMap = _hardwareMap;
        frontLeft = (DcMotor)hardwareMap.get("FLeft");
        frontRight = (DcMotor)hardwareMap.get("FRight");
        backLeft = (DcMotor)hardwareMap.get("BLeft");
        backRight = (DcMotor)hardwareMap.get("BRight");

        armSeg1 = (DcMotor)hardwareMap.get("arm1");
        armSeg2 = (DcMotor)hardwareMap.get("arm2");

        claw = (Servo) hardwareMap.get("claw");


        armSeg2.setDirection(DcMotorSimple.Direction.REVERSE);

        armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = (LynxI2cColorRangeSensor)hardwareMap.get("colorSensor");
    }

    public void setDriveMotors(double left, double right){
        frontRight.setPower(right);
        backRight.setPower(right);
        frontLeft.setPower(left);
        backLeft.setPower(left);
    }


}
