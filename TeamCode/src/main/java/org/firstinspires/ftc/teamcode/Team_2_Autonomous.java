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
            sleep(820);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(0.3);//turn left
            rightMotor.setPower(-0.3);
            sleep(545);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(0.73);//run left
            rightMotor.setPower(0.73);
            sleep(918);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(-0.3);//turn right
            rightMotor.setPower(0.3);
            sleep(653);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(0.2);//run forward
            rightMotor.setPower(0.2);
            sleep(818);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(4000);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(0.2);//run forward
            rightMotor.setPower(0.2);
            sleep(999);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(-0.3);//turn right
            rightMotor.setPower(0.3);
            sleep(635);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);
            sleep(100);

            leftMotor.setPower(0.7);//run right
            rightMotor.setPower(0.7);
            sleep(785);

            leftMotor.setPower(0);//stop
            rightMotor.setPower(0);

            break;
        }
    }
}
