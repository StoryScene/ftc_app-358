package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lawrencemao on 1/10/2018.
 */

//ClawGrabberThing

@TeleOp(name = "CGT", group = "Tests")

public class CGT extends LinearOpMode {

    private Servo theClaw; // grabs the RELIC
    private DcMotor arm; // rotates the grabber
    private DcMotor release; // extends the arm
    private DcMotor retract; // retracts the arm

    private static final double FLAPPER_OPEN = 0.1;
    private static final double FLAPPER_CLOSED = 0.9;

    @Override

    public void runOpMode() throws InterruptedException {

        theClaw = hardwareMap.servo.get("claw");
        arm = hardwareMap.dcMotor.get("arm");
        release = hardwareMap.dcMotor.get("release");
        retract = hardwareMap.dcMotor.get("retract");

        // set zero power behavior on motors? hold in place?

        theClaw.setPosition(FLAPPER_CLOSED);

        waitForStart();

        while(opModeIsActive()){

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

            idle();
        }
    }

}