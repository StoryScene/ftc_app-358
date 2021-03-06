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

public class Auto_Red extends LinearOpMode {

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
    ColorSensor color;  // sees colour - for autonomous
    state state358;

    double dPosition = 0; // down position
    double oPosition = 1;   // original position

    enum state {
        JEWEL, STOP, RED, BLUE, NULL, TURN, BOX
    }

    public void runOpMode() throws InterruptedException {

        fL = hardwareMap.dcMotor.get("fL");          //EH2 - 1
        bL = hardwareMap.dcMotor.get("bL");          //EH2 - 2
        fR = hardwareMap.dcMotor.get("fR");          //EH2 - 0
        bR = hardwareMap.dcMotor.get("bR");          //EH2 - 3
        lS = hardwareMap.dcMotor.get("lS");          //EH3 - 3

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        arm = hardwareMap.servo.get("arm");             //EH2 - 0
        claw = hardwareMap.servo.get("claw");           //EH3 - 0
        left = hardwareMap.servo.get("left");           //EH2 - 1
        right = hardwareMap.servo.get("right");         //EH2 - 2
        rotator = hardwareMap.dcMotor.get("rotator");   //EH3 - 1
        release = hardwareMap.dcMotor.get("release");   //EH3 - 2
        retract = hardwareMap.dcMotor.get("retract");   //EH3 - 0
        color = hardwareMap.colorSensor.get("color");   //EH2 - 0

        state358 = state.JEWEL;

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);

        double POWER = .5;

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Going into state: ", state358);
            telemetry.update();
            release.setPower(-0.01);

            switch(state358) {

                case JEWEL:
                    arm.setPosition(dPosition);
                    left.setPosition(0);
                    right.setPosition(0);

                    sleep(1000);

                    lS.setPower(0.5);
                    sleep(500);
                    lS.setPower(0);
                    if (color.blue()/1.5 > color.red()) { //blue: move forward
                        fL.setPower(POWER);
                        bL.setPower(POWER);
                        fR.setPower(POWER);
                        bR.setPower(POWER);
                        sleep(300);
                        fL.setPower(0);
                        bL.setPower(0);
                        fR.setPower(0);
                        bR.setPower(0);
                        state358 = state.BLUE;
                        break;
                    }

                    else if (color.blue() < color.red()/1.5) { //red: turn right and then reset direction
                        fL.setPower(POWER);
                        bL.setPower(POWER);
                        fR.setPower(-POWER);
                        bR.setPower(-POWER);
                        sleep(200);

                        fL.setPower(0);
                        bL.setPower(0);
                        fR.setPower(0);
                        bR.setPower(0);
                        arm.setPosition(oPosition);
                        sleep(2000);

                        fL.setPower(-POWER);
                        bL.setPower(-POWER);
                        fR.setPower(POWER);
                        bR.setPower(POWER);
                        sleep(200);

                        fL.setPower(0);
                        bL.setPower(0);
                        fR.setPower(0);
                        bR.setPower(0);
                        state358 = state.RED;
                        break;
                    }

                    else {
                        arm.setPosition(oPosition);
                        sleep(2000);
                        state358 = state.NULL;
                    }

                    break;

                case RED:
                    fL.setPower(POWER);
                    bL.setPower(POWER);
                    fR.setPower(POWER);
                    bR.setPower(POWER);
                    sleep(1100);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    state358 = state.TURN;
                    break;

                case BLUE:
                    arm.setPosition(oPosition);
                    sleep(2000);

                    fL.setPower(POWER);
                    bL.setPower(POWER);
                    fR.setPower(POWER);
                    bR.setPower(POWER);
                    sleep(800);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    state358 = state.TURN;
                    break;

                case NULL:
                    fL.setPower(POWER);
                    bL.setPower(POWER);
                    fR.setPower(POWER);
                    bR.setPower(POWER);
                    sleep(1000);
                    state358 = state.TURN;
                    break;

                case TURN:
                    fL.setPower(POWER);
                    bL.setPower(POWER);
                    fR.setPower(-POWER);
                    bR.setPower(-POWER);
                    sleep(1000);

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
                    sleep(1000);

                    fL.setPower(0);
                    bL.setPower(0);
                    fR.setPower(0);
                    bR.setPower(0);

                    left.setPosition(0.7);
                    right.setPosition(0.7);
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