package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;


@TeleOp(name = "Sensor: Digital touch", group = "Sensor")

public class The_button extends OpMode {

    DigitalChannel digitalTouch;
    DcMotor lS;

    public void init(){
        digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);
        lS = hardwareMap.dcMotor.get("lS");
    }

    public void loop(){

        if (digitalTouch.getState() == false) {
            telemetry.addData("Digital Touch", "Is Pressed");
            //if (gamepad2.dpad_up) ls.setPower(0.7);
            if (lS.getPower() > 0 ) lS.setPower(0);

        } else {
            telemetry.addData("Digital Touch", "Is Not Pressed");
            if (gamepad2.dpad_up) {
                lS.setPower(0.7);
            } else lS.setPower(0);

        }
        if (gamepad2.dpad_down){
            lS.setPower(-0.4);
        }

    }
}