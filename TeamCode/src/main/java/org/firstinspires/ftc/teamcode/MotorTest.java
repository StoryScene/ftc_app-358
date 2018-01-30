package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by kevinwang on 1/30/18.
 */

public class MotorTest extends LinearOpMode{

    DcMotor Motor;

    double Power;
    long Time;

    public void runOpMode() throws InterruptedException{

        Motor = hardwareMap.dcMotor.get("Motor");
        Power = 0.8;
        Time = 2000;

        while(opModeIsActive()){

            Motor.setPower(Power);
            sleep(Time);

            Motor.setPower(-1*Power);
            sleep(Time);

            Motor.setPower(0);
            sleep(Time);
        }
    }
}
