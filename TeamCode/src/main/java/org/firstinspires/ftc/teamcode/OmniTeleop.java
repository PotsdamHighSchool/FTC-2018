package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "OmniBot")
public class OmniTeleop extends OpMode {
    private VRobot robot;


    @Override
    public void init() {

      robot = new VRobot(hardwareMap,gamepad1, gamepad2, telemetry);

    }

    @Override
    public void loop() {
        robot.getGripperRod().setPosition(1);
        robot.normalDrive();
        robot.servos();
        robot.totallyRealGUI();
        robot.jakesBadIdea();
            //testDrive();
    }



    /*private void totallyRealGUI() {
        if (rawFL>=0){
            if(rawFR>=0){
                if(rawBL>=0){
                    if(rawBR>0){
                        telemetry.addData("motors", "\n| (%.2f) | (%.2f)\n| (%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n| (%.2f) | (%.2f)\n| (%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }

                }
                else{
                    if(rawBR>=0){
                        telemetry.addData("motors", "\n| (%.2f) | (%.2f)\n|(%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n| (%.2f) | (%.2f)\n|(%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                }
            }
            else{
                if(rawBL>=0){
                    if(rawBR>=0){
                        telemetry.addData("motors", "\n| (%.2f) |(%.2f)\n| (%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n| (%.2f) |(%.2f)\n| (%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }

                }
                else{
                    if(rawBR>=0){
                        telemetry.addData("motors", "\n| (%.2f) |(%.2f)\n|(%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n| (%.2f) |(%.2f)\n|(%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                }
            }

        }
        else{
            if(rawFR>=0){
                if(rawBL>=0){
                    if(rawBR>0){
                        telemetry.addData("motors", "\n|(%.2f) | (%.2f)\n| (%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n|(%.2f) | (%.2f)\n| (%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }

                }
                else{
                    if(rawBR>=0){
                        telemetry.addData("motors", "\n|(%.2f) | (%.2f)\n|(%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n|(%.2f) | (%.2f)\n|(%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                }
            }
            else{
                if(rawBL>=0){
                    if(rawBR>=0){
                        telemetry.addData("motors", "\n|(%.2f) |(%.2f)\n| (%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n|(%.2f) |(%.2f)\n| (%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }

                }
                else{
                    if(rawBR>=0){
                        telemetry.addData("motors", "\n| (%.2f) |(%.2f)\n|(%.2f) | (%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                    else{
                        telemetry.addData("motors", "\n|(%.2f) |(%.2f)\n|(%.2f) |(%.2f)", rawFL,rawFR,rawBL,rawBR);
                    }
                }
            }

        }
    }
    */



}
