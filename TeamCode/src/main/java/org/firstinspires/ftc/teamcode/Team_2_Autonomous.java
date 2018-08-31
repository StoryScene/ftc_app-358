package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous

public class Team_2_Autonomous extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    //org.firstinspires.ftc.teamcode.Meet2_Red.state state358;

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        double POWER =0.5;

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Starting to drive: ", POWER);
            telemetry.update();

            leftMotor.setPower(POWER);//run forward
            rightMotor.setPower(POWER);
            sleep(800);

            leftMotor.setPower(0.3);//turn left
            rightMotor.setPower(-0.3);
            sleep(650);

            leftMotor.setPower(0.75);//run left
            rightMotor.setPower(0.75);
            sleep(1000);

            leftMotor.setPower(-0.3);//turn right
            rightMotor.setPower(0.3);
            sleep(650);

            leftMotor.setPower(POWER);//run forward
            rightMotor.setPower(POWER);
            sleep(800);

            leftMotor.setPower(-0.3);//turn right
            rightMotor.setPower(0.3);
            sleep(650);

            leftMotor.setPower(0.8);//run right
            rightMotor.setPower(0.8);
            sleep(800);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);

            break;
        }
    }
}