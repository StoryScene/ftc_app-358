package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by KPSS on 30.10.17.
 */
@TeleOp
@Disabled

public class Ultra_Test extends OpMode{

    Servo rightservo;
    Servo leftservo;
    public void init(){
        rightservo = hardwareMap.servo.get("rightservo");
        leftservo = hardwareMap.servo.get("leftservo");
    }
    public void loop(){
        rightservo.setPosition(0.5);
        leftservo.setPosition(0.5);
    }
}