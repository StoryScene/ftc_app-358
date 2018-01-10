package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import static java.lang.Math.*;

/**
 * Created by kevinwang on 12/5/17.
 */

public class TeleOp_Drive_Code{

    static public Double magnitudeLeftStick(Gamepad gamePad){

        return sqrt(pow(gamePad.left_stick_x, 2) + pow(gamePad.left_stick_y, 2));

    }

    static public Double findMax(Double d1, Double d2, Double d3, Double d4) {

        return max(max(d1, d2), max(d3, d4));

    }

    static void TeleOpDrive(Gamepad gamePad, DcMotor frontLeft, DcMotor backLeft, DcMotor frontRight, DcMotor backRight){

        double drive;
        double strafe;
        double rotate;

        double joystick;

        if (magnitudeLeftStick(gamePad) > 1){

            drive = gamePad.left_stick_y / magnitudeLeftStick(gamePad);
            strafe = gamePad.left_stick_x / magnitudeLeftStick(gamePad);
            rotate = gamePad.right_stick_x;

            joystick = 1;

        }
        else {

            drive = gamePad.left_stick_y;
            strafe = gamePad.left_stick_x;
            rotate = gamePad.right_stick_x;

            joystick = max(magnitudeLeftStick(gamePad), abs(rotate));

        }

        double frontLeftPower = drive - strafe - rotate;
        double backLeftPower = drive + strafe - rotate;
        double frontRightPower = drive + strafe + rotate;
        double backRightPower = drive - strafe + rotate;

        double power = -1 * joystick * abs(joystick) / (0.5 * pow(gamePad.right_trigger, 2) + 1);
        double maxPower = findMax(abs(frontLeftPower), abs(backLeftPower), abs(frontRightPower), abs(backRightPower));

        frontLeft.setPower(power * frontLeftPower / maxPower);
        backLeft.setPower(power * backLeftPower / maxPower);
        frontRight.setPower(power * frontRightPower / maxPower);
        backRight.setPower(power * backRightPower / maxPower);

    }

}