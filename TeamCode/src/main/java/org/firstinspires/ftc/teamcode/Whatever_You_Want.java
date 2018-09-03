package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by kevinwang on 8/24/18.
 */

@Autonomous

public class Whatever_You_Want extends LinearOpMode{

    //Declare motors
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    public void runOpMode() throws InterruptedException{

        FL = hardwareMap.dcMotor.get("FL");
        FR = hardwareMap.dcMotor.get("FR");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        //time not yet set
        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 1, 0.8);
        sleep(1200);

        FL.setPower(0);
        BL.setPower(0);
        FR.setPower(-0.2);
        BR.setPower(-0.2);
        sleep(400);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 0, 0);
        sleep(500);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 2, 0.8);
        sleep(2200);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 1, 0.8);
        sleep(500);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 0, 0.8);
        sleep(4000);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 1, 0.8);
        sleep(500);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 2, -0.8);
        sleep(2400);

        Whatever_Drive_Code.Drive(FL, BL, FR, BR, 0, 0.8);
    }
}
