package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lawrencemao on 11/6/17.
 */

@TeleOp
public class servoCalibrating extends OpMode{
    Servo servo;

    public void init() {
        servo = hardwareMap.servo.get("servo");
    }

    public void loop() {
        if (gamepad1.a){
            servo.setPosition(0.9);
        }
        else {
            servo.setPosition(0);
        }
    }
}