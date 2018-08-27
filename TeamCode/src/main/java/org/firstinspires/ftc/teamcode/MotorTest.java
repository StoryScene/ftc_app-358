package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.*;

/**
 * Created by kevinwang on 1/30/18.
 */

@Autonomous

public class MotorTest extends LinearOpMode{

    Servo Servo;

    double RetractedPosition;
    double ExtendedPosition;

    int Time;

    public void runOpMode() throws InterruptedException{

        Servo = hardwareMap.servo.get("Motor");
        double Power = 0.8;
        Time = 1;

        waitForStart();

        while(opModeIsActive()){

            Servo.setPosition(Power*sin(Time*PI/180));
            sleep(10);
            Time = Time + 1;

        }
    }
}
