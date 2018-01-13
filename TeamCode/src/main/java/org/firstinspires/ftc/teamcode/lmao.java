package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lawrencemao on 1/12/18.
 */

@TeleOp

public class lmao extends OpMode{

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;

    Servo arm;          // controls the servo which knocks the jewel
    Servo claw;         // grabs the RELIC
    Servo left;         // controls left servo on the glyph mech
    Servo right;        // controls right servo on the glyph mech
    DcMotor rotator;    // controls the rotational arm
    DcMotor release;    // extends the arm
    DcMotor retract;    // retracts the arm

    double FLAPPER_OPEN = 0.1;
    double FLAPPER_CLOSED = 0.9;

    public void init(){

        fL = hardwareMap.dcMotor.get("fL");          //EH2 - 1
        bL = hardwareMap.dcMotor.get("bL");          //EH2 - 2
        fR = hardwareMap.dcMotor.get("fR");          //EH2 - 0
        bR = hardwareMap.dcMotor.get("bR");          //EH2 - 3
        lS = hardwareMap.dcMotor.get("lS");          //EH5 - 0

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        arm = hardwareMap.servo.get("arm");
        claw = hardwareMap.servo.get("claw");
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");
        rotator = hardwareMap.dcMotor.get("rotator");
        release = hardwareMap.dcMotor.get("release");
        retract = hardwareMap.dcMotor.get("retract");

    }

    public void loop() {

        // Holds arm in place
        arm.setPosition(0);

        // Drive Code
        TeleOp_Drive_Code.TeleOpDrive(gamepad1, fL, bL, fR, bR);

        // Linear Slide Controller
        lS.setPower(0);

        if (gamepad2.dpad_up){
            lS.setPower(-0.7);
        }

        if (gamepad2.dpad_down){
            lS.setPower(0.7);
        }

        // Glyph Mechanism Controller
        if (gamepad2.a){
            left.setPosition(1);
            right.setPosition(0);
        }

        else{
            left.setPosition(0);
            right.setPosition(1);
        }

        // Relic Grabbing Mechanism
        if(gamepad1.a){ // grabber
            claw.setPosition(FLAPPER_OPEN);
        }
        else claw.setPosition(FLAPPER_CLOSED);

        if(gamepad1.left_bumper) rotator.setPower(0.5); // grabber rotation
        else if (gamepad1.right_bumper) rotator.setPower(-0.5);
        else rotator.setPower(0);

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