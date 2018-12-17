package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Better Tank, but there's 4 wheels")
public class BetterTankBot4 extends OpMode {

    //Motors
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    private DcMotor armSeg1;
    private DcMotor armSeg2;

    //Servos
    private Servo claw;

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
        frontLeft = (DcMotor) hardwareMap.get("FLeft");
        frontRight = (DcMotor) hardwareMap.get("FRight");
        backLeft = (DcMotor) hardwareMap.get("BLeft");
        backRight = (DcMotor) hardwareMap.get("BRight");

        armSeg1 = (DcMotor) hardwareMap.get("arm1");
        armSeg2 = (DcMotor) hardwareMap.get("arm2");

        armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = (Servo) hardwareMap.get("claw");
        driveLogic = true;
        driveModeLogic = true;
        armLogic = true;
        driveMode = true;
        speedAug = .5;
        armAug = .5;
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
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
        armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void driveCode() {
        telemetry.addData("Mode", driveMode); // might be the problem
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

        //Servo Code

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
    public void claw(){
        if(gamepad2.a){
            clawPos = 0.5f;
        }
        if(gamepad2.b){
            clawPos = 0;
        }
        claw.setPosition(clawPos);

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

        armSeg1.setPower(gamepad2.left_stick_y * armAug);
        armSeg2.setPower(-gamepad2.right_stick_y * armAug);
    }
    public void EmStop(){
        armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armSeg1.setPower(0);
        armSeg2.setPower(0);
        armSeg1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armSeg2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
