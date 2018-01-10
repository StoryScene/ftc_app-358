package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lawrencemao on 10/5/17.
 */

@Disabled
public class Claw_Prototype extends OpMode{

    Servo left;
    Servo right;

    public void init(){
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");
    }

    public void loop(){
        if (gamepad1.a) {
            left.setPosition(1);
            right.setPosition(0);
        }
        else{
            left.setPosition(0);
            right.setPosition(1);
        }
    }

}