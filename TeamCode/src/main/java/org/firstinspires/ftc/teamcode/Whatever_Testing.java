package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by kevinwang on 8/30/18.
 */

@TeleOp

public class Whatever_Testing extends OpMode{

    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    public void init(){

        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void loop() {

        if (gamepad1.dpad_up) {

            FL.setPower(1);
            FR.setPower(1);
            BL.setPower(1);
            BR.setPower(1);

        }

        else if (gamepad1.dpad_down) {

            FL.setPower(-1);
            FR.setPower(-1);
            BL.setPower(-1);
            BR.setPower(-1);

        }

        else if (gamepad1.dpad_left) {

            FL.setPower(-1);
            FR.setPower(1);
            BL.setPower(1);
            BR.setPower(-1);

        }

        else if (gamepad1.dpad_right) {

            FL.setPower(1);
            FR.setPower(-1);
            BL.setPower(-1);
            BR.setPower(1);

        }

        else {

            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);

        }

    }

}
