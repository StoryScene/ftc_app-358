package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by lawrencemao on 10/18/17.
 * Using code from https://ftcforum.usfirst.org/forum/ftc-technology/android-studio/6361-mecanum-wheels-drive-code-example
 */

@Disabled
public class MecanumDrive2 extends OpMode{

    final static float JOYSTICK_DEADZONE = .1F;
    final static float MAX_SPEED = 1.0F;

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;

    public void init() {
        leftFront = hardwareMap.dcMotor.get("LF");
        rightFront = hardwareMap.dcMotor.get("RF");
        leftBack = hardwareMap.dcMotor.get("LB");
        rightBack = hardwareMap.dcMotor.get("RB");

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void loop() {
        gamepad1.left_stick_x = Range.clip(gamepad1.left_stick_x, -1, 1);
        gamepad1.left_stick_y = Range.clip(gamepad1.left_stick_y, -1, 1);
        gamepad1.right_stick_x = Range.clip(gamepad1.right_stick_x, -1, 1);

        if (gamepad1.left_stick_x < JOYSTICK_DEADZONE) {
            gamepad1.left_stick_x = 0;
        }

        if (gamepad1.left_stick_y < JOYSTICK_DEADZONE) {
            gamepad1.left_stick_y = 0;
        }

        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = 0.0;
        if (r != 0) { robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4; }
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        leftFront.setPower(v1);
        rightFront.setPower(v2);
        leftBack.setPower(v3);
        rightBack.setPower(v4);

        telemetry.addData("leftStickX: ", gamepad1.left_stick_x );
        telemetry.addData("leftStickY: ", gamepad1.left_stick_y );
        telemetry.addData("rightStickX: ", gamepad1.right_stick_x );

        telemetry.addData("v1 (FL): ", v1);

        telemetry.addData("r: ", r);
        telemetry.addData("robotAngle: ", robotAngle );
    }
}