package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class Encoder_Test extends LinearOpMode {

    DcMotor M1 = null;
    DcMotor M2 = null;
    DcMotor M3 = null;
    DcMotor M4 = null;

    public void runOpMode() throws InterruptedException {

        //initialize motors
        M1 = hardwareMap.dcMotor.get("M1");
        M2 = hardwareMap.dcMotor.get("M2");
        M3 = hardwareMap.dcMotor.get("M3");
        M4 = hardwareMap.dcMotor.get("M4");

        M1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        M2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        M3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        M4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        RealCode_Drive.Rotate(M1, M2, M3, M4,0.5, 1120);

    }
}