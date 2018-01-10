package org.firstinspires.ftc.teamcode;

/**
 *  This current autonomous will knock off the jewel (30 points),
 *  Score a glyph into the corresponding cryptobox (45 points),
 *  Drive into the safe zone (10 points),
 *  For a total of 85 points
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class Auto_Blue extends LinearOpMode {

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;
    Servo left;
    Servo right;
    Servo arm;
    ColorSensor color;
    state state358;
    DcMotor retract;
    DcMotor release;

    double dPosition = 0.3; // down position
    double oPosition = 1;   // original position

    enum state {
        JEWEL, STOP, RED, BLUE, TURN, BOX
    }

    public void runOpMode() throws InterruptedException {

        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");
        lS = hardwareMap.dcMotor.get("linearSlide");
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");
        arm = hardwareMap.servo.get("arm");
        color = hardwareMap.colorSensor.get("color");
        retract = hardwareMap.dcMotor.get("retract");
        release = hardwareMap.dcMotor.get("release");
        state358 = state.JEWEL;

        fL.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        double POWER = .5;

        arm.setPosition(dPosition);
        left.setPosition(0);
        right.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state: ", state358);
            telemetry.update();
            release.setPower(-0.01);

            switch(state358) {

                case JEWEL:
                    lS.setPower(-0.5);
                    sleep(200);
                    lS.setPower(0);
                    if (color.blue()/2 > color.red()) { //blue: move backwards
                        fL.setPower(-POWER);
                        bL.setPower(-POWER);
                        fR.setPower(-POWER);
                        bR.setPower(-POWER);
                        sleep(200);
                        fL.setPower(0);
                        bL.setPower(0);
                        fR.setPower(0);
                        bR.setPower(0);
                        state358 = state.BLUE;
                        break;
                    }

                    if (color.blue() < color.red()/2) { //red: turn left and then reset direction
                        fL.setPower(-POWER);
                        bL.setPower(-POWER);
                        fR.setPower(POWER);
                        bR.setPower(POWER);
                        sleep(200);

                        fL.setPower(0);
                        bL.setPower(0);
                        fR.setPower(0);
                        bR.setPower(0);
                        arm.setPosition(oPosition);
                        sleep(2000);

                        fL.setPower(POWER);
                        bL.setPower(POWER);
                        fR.setPower(-POWER);
                        bR.setPower(-POWER);
                        sleep(200);

                        fL.setPower(0);
                        bL.setPower(0);
                        fR.setPower(0);
                        bR.setPower(0);
                        state358 = state.RED;
                        break;
                    }

                    break;

                case RED:
                    fL.setPower(-POWER);
                    bL.setPower(-POWER);
                    fR.setPower(-POWER);
                    bR.setPower(-POWER);
                    sleep(1000);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    state358 = state.TURN;
                    break;

                case BLUE:
                    arm.setPosition(oPosition);
                    sleep(2000);

                    fL.setPower(-POWER);
                    bL.setPower(-POWER);
                    fR.setPower(-POWER);
                    bR.setPower(-POWER);
                    sleep(900);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    state358 = state.TURN;
                    break;

                case TURN:
                    fL.setPower(POWER);
                    bL.setPower(POWER);
                    fR.setPower(-POWER);
                    bR.setPower(-POWER);
                    sleep(650);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    state358 = state.BOX;
                    break;

                case BOX:
                    fL.setPower(POWER);
                    bL.setPower(POWER);
                    fR.setPower(POWER);
                    bR.setPower(POWER);
                    sleep(200);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    left.setPosition(1);
                    right.setPosition(1);
                    sleep(1000);

                    fL.setPower(-POWER);
                    bL.setPower(-POWER);
                    fR.setPower(-POWER);
                    bR.setPower(-POWER);
                    sleep(200);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    state358 = state.STOP;
                    break;

                case STOP:
                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);
                    sleep(30000);

            }
        }
    }
}