package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by kevinwang on 8/28/18.
 */

@TeleOp

public class Whatever_Test_Drive extends OpMode{

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

    }

    public void loop(){
        TeleOp_Drive_Code.TeleOpDrive(gamepad1, FL, BL, FR, BR);
    }
}
