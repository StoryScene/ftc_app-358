package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by lawrencemao on 12/5/17.
 * Program for testing a new glyph mechanism
 */

@TeleOp

public class lmao extends OpMode{

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor glyph;
    DcMotor lS;

    public void init(){

        fL = hardwareMap.dcMotor.get("fL");          //EH2 - 1
        bL = hardwareMap.dcMotor.get("bL");          //EH2 - 2
        fR = hardwareMap.dcMotor.get("fR");          //EH2 - 0
        bR = hardwareMap.dcMotor.get("bR");          //EH2 - 3
        lS = hardwareMap.dcMotor.get("lS");          //EH5 - 0
        glyph = hardwareMap.dcMotor.get("glyph");    //EH5 - 1

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

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

    }
}