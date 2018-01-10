package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import static java.lang.Math.*;

/**
 * Created by kevinwang on 11/15/17.
 */

@Disabled

public class TeleOp_Drive extends LinearOpMode{

    //Declaring all the motors, servos, and sensors.
    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;
    Servo left;
    Servo right;
    Servo arm;
    ColorSensor color;



    //This function finds the magnitude of the left stick of a gamepad.
    public Double magnitudeLeftStick(Gamepad gamepad){
        return sqrt(pow(gamepad.left_stick_x, 2) + pow(gamepad.left_stick_y, 2));
    }

    //This function finds the max value given 4 values.
    public Double findMax(Double d1, Double d2, Double d3, Double d4){
        return max(max(d1, d2), max(d3, d4));
    }

    //Run OpMode code.
    public void runOpMode() throws InterruptedException{



        //Defining where to find the motors, servos, and sensors.
        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");
        lS = hardwareMap.dcMotor.get("linearSlide");
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");
        arm = hardwareMap.servo.get("arm");
        color = hardwareMap.colorSensor.get("color");

        //Defining the directions of the motors and servos.
        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        //TeleOp process.
        while (opModeIsActive()){

            //Auto-servo is held in place.
            arm.setPosition(.95);

            //Defining drive, strafe, and rotation power.
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            //Defining the motor power distribution.
            double flPower = drive - strafe - rotate;
            double blPower = drive + strafe - rotate;
            double frPower = drive + strafe + rotate;
            double brPower = drive - strafe + rotate;

            //Defining the joystick magnitude and maximum power.
            double POWER = -1 * pow(Range.clip(max(magnitudeLeftStick(gamepad1), abs(rotate)), -1, 1), 3) / (0.5 * pow(gamepad1.right_trigger, 2) + 1);
            telemetry.addData("POWER: ", POWER);
            double maxPower = findMax(abs(flPower), abs(blPower), abs(frPower), abs(brPower));
            telemetry.addData("maxPower: ", maxPower);
            telemetry.update();

            //Sets the power for all the drive motors.
            fL.setPower(POWER * flPower / maxPower);
            bL.setPower(POWER * blPower / maxPower);
            fR.setPower(POWER * frPower / maxPower);
            bR.setPower(POWER * brPower / maxPower);

            //This will lower the UltraLord if the left bumper is pressed and raise if the right is pressed.
            //The height will remain the same if both are pressed or neither are.
            if ((!(gamepad1.right_bumper) && !(gamepad1.left_bumper)) || (gamepad1.right_bumper && gamepad1.left_bumper)){
                lS.setPower(0);
            }
            else if (gamepad1.right_bumper){
                lS.setPower(0.5);
            }
            else if (gamepad1.left_bumper){
                lS.setPower(-0.5);
            }

            //This will open the UltraLord if A is pressed down, and open if A is released.
            if (gamepad1.a){
                left.setPosition(1);
                right.setPosition(1);
            }
            else if (!gamepad1.a){
                left.setPosition(0);
                right.setPosition(0);
            }




        }
    }
}
