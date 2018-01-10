package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lawrencemao on 10/31/17.
 */

@TeleOp
@Disabled

public class ultraLord_test extends OpMode{

    DcMotor lift;
    Servo rServo;
    Servo lServo;

    double lPos;
    double rPos;
    double lift_pos;

    public void init(){
        lift = hardwareMap.dcMotor.get("lift");
        lServo = hardwareMap.servo.get("lS");
        rServo = hardwareMap.servo.get("rS");
    }
    public void loop(){
        lift.setPower(gamepad1.left_stick_y);
        lServo.setPosition(gamepad1.right_stick_y);
        rServo.setPosition(gamepad1.left_stick_y);

        lPos = lServo.getPosition();
        rPos = lServo.getPosition();

        lift_pos = lift.getCurrentPosition();

        telemetry.addData("Left Servo: ", lPos);
        telemetry.addData("Right Servo: ", rPos);
        telemetry.addData("Lifting_Pos:", lift_pos);
    }
}