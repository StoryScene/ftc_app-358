package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        //time not yet set
        Whatever_Drive_Code.Drive(FL, BL, FR, FL, 1, 1);
        sleep(1000);

        Whatever_Drive_Code.Drive(FL, BL, FR, FL, 2, -1);
        sleep(1000);

        Whatever_Drive_Code.Drive(FL, BL, FR, FL, 1, 1);
        sleep(1000);

        Whatever_Drive_Code.Drive(FL, BL, FR, FL, 0, 1);
        sleep(1000);

        Whatever_Drive_Code.Drive(FL, BL, FR, FL, 1, 1);
        sleep(1000);

        Whatever_Drive_Code.Drive(FL, BL, FR, FL, 2, 1);
        sleep(1000);

    }
}
