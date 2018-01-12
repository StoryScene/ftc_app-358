package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by kevinwang on 12/5/17.
 */

@TeleOp

public class LolxD extends OpMode{

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor glyph;
    DcMotor lS;

    //CGT
    Servo theClaw; // grabs the RELIC
    DcMotor arm; // rotates the grabber
    DcMotor release; // extends the arm
    DcMotor retract; // retracts the arm

    double FLAPPER_OPEN = 0.1;
    double FLAPPER_CLOSED = 0.9;

    public void init(){

        fL = hardwareMap.dcMotor.get("fL");          //EH2 - 1
        bL = hardwareMap.dcMotor.get("bL");          //EH2 - 2
        fR = hardwareMap.dcMotor.get("fR");          //EH2 - 0
        bR = hardwareMap.dcMotor.get("bR");          //EH2 - 3
        lS = hardwareMap.dcMotor.get("lS");          //EH5 - 0
        glyph = hardwareMap.dcMotor.get("glyph");    //EH5 - 1

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        //CGT
        theClaw = hardwareMap.servo.get("claw");
        arm = hardwareMap.dcMotor.get("arm");
        release = hardwareMap.dcMotor.get("release");
        retract = hardwareMap.dcMotor.get("retract");

        theClaw.setPosition(FLAPPER_CLOSED);

    }

    public void loop() {

        //Drive Code
        TeleOp_Drive_Code.TeleOpDrive(gamepad1, fL, bL, fR, bR);

        //Linear Slide Controller
        lS.setPower(0);

        if (gamepad2.dpad_up){
            lS.setPower(-0.7);
        }

        if (gamepad2.dpad_down){
            lS.setPower(0.7);
        }

        //Glyph Mechanism Controller
        glyph.setPower(gamepad2.right_stick_y);

        //CGT
        if(gamepad1.a){ // grabber
            theClaw.setPosition(FLAPPER_OPEN);
        }
        else theClaw.setPosition(FLAPPER_CLOSED);

        if(gamepad1.left_bumper) arm.setPower(0.5); // grabber rotation
        else if (gamepad1.right_bumper) arm.setPower(-0.5);
        else arm.setPower(0);

           /* if(gamepad1.b){
                rightFlapper.setPosition(FLAPPER_CLOSED);
                leftFlapper.setPosition(1-FLAPPER_CLOSED);
            } */

        if(gamepad1.dpad_up){ // extend and retract arm
            release.setPower(-0.6);
            retract.setPower(-0.02);
        }
        else if(gamepad1.dpad_down){
            retract.setPower(0.4);
            release.setPower(0.3);
        }
        else {
            retract.setPower(0);
            release.setPower(0);
        }

    }

}