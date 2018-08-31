package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous

public class team2drive extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    //org.firstinspires.ftc.teamcode.Meet2_Red.state state358;

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        double POWER =1.0;

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Starting to drive: ", POWER);
            telemetry.update();

            leftMotor.setPower(POWER);//run forward
            rightMotor.setPower(POWER);
            sleep(1000);

            leftMotor.setPower(-1.0);//turn left
            rightMotor.setPower(1.0);
            sleep(1000);

            leftMotor.setPower(1.5);//run left
            rightMotor.setPower(1.5);
            sleep(1000);

            leftMotor.setPower(1.0);//turn right
            rightMotor.setPower(-1.0);
            sleep(1000);

            leftMotor.setPower(0.6);//run forward
            rightMotor.setPower(0.6);
            sleep(1000);

            sleep(4000);

            leftMotor.setPower(0.4);//run forward
            rightMotor.setPower(0.4);
            sleep(1000);

            leftMotor.setPower(1.0);//turn right
            rightMotor.setPower(-1.0);
            sleep(1000);

            leftMotor.setPower(2);//run right
            rightMotor.setPower(2);
            break;
        }
    }
}